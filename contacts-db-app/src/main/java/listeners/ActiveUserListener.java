package listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ActiveUserListener
 *
 */
public class ActiveUserListener implements HttpSessionListener {
	ServletContext context;
	static int activeUser=0;
	
		/**
	 * Default constructor.
	 */
	public ActiveUserListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

		context = se.getSession().getServletContext();		
		
		if(context.getAttribute("activeUser")==null) {
			activeUser=0;
		}		
		activeUser++;
		context.setAttribute("activeUser", activeUser);
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		context = se.getSession().getServletContext();
//		activeUser=(int) context.getAttribute("activeUser");
		activeUser--;
		context.setAttribute("activeUser", activeUser);
	}

}
