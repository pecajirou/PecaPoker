package com.pecapoker.texasholdem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleHoldemPlayer extends HoldemPlayer {

	public ConsoleHoldemPlayer(int id, String name) {
		super(id, name);
	}

	@Override
	protected int _getRaiseAmount(RoundActionRule rar)
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
	protected int _getActionNo(RoundActionRule rar)
	{
		printHand();
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
