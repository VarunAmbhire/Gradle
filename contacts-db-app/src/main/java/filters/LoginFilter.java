package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		

		String username = (String) session.getAttribute("user");
		String logInStatus = null;
		String url = req.getRequestURI();
		String par = url.substring(url.indexOf("p/"), url.length());
		String param = par.replace("p/", "");

		if (username != null) {
			logInStatus = session.getAttribute("logStatus").toString();
		}

		if (logInStatus == null || logInStatus.equals("false")) {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect("/contacts-db-app/Login?backTo=" + param + "&" + req.getQueryString());
			return;
		}
		chain.doFilter(request, response);

		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
