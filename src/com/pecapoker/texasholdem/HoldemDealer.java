package com.pecapoker.texasholdem;

import java.util.ArrayList;
import java.util.List;

import com.pecapoker.playingcards.Player;
import com.pecapoker.playingcards.Pot;
import com.pecapoker.texasholdem.HdConst.RoundStatus;

class HoldemDealer extends com.pecapoker.playingcards.Dealer {
	public HoldemDealer()
	{
		super();
	}
	private final int NO_MIN_CHIP = 99999999;

	@Override
	protected Player decideWinner2players(Player p1, Player p2) {
		// TODO 仮実装
		if ((((HoldemPlayer)p1).getRoundStatus() == RoundStatus.FOLDED)
			&& (((HoldemPlayer)p1).getRoundStatus() == RoundStatus.FOLDED) )
		{
			return null;
		}
		if (((HoldemPlayer)p1).getRoundStatus() == RoundStatus.FOLDED)
		{
			return p2;
		}
		if (((HoldemPlayer)p2).getRoundStatus() == RoundStatus.FOLDED)
		{
			return p1;
		}

		if (p1.getHighestCard() == null && p2.getHighestCard() == null)
		{
			return null;
		}
		if (p1.getHighestCard() == null)  {
			return p2;
		}
		if (p2.getHighestCard() == null) {
			return p1;
		}

		if (p1.getHighestCard().compareTo(p2.getHighestCard()) == 0) {
			return null;
		}
		if (p1.getHighestCard().compareTo(p2.getHighestCard()) > 0) {
			return p1;
		}

		return p2;
	}

	public void printAllPlayerHands() {
		for(Player p : players) {
			System.out.print(p + " is ");
			p.printHand();
		}
	}

	private List<HoldemPlayer> _selectWinners(List<Player> activePlayers)
	{
		List<HoldemPlayer> winners = new ArrayList<HoldemPlayer>();
		for (Player pl : activePlayers) {
			HoldemPlayer p = (HoldemPlayer)pl;
			if (p.isFolded()) {
				continue;
			}
			if (winners.size() == 0) {
				winners.add(p);
			}
			else {
				HoldemPlayer winner = winners.get(0);
				winner = (HoldemPlayer)decideWinner2players(winner, p);
				if (winner == null) {
					winners.add(p);
				}
				else if (winner == p) {
					winners.clear();
					winners.add(p);
				}
				else {
					// 何もしない
					;
				}
			}
		}
		return winners;
	}

	// TODO 5.カードが増える＝フロップに進む
	// TODO 6.カードが増える＝ターンに進む
	// TODO 7.カードが増える＝リバーに進む
	// TODO 8.ペアの判定
	public List<HoldemPlayer> concludeHand(Pot pot)
	{
		printAllPlayerHands();

		List<HoldemPlayer> winners = new ArrayList<HoldemPlayer>();
		winners = _selectWinners(pot.getPlayers());
		assert winners.size() > 0;
		int dividedChip = pot.getChip() / winners.size();
		for (Player p : winners) {
			p.receiveChip(dividedChip);
		}
		return winners;
	}

	public void initRoundStatusAfterRaise(Player raiser)
	{
		for(Player p : players)
		{
			if (p == raiser)
			{
				continue;
			}
			if (((HoldemPlayer)p).getRoundStatus() == RoundStatus.FOLDED)
			{
				continue;
			}
			if (((HoldemPlayer)p).getRoundStatus() == RoundStatus.ALLINED)
			{
				continue;
			}
			((HoldemPlayer)p).resetActionStatusOnly();
		}
	}
	/**
	 * 全プレイヤーにアクションさせる
	 */
	public void round() throws RoundRulesException {
		/**
		 * 全Playerを１周するまでのルール
		 */
		RoundActionRule rar = new RoundActionRule();
		Player raiser = null;
		rar.setCallAmount(100);
		do
		{
			initRoundStatusAfterRaise(raiser);
			raiser = _scanPlayers(raiser, rar);
		} while (raiser != null);
	}

	/**
	 * 新たなレイズが入るか、全員回るまで回す
	 * @param raiser　このプレイヤーの次の人から回る
	 * @return　レイズが入ったらそのプレイヤー
	 * @throws RoundRulesException
	 */
	private Player _scanPlayers(Player raiser, RoundActionRule rar) throws RoundRulesException
	{
		int actionedNum = 0;
		int iRaiser = _getPlayerIndex(raiser);
		if (iRaiser != -1) {
			actionedNum++;
		}
		int iPlayer = this.players.getNextIndex(iRaiser);
		assert iPlayer >= 0;

		while(actionedNum < this.players.size())
		{
			HoldemPlayer p = (HoldemPlayer)this.players.get(iPlayer);
			if (p.getRoundStatus() == RoundStatus.FOLDED) {
				iPlayer = this.players.getNextIndex(iPlayer);
				actionedNum++;
				continue;
			}
			p.getRoundAction(rar);
			actionToRoundActionRule(rar, p.getLastAction());
			if (p.isRaised()) {
				return p;
			}
			iPlayer = this.players.getNextIndex(iPlayer);
			actionedNum++;
		}
		return null;
	}

	/**
	 * アクションの結果をルール（最低ベット額）に反映
	 * @param rar
	 * @param p
	 */
	public void actionToRoundActionRule(RoundActionRule rar, Action ac) {
		if (ac.isRaise()) {
			rar.setLastRaiseDiffAmount(ac.getChip() - rar.getCallAmount());
			rar.setCallAmount(ac.getChip());
		}
	}

	private int _getPlayerIndex(Player raiser) {
		int iPlayer = -1;
		if (raiser != null) {
			iPlayer = this.players.indexOf(raiser);
			assert iPlayer >= 0;
		}
		return iPlayer;
	}

	public void resetHand() {
		for(Player p : players) {
			p.resetHand();
			((HoldemPlayer)p).resetAction();
		}
	}

	public List<Pot> collectChipToPot() {
		List<Pot> pots = new ArrayList<Pot>();
		int beforeMinChip = 0;
		_makePots(pots, beforeMinChip);
		return pots;
	}

	private void _makePots(List<Pot> pots, int beforeMinChip) {
		// 一番安いFold以外のActionを探す
		int minChip = getMinimumChipFromAction(beforeMinChip);
		if (minChip == NO_MIN_CHIP) {
			return;
		}

		Pot currentPot = new Pot();

		// プレイヤーを集める
		for(Player p : this.players) {
			HoldemPlayer hp = (HoldemPlayer)p;
			if (hp.getLastAction().getChip() <= beforeMinChip)
			{
				continue;
			}
			currentPot.addChip(Math.min(hp.getLastAction().getChip(), minChip - beforeMinChip));
			if (hp.isFolded()) {
				continue;
			}
			if (hp.getLastAction().getChip() < minChip)
			{
				continue;
			}
			currentPot.addPlayer(hp);
		}
		pots.add(currentPot);

		_makePots(pots, minChip);
	}

	public int getMinimumChipFromAction(int beforeMinChip) {
		int minChip = NO_MIN_CHIP;
		for (Player p : this.players) {
			HoldemPlayer hp = (HoldemPlayer)p;
			if (hp.isFolded()) {
				continue;
			}
			if ((hp.getLastAction().getChip() > beforeMinChip)
				&& (hp.getLastAction().getChip() < minChip)
					) {
				minChip = hp.getLastAction().getChip();
			}
		}
		return minChip;
	}

	public ArrayList<Player> getPots() {
		return null;
	}


}
