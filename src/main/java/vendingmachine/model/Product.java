package vendingmachine.model;

public class Product {

	private final String name;
	private final int price;
	private int count;
	private static final int SOLD_OUT_COUNT = 0;

	public Product(String name, int price, int count) {
		this.name = name;
		this.price = price;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getCount() {
		return count;
	}

	public boolean isSoldOut() {
		return count == SOLD_OUT_COUNT;
	}

	public void takeOutProduct() {
		count--;
	}
}
