package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Player;

public class Game {
	public static void main(String[] args) {
		System.out.println("hello texasholdem");

		try {
			HoldemDealer d = new HoldemDealer();
			HoldemPlayer cpu = new HoldemPlayer(1, "Hiyoten");
			ConsoleHoldemPlayer human = new ConsoleHoldemPlayer(2, "Jirou");

			d.addPlayer(cpu);
			d.addPlayer(human);

			// シャッフル
			d.shuffle();

			// どちらかのchipが0になるまで、または10ハンド
			for (int i = 0; i < 3; i++) {
				if (cpu.getChip() == 0) {
					break;
				}
				if (human.getChip() == 0) {
					break;
				}

				//
				// 1ハンド
				//
				{
					d.resetHand();

					// ２枚配る
					d.dealAllPlayers();
					d.dealAllPlayers();

					//
					// 1ラウンド
					//
					{
						// 全員アクションさせる
						d.round();

						// 勝敗判定
						Player winner = d.judgeWinner();
						if (winner == null) {
							System.out.println("draw");
						}
						else {
							System.out.println("winner is " + winner);
						}
					}
				}
				System.out.println(human + "=" + human.getChip() + ":" + cpu + " =" + cpu.getChip());
			}
			System.out.println("***" + human + "=" + human.getChip() + ":" + cpu + " =" + cpu.getChip());
		}
		catch( Exception ex) {
			System.out.println("!!!Exception occured" + ex.getMessage());
			System.out.println("!!! in " + ex.getStackTrace());
		}
	}
}
