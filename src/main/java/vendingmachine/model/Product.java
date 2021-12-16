package vendingmachine.model;

public class Product {

	private final String name;
	private final int price;
	private int number;
	private static final int SOLD_OUT_COUNT = 0;

	public Product(String name, int price, int number) {
		this.name = name;
		this.price = price;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getNumber() {
		return number;
	}

	public boolean isSoldOut() {
		return number == SOLD_OUT_COUNT;
	}

	public void takeOutProduct() {
		number--;
	}
}
