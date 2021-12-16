package vendingmachine.model;

import java.util.Map;

public class CoinCase {

	private final Map<Coin, Integer> coinCase;
	private static final int INITIAL_VALUE = 0;
	private static final int COUNTING_COIN = 1;

	public CoinCase(Map<Coin, Integer> coinCase) {
		this.coinCase = coinCase;
	}

	public Map<Coin, Integer> getCoinCase() {
		return coinCase;
	}

	public void increasePickedCoinCount(Coin coin) {
		coinCase.put(coin, coinCase.get(coin) + COUNTING_COIN);
	}

	public int returnChange(Coin coin, int countUserReceiveChange) {
		int count = coinCase.get(coin);
		if (count < countUserReceiveChange) {
			useAllCoins(coin);
			return count;
		}
		decreasePickedCoinCount(coin, countUserReceiveChange);
		return countUserReceiveChange;
	}

	private void decreasePickedCoinCount(Coin coin, int countUserReceiveChange) {
		coinCase.put(coin, coinCase.get(coin) - countUserReceiveChange);
	}

	private void useAllCoins(Coin coin) {
		coinCase.put(coin, INITIAL_VALUE);
	}
}
