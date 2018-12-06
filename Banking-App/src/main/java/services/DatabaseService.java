package services;

import java.sql.SQLException;
import java.util.List;

import model.BankMaster;
import model.BankTransaction;
import repository.DatabaseHandler;

public class DatabaseService {
	DatabaseHandler databaseHandler = new DatabaseHandler();

	public void registerUser(String id, String name, int balance, String date, String password)
			throws ClassNotFoundException, SQLException {
		databaseHandler.registerDetails(id, name, balance, date, password);
	}

	public List<BankMaster> getBankMaster(String id) throws ClassNotFoundException, SQLException {
		return databaseHandler.getBankMaster(id);
	}

	public void addTransaction(String txnId, String name, String type, int amount, String date, String customerId)
			throws ClassNotFoundException, SQLException {
		databaseHandler.transaction(txnId, name, type, amount, date, customerId);
	}

	public List<BankTransaction> getTansactionList() throws ClassNotFoundException, SQLException {
		return databaseHandler.getTransaction();
	}
	
	public List<BankTransaction>getPassbookTransaction(String id) throws ClassNotFoundException, SQLException{
		return databaseHandler.getPassBookTransaction(id);
	}
	
	public void updateBalance(String id, int balance) throws ClassNotFoundException, SQLException {
		 databaseHandler.updateBalance(id, balance);
	}
}
