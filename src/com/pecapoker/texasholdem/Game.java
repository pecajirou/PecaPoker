package com.pecapoker.texasholdem;

import java.util.List;

import com.pecapoker.playingcards.Player;
import com.pecapoker.playingcards.Pot;

public class Game {
	public static void main(String[] args) {
		System.out.println("hello texasholdem");

		try {
			HoldemDealer d = new HoldemDealer();
			HoldemPlayer cpu1 = new AiHoldemPlayer(1, "Hiyoten");
			HoldemPlayer cpu2 = new AiHoldemPlayer(2, "saburou");
			ConsoleHoldemPlayer human = new ConsoleHoldemPlayer(3, "Jirou");

			d.addPlayer(cpu1);
			d.addPlayer(cpu2);
			d.addPlayer(human);

			// シャッフル
			d.shuffle();

			// どちらかのchipが0になるまで、または10ハンド
			for (int i = 0; i < 3; i++) {
				//
				// 1ハンド
				//
				{
					System.out.println("##### HAND " + (i+1) + "#####");
					Pot pot = new Pot();
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
						d.collectChipToPot(pot);

						// 勝敗判定
						List<HoldemPlayer> winners = d.concludeHand(pot.getChip());
						if (winners.size() == 0) {
							System.out.println("draw");
						}
						else {
							System.out.print("winner is ...");
							for(Player p : winners) {
								System.out.print(p + ", ");
							}
							System.out.println("");
						}
					}
				}
				d.getPlayers().printAllPlayerChips();
			}
			System.out.print("*** ");
			d.getPlayers().printAllPlayerChips();
		}
		catch( Exception ex) {
			System.out.println("!!!Exception occured" + ex.getMessage());
			System.out.println("!!! in " + ex.getStackTrace());
		}
	}
}
