package com.pecapoker.texasholdem;

import org.junit.Test;

import com.pecapoker.playingcards.Player;

public class GameTest {

	@Test
	public void test() {
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
				d.resetHand();

				// ２枚配る
				d.dealAllPlayers();
				d.dealAllPlayers();

				//
				// 1ラウンド
				//
				{
					// 初期化
					d.initRound();

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
