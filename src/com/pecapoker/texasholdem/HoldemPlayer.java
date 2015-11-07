package com.pecapoker.texasholdem;

import com.pecapoker.texasholdem.HdConst.RoundStatus;

class HoldemPlayer extends com.pecapoker.playingcards.Player {
	protected final int AC_FOLD = 0;
	protected final int AC_CALL = 1;
	protected final int AC_RAISE = 2;

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
	public Action getRoundAction(RoundActionRule rar) throws RoundRulesException
	{
		int actionNo = _getActionNo(rar);
		if (actionNo == AC_CALL)
		{
			return doCall(rar);
		}
		else if (actionNo == AC_RAISE)
		{
			int amount = _getRaiseAmount(rar);
			return doRaise(rar, amount);
		}
		else {
			return doFold();
		}
	}

	protected int _getActionNo(RoundActionRule rar)
	{
		return AC_FOLD;
	}
	protected int _getRaiseAmount(RoundActionRule rar)
	{
		return 100;
	}

	public CallAction doCall(RoundActionRule rar) throws RoundRulesException {

		int diffAmount = rar.getCallAmount() - this.lastAction.getChip();
		assert diffAmount >= 0;
		if (this.chip < diffAmount) {
			throw new RoundRulesException(" this.chip < diffAmount (" + this.getChip() + " < " + diffAmount + ")");
		}
		this.chip -= diffAmount;
		this.lastAction =  new CallAction(this.lastAction.getChip() + diffAmount);

		System.out.println(this + " call");
		return (CallAction)this.lastAction;
	}

	public RaiseAction doRaise(RoundActionRule rar, int amount) throws RoundRulesException {

		int diffAmount = amount - this.lastAction.getChip();
		if (this.chip < diffAmount) {
			throw new RoundRulesException(" this.chip < diffAmount (" + this.getChip() + " < " + diffAmount + ")");
		}
		this.chip -= diffAmount;
		this.lastAction = new RaiseAction(this.lastAction.getChip() + diffAmount);

		System.out.println(this + " raise make " + lastAction.getChip());
		return (RaiseAction)this.lastAction;
	}

	public FoldAction doFold()
	{
		this.lastAction = new FoldAction(this.lastAction.getChip());

		System.out.println(this + " fold");
		return (FoldAction)this.lastAction;
	}

	public Action getLastAction() {
		return this.lastAction;
	}

	public boolean isRaised()
	{
		return lastAction instanceof RaiseAction;
	}

	public boolean isFolded()
	{
		return lastAction instanceof FoldAction;
	}

	public boolean isCalled()
	{
		return lastAction instanceof CallAction;
	}
}
