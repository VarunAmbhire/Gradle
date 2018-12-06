package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BankTransaction;
import services.DatabaseService;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		DatabaseService databaseService = new DatabaseService();
		List<BankTransaction> transactionList = new ArrayList<>();
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String customerID = session.getAttribute("id").toString();

		try {
			transactionList = databaseService.getPassbookTransaction(customerID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("list", transactionList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/Home.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub;
		DatabaseService databaseService = new DatabaseService();

		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		String transactionId = uid.randomUUID().toString();
		String name = request.getParameter("name");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String type = request.getParameter("transactionMethod").substring(0, 1);
		Date d = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = simpleDateFormat.format(d);
		String customerId = request.getParameter("id");

		try {
			databaseService.addTransaction(transactionId, name, type, amount, date, customerId);

			int balance = databaseService.getBankMaster(customerId).get(0).getBalance();
			if (type.equals("W")) {
				balance = balance - amount;
			} else {
				balance = balance + amount;
			}
			databaseService.updateBalance(customerId, balance);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("Home");
	}

}
