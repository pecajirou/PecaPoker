package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Dealer;

public class Game {
	public static void main(String[] args) {
		System.out.println("hello world");

		Dealer d = new HoldemDealer();
		HoldemPlayer jirou = new HoldemPlayer(1, "Jirou");

//		d.AddPlayer(jirou);

	}
}
