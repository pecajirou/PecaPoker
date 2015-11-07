package com.pecapoker.texasholdem;

import com.pecapoker.texasholdem.HdConst.RoundStatus;

class HoldemPlayer extends com.pecapoker.playingcards.Player {
	protected Action lastAction;

	public HoldemPlayer(int id, String name)
	{
		super(id, name);
		resetAction();
	}
	public RoundStatus getRoundStatus() {
		return lastAction.getRoundStatus();
	}

	public void resetAction() {
		this.lastAction = new NoneAction(0);
	}
	public void resetActionStatusOnly()
	{
		lastAction = new NoneAction(lastAction.getChip());
	}

	/**
	 * アクションを選択する
	 * @return 選択したアクション
	 */
	public Action getRoundAction(HoldemRoundActionRule rar) throws RoundRulesException
	{
		if (true) {
			return doCall((HoldemRoundActionRule)rar);
		}
		else {
			return doFold();
		}
	}

	public CallAction doCall(HoldemRoundActionRule rar) throws RoundRulesException {
		System.out.println(this + " call");

		int diffAmount = rar.getCallAmount() - this.lastAction.getChip();
		assert diffAmount >= 0;
		if (this.chip < diffAmount) {
			throw new RoundRulesException("this.chip < diffAmount");
		}
		this.chip -= diffAmount;
		this.lastAction =  new CallAction(this.lastAction.getChip() + diffAmount);
		return (CallAction)this.lastAction;
	}

	public RaiseAction doRaise(HoldemRoundActionRule rar, int amount) throws RoundRulesException {
		System.out.println(this + " raise");

		int diffAmount = amount - this.lastAction.getChip();
		if (this.chip < diffAmount) {
			throw new RoundRulesException("this.chip < diffAmount");
		}
		this.chip -= diffAmount;
		this.lastAction = new RaiseAction(this.lastAction.getChip() + diffAmount);
		return (RaiseAction)this.lastAction;
	}

	public FoldAction doFold() {
		System.out.println(this + " fold");

		this.lastAction = new FoldAction(this.lastAction.getChip());
		return (FoldAction)this.lastAction;
	}

	public Action getLastAction() {
		return this.lastAction;
	}


}
