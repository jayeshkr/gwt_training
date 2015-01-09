package com.geo.gwt.server;

import java.util.Random;

import com.geo.gwt.client.GreetingService;
import com.geo.gwt.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
	
	public String getMarks(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be provided");
		}
		
		int Low = 10;
		int High = 100;
		
		Random m = new Random();
		int maths = m.nextInt(High-Low) + Low;
		Random e = new Random();
		int english = m.nextInt(High-Low) + Low;
		Random s = new Random();
		int science = m.nextInt(High-Low) + Low;

		String ret = "<font color=red><b>ERROR: Invalid student !</b></font>";
		System.err.println("input:"+input.toUpperCase());
		System.err.println(input.toUpperCase().startsWith("C"));
	if(input.toUpperCase().startsWith("C")){
	     ret = "<table border bgcolor='yellow' width='100%'><tr><td colspan='2'>Marks obtained by "+input.toUpperCase()+"</td></tr><tr><td>Subject</td><td>Marks</td></tr><tr><td>Maths</td><td>"+maths+"%</td></tr><tr><td>English</td><td>"+english+"%</td></tr><tr><td>Science</td><td>"+science+"%</td></tr></table>";
		
		}
	return ret;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
