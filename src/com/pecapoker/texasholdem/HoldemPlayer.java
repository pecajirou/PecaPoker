package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Action;
import com.pecapoker.playingcards.RoundActionRule;
import com.pecapoker.texasholdem.HdConst.RoundStatus;

class HoldemPlayer extends com.pecapoker.playingcards.Player {
	private RoundStatus roundStatus;
	public HoldemPlayer(int id, String name)
	{
		super(id, name);
		roundStatus = RoundStatus.NONE;
	}
	public RoundStatus getRoundStatus() {
		return roundStatus;
	}
	protected void setRoundStatus(RoundStatus roundStatus) {
		this.roundStatus = roundStatus;
	}

	public void resetRoundStatus()
	{
		setRoundStatus(RoundStatus.NONE);
	}

	@Override
	/**
	 * アクションを選択する
	 * @return 選択したアクション
	 */
	public Action getRoundAction(RoundActionRule rar) throws RoundRulesException
	{
		if (true) {
			return call((HoldemRoundActionRule)rar);
		}
		else {
			this.setRoundStatus(RoundStatus.FOLDED);
			return new FoldAction();
		}
	}

	public CallAction call(HoldemRoundActionRule rar) throws RoundRulesException {
		System.out.println(this + " call");
		if (this.chip < rar.getCallAmount()) {
			throw new RoundRulesException("this.chip < rar.getCallAmount()");
		}
		this.setRoundStatus(RoundStatus.CALLED);
		this.chip -= rar.getCallAmount();
		return new CallAction(100);
	}
}
