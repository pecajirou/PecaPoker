package com.pecapoker.texasholdem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleHoldemPlayer extends HoldemPlayer {

	private final int AC_FOLD = 0;
	private final int AC_CALL = 1;
	private final int AC_RAISE = 2;

	public ConsoleHoldemPlayer(int id, String name) {
		super(id, name);
	}

	public int getRaiseAmount(HoldemRoundActionRule rar)
	{
		System.out.println(this + " Raise amount ? " );
		int amount = 0;

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			String buf = br.readLine();
			amount = Integer.parseInt(buf);
		}
		catch (Exception ex)
		{
			;
		}
		if (amount < rar.getMinRaiseAmount())
		{
			amount = rar.getMinRaiseAmount();
		}
		return amount;
	}
	@Override
	/**
	 * アクションを選択する
	 * @return 選択したアクション
	 */
	public Action getRoundAction(HoldemRoundActionRule rar) throws RoundRulesException
	{
		printHand();
		int actionNo = getActionNo((HoldemRoundActionRule)rar);
		if (actionNo == AC_CALL)
		{
			return doCall((HoldemRoundActionRule)rar);
		}
		else if (actionNo == AC_RAISE)
		{
			int amount = getRaiseAmount((HoldemRoundActionRule)rar);
			return doRaise((HoldemRoundActionRule)rar, amount);
		}
		else {
			return doFold();
		}
	}

	private int getActionNo(HoldemRoundActionRule rar) {
		if (this.chip < rar.getMinRaiseAmount())
		{
			System.out.println(this + " Select action 0:FOLD 1:CALL" );
		}
		else {
			System.out.println(this + " Select action 0:FOLD 1:CALL 2:RAISE" );
		}

		int actionNo = AC_FOLD;

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			String buf = br.readLine();
			int an = Integer.parseInt(buf);
			if (an == 2 && this.chip < rar.getMinRaiseAmount()) {
				actionNo = AC_FOLD;
			}
			else {
				actionNo = an;
			}
		} catch (Exception ex)
		{
			;
		}
		return actionNo;
	}
}
