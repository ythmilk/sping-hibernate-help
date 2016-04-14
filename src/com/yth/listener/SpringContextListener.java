package com.yth.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application Lifecycle Listener implementation class SpringContextListener
 *
 */
@WebListener
public class SpringContextListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public SpringContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext sct = arg0.getServletContext();
		String congif = sct.getInitParameter("configLocation");
		ApplicationContext act = new ClassPathXmlApplicationContext(congif);
		sct.setAttribute("ApplicationContext", act);

	}

}
