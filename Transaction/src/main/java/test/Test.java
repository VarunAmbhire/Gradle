package test;

import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

import Repository.DatabaseRepository;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		DatabaseRepository databaseRepository = new DatabaseRepository();
		Connection connection=databaseRepository.createConnection();
		
		Scanner scanner = new Scanner(System.in);
		int amount = scanner.nextInt();
		scanner.close();
		try {
			connection.setAutoCommit(false);
			
			int custBalance = databaseRepository.getCustomerBalance(100);
			int merchBalance = databaseRepository.getMerchantBalance(1);

			System.out.println("Customer Balance " + custBalance + " Merchant Balance" + merchBalance);

			int cBalance = custBalance - amount;
			int mBalance = merchBalance + amount;

			System.out.println("Customer Balance " + cBalance + " Merchant Balance" + mBalance);

			databaseRepository.updateCustomer(100, cBalance);
			databaseRepository.updateMerchant(1, mBalance);

			connection.commit();

		} catch (Exception e) {
			System.out.println(e);
			databaseRepository.createConnection().rollback();

		}

	}

}
