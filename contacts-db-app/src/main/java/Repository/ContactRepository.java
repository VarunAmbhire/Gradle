package Repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Model.Contacts;

public class ContactRepository {
	List<Contacts> contact=new ArrayList<>();
	
	private Connection createConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:4040/swabhav", "root", "root");
		return connection;
	}
	
	public List<Contacts> getContacts() throws SQLException, Exception{
		PreparedStatement preparedStatement=(PreparedStatement) createConnection().prepareStatement("select * from contact");
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
			Contacts c=new Contacts(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			contact.add(c);
		}
		resultSet.close();
		createConnection().close();
		return contact;
	}
	
	public void addContacts(String id, String name, String email,String state, String gender) throws Exception {
		PreparedStatement preparedStatement=(PreparedStatement) createConnection().prepareStatement("insert into contact values(?,?,?,?,?)");
		preparedStatement.setString(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, email);
		preparedStatement.setString(4, state);
		preparedStatement.setString(5, gender);
		
		preparedStatement.execute();
		createConnection().close();
	}
	
	public void removeContact(String id) throws Exception {
		PreparedStatement preparedStatement=(PreparedStatement) createConnection().prepareStatement("delete from contact where id=?");
		preparedStatement.setString(1, id);
		
		preparedStatement.execute();		
		createConnection().close();
	}
	
	String name,email,state,gender;			
	public Contacts selectedContact(String id) throws Exception {
		PreparedStatement preparedStatement=(PreparedStatement) createConnection().prepareStatement("select * from contact where id=?");
		preparedStatement.setString(1, id);

		ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
			name=resultSet.getString(2);
			email=resultSet.getString(3);
			state=resultSet.getString(4);
			gender=resultSet.getString(5);
			}
			resultSet.close();
			createConnection().close();
		return new Contacts(id, name, email, state, gender);
	}
	
	public void update(String id, String name, String email,String state, String gender) throws Exception {
		PreparedStatement preparedStatement=
				(PreparedStatement) createConnection().prepareStatement("update contact set name=?,email=?,state=?,gender=?"+ 
				"where id=?");
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, email);
		preparedStatement.setString(3, state);
		preparedStatement.setString(4, gender);
		preparedStatement.setString(5, id);
		
		preparedStatement.execute();
		createConnection().close();
	}
	
	public List<Contacts> searchResult(String name) throws Exception{
		List<Contacts>result=new ArrayList<>();
		String sqlState="select * from contact where name like '%"+name+"%'";		
		PreparedStatement preparedStatement=(PreparedStatement)
				createConnection().prepareStatement(sqlState);
//		preparedStatement.setString(1, name);		
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
			Contacts c=new Contacts(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			result.add(c);
		}
		resultSet.close();
		createConnection().close();
		return result;
	}
}
