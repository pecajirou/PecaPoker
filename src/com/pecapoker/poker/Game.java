package com.pecapoker.poker;

import com.pecapoker.playingcards.Dealer;

public class Game {
	public static void main(String[] args) {
		System.out.println("hello world");

		Dealer d = new PokerDealer();
		PokerPlayer jirou = new PokerPlayer(1, "Jirou");

//		d.AddPlayer(jirou);

	}
}
