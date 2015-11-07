package com.pecapoker.texasholdem;

import org.junit.Test;

import com.pecapoker.playingcards.Player;
import com.pecapoker.playingcards.Pot;

public class GameTest {

	@Test
	public void testGame() {
		System.out.println("hello texasholdem");

		try {
			HoldemDealer d = new HoldemDealer();
			HoldemPlayer jirou = new HoldemPlayer(1, "Jirou");
			HoldemPlayer saburou = new HoldemPlayer(2, "Saburou");

			d.addPlayer(jirou);
			d.addPlayer(saburou);

			// シャッフル
			d.shuffle();

			// どちらかのchipが0になるまで、または10ハンド
			for (int i = 0; i < 10; i++) {
				if (jirou.getChip() == 0) {
					break;
				}
				if (saburou.getChip() == 0) {
					break;
				}

				//
				// 1ハンド
				//
				{
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
						Player winner = d.concludeHand(pot.getChip());
						if (winner == null) {
							System.out.println("draw");
						}
						else {
							System.out.println("winner is " + winner);
						}
					}
				}
				System.out.println("Jirou=" + jirou.getChip() + " Saburou=" + saburou.getChip());
			}
			System.out.println("*** Jirou=" + jirou.getChip() + " Saburou=" + saburou.getChip());
		}
		catch( Exception ex) {
			System.out.println("!!!Exception occured" + ex.getMessage());
			System.out.println("!!! in " + ex.getStackTrace());
		}
	}

}
