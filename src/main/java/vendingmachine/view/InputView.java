package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.model.VendingMachine;
import vendingmachine.utils.exception.MoneyException;
import vendingmachine.utils.exception.ProductException;

public class InputView {

	private InputView() {
	}

	public static int writeVendingMachineAmount() {
		OutputView.askVendingMachineAmount();
		String inputMoney = Console.readLine();
		try {
			MoneyException.validateInputMoney(inputMoney);
			int money = Integer.parseInt(inputMoney);
			MoneyException.validateMoney(money);
			return money;
		} catch (IllegalArgumentException IAE) {
			OutputView.printError(IAE);
			return writeVendingMachineAmount();
		}
	}

	public static String writeProductsInfo() {
		OutputView.askProductInfo();
		try {
			return ProductException.validateInputProductsInfo(Console.readLine());
		} catch (IllegalArgumentException IAE) {
			OutputView.printError(IAE);
			return writeProductsInfo();
		}
	}

	public static int writeInsertMoney() {
		OutputView.askInsertMoney();
		String inputMoney = Console.readLine();
		try {
			MoneyException.validateInputMoney(inputMoney);
			int money = Integer.parseInt(inputMoney);
			MoneyException.validateMoney(money);
			return money;
		} catch (IllegalArgumentException IAE) {
			OutputView.printError(IAE);
			return writeInsertMoney();
		}
	}

	public static String writeProductNameToBuy(VendingMachine vendingMachine) {
		OutputView.printInsertedMoney(vendingMachine);
		OutputView.askProductToBuy();
		String inputName = Console.readLine();
		try {
			ProductException.validateNameOfProduct(inputName, vendingMachine);
			ProductException.validateProductSoldOut(inputName, vendingMachine.getProducts());
			return inputName;
		} catch (IllegalArgumentException IAE) {
			OutputView.printError(IAE);
			return writeProductNameToBuy(vendingMachine);
		}
	}
}
