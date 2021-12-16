package vendingmachine.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.model.Product;
import vendingmachine.utils.exception.ProductException;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class ProductFactory {

	private static final int NAME_INDEX = 0;
	private static final int PRICE_INDEX = 1;
	private static final int NUMBER_INDEX = 2;
	private static final String PRODUCTS_DELIMITER = ";";
	private static final String PRODUCT_INFO_DELIMITER = ",";
	private static final String PRODUCT_BRACKET_LEFT = "[";
	private static final String PRODUCT_BRACKET_RIGHT = "]";

	public static List<Product> makeProducts() {
		String inputProductsInfo = InputView.writeProductsInfo();
		try {
			List<Product> products = Arrays
				.stream(inputProductsInfo.split(PRODUCTS_DELIMITER))
				.map(ProductFactory::makeProduct)
				.collect(Collectors.toList());
			ProductException.validateDuplicatedName(products);
			return products;
		} catch (IllegalArgumentException IAE) {
			OutputView.printError(IAE);
			return makeProducts();
		}
	}

	public static Product makeProduct(String productInfo) {
		List<String> filteredInfo = filterInfo(productInfo);
		ProductException.validateFilteredInfo(filteredInfo);
		return new Product(findName(filteredInfo), findPrice(filteredInfo),
			findNumber(filteredInfo));
	}

	private static String findName(List<String> filteredInfo) {
		return filteredInfo.get(NAME_INDEX);
	}

	private static int findPrice(List<String> filteredInfo) {
		return Integer.parseInt(filteredInfo.get(PRICE_INDEX));
	}

	private static int findNumber(List<String> filteredInfo) {
		return Integer.parseInt(filteredInfo.get(NUMBER_INDEX));
	}

	private static List<String> filterInfo(String productInfo) {
		ProductException.validateInputProductsInfo(productInfo);
		return Arrays.asList(removeBrackets(productInfo).split(PRODUCT_INFO_DELIMITER));
	}

	private static String removeBrackets(String productInfo) {
		return productInfo
			.replace(PRODUCT_BRACKET_LEFT, "")
			.replace(PRODUCT_BRACKET_RIGHT, "");
	}
}
