package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opencsv.CSVWriter;

import model.BankTransaction;
import services.DatabaseService;

/**
 * Servlet implementation class DownLoadController
 */
public class DownLoadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownLoadController() {
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

		File file = new File("passbook.csv");
		try {
			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);

			for (BankTransaction bankTransaction : transactionList) {
				String data[] = { bankTransaction.getTransactionId(), bankTransaction.getName(),
						bankTransaction.getType(), bankTransaction.getAmount() + "", bankTransaction.getDate(),
						bankTransaction.getCustomerId() };
				writer.writeNext(data);

			}
			writer.close();
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			String filename = "passbook.csv";
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

			FileInputStream fileInputStream = new FileInputStream(filename);

			int i;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
			fileInputStream.close();
			out.close();
		} catch (Exception e) {

		}
		response.sendRedirect("Home");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
