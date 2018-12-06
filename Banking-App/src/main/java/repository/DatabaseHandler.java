package repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.BankMaster;
import model.BankTransaction;

public class DatabaseHandler {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:4040/swabhav", "root",
				"root");
		return connection;
	}

	public void registerDetails(String id, String name, int balance, String date, String password)
			throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) getConnection()
				.prepareStatement("insert into bank_master values(?,?,?,?,?)");
		preparedStatement.setString(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.setInt(3, balance);
		preparedStatement.setString(4, date);
		preparedStatement.setString(5, password);

		preparedStatement.execute();

		preparedStatement.close();
		getConnection().close();
	}

	public List<BankMaster> getBankMaster(String id) throws ClassNotFoundException, SQLException {
		List<BankMaster> details = new ArrayList<>();
		PreparedStatement preparedStatement = (PreparedStatement) getConnection()
				.prepareStatement("select * from bank_master where c_id=?");
		preparedStatement.setString(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			BankMaster bankMaster = new BankMaster(resultSet.getString(1), resultSet.getString(2),
					Integer.parseInt(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5));
			details.add(bankMaster);
		}
		preparedStatement.close();
		getConnection().close();
		return details;
	}

	public void transaction(String txnId, String name, String type, int amount, String date, String customerId)
			throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) getConnection()
				.prepareStatement("insert into bank_transaction values(?,?,?,?,?,?)");
		preparedStatement.setString(1, txnId);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, type);
		preparedStatement.setInt(4, amount);
		preparedStatement.setString(5, date);
		preparedStatement.setString(6, customerId);

		preparedStatement.execute();

		preparedStatement.close();
		getConnection().close();
	}

	public List<BankTransaction> getTransaction() throws ClassNotFoundException, SQLException {
		List<BankTransaction> transaction = new ArrayList<>();
		PreparedStatement preparedStatement = (PreparedStatement) getConnection()
				.prepareStatement("select * from bank_transaction");

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			BankTransaction bankTransaction = new BankTransaction(resultSet.getString(1), resultSet.getString(2),
					resultSet.getString(3), Integer.parseInt(resultSet.getString(4)), resultSet.getString(5),
					resultSet.getString(6));
			transaction.add(bankTransaction);
		}
		preparedStatement.close();
		getConnection().close();
		return transaction;
	}
	
	public List<BankTransaction> getPassBookTransaction(String id) throws ClassNotFoundException, SQLException {
		List<BankTransaction> transaction = new ArrayList<>();
		PreparedStatement preparedStatement = (PreparedStatement) getConnection()
				.prepareStatement("select * from bank_transaction where c_id=?");
		preparedStatement.setString(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			BankTransaction bankTransaction = new BankTransaction(resultSet.getString(1), resultSet.getString(2),
					resultSet.getString(3), Integer.parseInt(resultSet.getString(4)), resultSet.getString(5),
					resultSet.getString(6));
			transaction.add(bankTransaction);
		}
		preparedStatement.close();
		getConnection().close();
		return transaction;
	}
	
	public void updateBalance(String id, int balance) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement=(PreparedStatement) getConnection().prepareStatement("UPDATE bank_master set balance = ? where c_id=?");
		preparedStatement.setInt(1, balance);
		preparedStatement.setString(2, id);
		
		preparedStatement.execute();
		preparedStatement.close();
		getConnection().close();
	}
}
