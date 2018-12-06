package Repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DatabaseRepository {

	public Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:4040/swabhav", "root",
				"root");
		return connection;
	}

	public int getCustomerBalance(int id) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) createConnection()
				.prepareStatement("select balance from customer where id=?");
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		int balance = 0;
		while (resultSet.next()) {
			balance = resultSet.getInt(1);
		}

		preparedStatement.close();
		createConnection().close();

		return balance;
	}

	public void updateCustomer(int id, int balance) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) createConnection()
				.prepareStatement("update customer set balance=? where id=?");
		preparedStatement.setInt(1, balance);
		preparedStatement.setInt(2, id);

		preparedStatement.execute();
		preparedStatement.close();
		createConnection().close();
	}

	public int getMerchantBalance(int id) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) createConnection()
				.prepareStatement("select balance from merchant where id=?");
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		int balance = 0;
		while (resultSet.next()) {
			balance = resultSet.getInt(1);
		}
		preparedStatement.close();
		createConnection().close();
		return balance;
	}

	public void updateMerchant(int id, int balance) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) createConnection()
				.prepareStatement("update merchant set bance=? where id=?");
		preparedStatement.setInt(1, balance);
		preparedStatement.setInt(2, id);

		preparedStatement.execute();
		preparedStatement.close();
		createConnection().close();
	}
}
