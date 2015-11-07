package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Action;
import com.pecapoker.playingcards.Player;
import com.pecapoker.playingcards.Pot;
import com.pecapoker.texasholdem.HdConst.RoundStatus;

class HoldemDealer extends com.pecapoker.playingcards.Dealer {
	public HoldemDealer()
	{
		super();
	}

	@Override
	protected Player decideWinner(Player p1, Player p2) {
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

	// TODO 1.CALL額をあわせる
	// TODO 2.Judgeを３人以上にする
	// TODO 3.AIを変える
	// TODO 4.AllIn
	// TODO 5.カードが増える＝フロップに進む
	// TODO 6.カードが増える＝ターンに進む
	// TODO 7.カードが増える＝リバーに進む
	// TODO 8.ペアの判定
	public Player judgeWinner() {
		// TODO ２人以上で判定
		// TODO 引き分けはどちらも勝ち
		printAllPlayerHands();

		Player winner = decideWinner(players.get(0), players.get(1));
		if (winner != null) {
			winner.receiveChip(pot.getChip());
		}
		else {
			int val = pot.getChip() / 2;
			players.get(0).receiveChip(val);
			players.get(1).receiveChip(val);
		}
		return winner;
	}

	public void initRoundStatus(Player raiser)
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
			else {
				((HoldemPlayer)p).resetRoundStatus();
			}
		}
	}
	/**
	 * 全プレイヤーにアクションさせる
	 */
	public void round() throws RoundRulesException {
		/**
		 * 全Playerを１周するまでのルール
		 */
		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		Player raiser = null;
		rar.setCallAmount(100);
		do
		{
			initRoundStatus(raiser);
			raiser = _scanPlayers(raiser, rar);
		} while (raiser != null);
	}

	/**
	 * 新たなレイズが入るか、全員回るまで回す
	 * @param raiser　このプレイヤーの次の人から回る
	 * @return　レイズが入ったらそのプレイヤー
	 * @throws RoundRulesException
	 */
	private Player _scanPlayers(Player raiser, HoldemRoundActionRule rar) throws RoundRulesException
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
			Player p = this.players.get(iPlayer);
			if (((HoldemPlayer)p).getRoundStatus() == RoundStatus.FOLDED) {
				actionedNum++;
				continue;
			}
			// TODO レイズできる条件の判定
			// TODO レイズ額の考慮　それにしたがってCALL, RAISE
			Action ac = ((HoldemPlayer)p).getRoundAction(rar);
			pot.addChip(ac.getChip());
			if (ac.isRaise()) {
				raiser = p;
				return raiser;
			}
			iPlayer = this.players.getNextIndex(iPlayer);
			actionedNum++;
		}
		return null;
	}

	private int _getPlayerIndex(Player raiser) {
		int iPlayer = -1;
		if (raiser != null) {
			iPlayer = this.players.indexOf(raiser);
			assert iPlayer > 0;
		}
		return iPlayer;
	}

	public void resetHand() {
		for(Player p : players) {
			p.resetHand();
		}
		pot = new Pot();
	}


}
