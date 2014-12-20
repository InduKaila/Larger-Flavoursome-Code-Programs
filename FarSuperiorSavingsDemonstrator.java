class FarSuperiorSavingsAccount {
	private static final int INTEREST_RATE = 2; // the Interest Rate remains the same
	int balanceCalculated;
	

	int printBalance() {
		System.out.println("The balance is £" + balanceCalculated);

		return balanceCalculated;
	}

	int deposit(int deposit) {
		balanceCalculated += deposit;
		return balanceCalculated;
	}

	int withdraw(int withdrawal) {
		balanceCalculated -= withdrawal;
		return balanceCalculated;
	}

	
	int applyInterest() {
		int interest = (balanceCalculated * INTEREST_RATE) / 100;
		balanceCalculated += interest;
		return balanceCalculated;
	}
}

public class FarSuperiorSavingsDemonstrator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FarSuperiorSavingsAccount person1 = new FarSuperiorSavingsAccount();
		person1.deposit(40);
		person1.printBalance();
		person1.withdraw(120);
		person1.printBalance();
		person1.applyInterest();
		person1.printBalance();
		person1.withdraw(30);
		person1.printBalance();
	}
}
