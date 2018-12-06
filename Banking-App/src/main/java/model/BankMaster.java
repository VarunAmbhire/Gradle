package model;

public class BankMaster {
	private String customerId;
	private String name;
	private int balance;
	private String date;
	private String password;

	public BankMaster(String customerId, String name, int balance, String date, String password) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.balance = balance;
		this.date = date;
		this.password = password;
	}

	public String getId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public String getDate() {
		return date;
	}

	public String getPassword() {
		return password;
	}
}
