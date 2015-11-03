package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Action;
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
	public Action getRoundAction()
	{
		if (true) {
			this.setRoundStatus(RoundStatus.CALLED);
			return new CallAction();
		}
		else {
			this.setRoundStatus(RoundStatus.FOLDED);
			return new FoldAction();
		}
	}
}
