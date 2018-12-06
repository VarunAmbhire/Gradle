package model;

public class BankTransaction {

	private String transactionId;
	private String name;
	private String type;
	private int amount;
	private String date;
	private String customerId;

	public BankTransaction(String transactionId, String name, String type, int amount, String date, String customerId) {
		super();
		this.transactionId = transactionId;
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.customerId = customerId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public String getDate() {
		return date;
	}

	public String getCustomerId() {
		return customerId;
	}

}
