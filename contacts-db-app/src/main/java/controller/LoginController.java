package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LogInService;

/**
 * Servlet implementation class LogController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("views/Login.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		LogInService logInService=new LogInService();
		boolean status=logInService.authenticate(userName, password);
		
		HttpSession session=request.getSession(true);
		session.setAttribute("logStatus", status);
		session.setAttribute("user", userName);
		if(status) {
			response.sendRedirect(request.getParameter("loc"));
		}else {
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("views/Login.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
