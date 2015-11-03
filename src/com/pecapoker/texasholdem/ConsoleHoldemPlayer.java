package com.pecapoker.texasholdem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.pecapoker.playingcards.Action;
import com.pecapoker.playingcards.RoundActionRule;

public class ConsoleHoldemPlayer extends HoldemPlayer {

	private final int AC_FOLD = 0;
	private final int AC_CALL = 1;

	public ConsoleHoldemPlayer(int id, String name) {
		super(id, name);
	}

	@Override
	/**
	 * アクションを選択する
	 * @return 選択したアクション
	 */
	public Action getRoundAction(RoundActionRule rar) throws RoundRulesException
	{
		int actionNo = getActionNo();
		if (actionNo == AC_CALL) {
			return call((HoldemRoundActionRule)rar);
		}
		else {
			return fold();
		}
	}

	private int getActionNo() {
		System.out.println(this + "Select action 0:FOLD 1:CALL " );
		int actionNo = AC_FOLD;

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			String buf = br.readLine();
			actionNo = Integer.parseInt(buf);
		} catch (Exception ex)
		{
			actionNo = AC_FOLD;
		}
		return actionNo;
	}
}
