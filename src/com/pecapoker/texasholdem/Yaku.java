package com.pecapoker.texasholdem;

public class Yaku {
	protected int getYakuValue() {
		return 0;
	}
	public int compareTo(Yaku other) {
		return this.getYakuValue() - other.getYakuValue();
	}

}
