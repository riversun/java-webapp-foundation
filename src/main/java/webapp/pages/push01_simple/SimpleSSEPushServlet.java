package webapp.pages.push01_simple;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.framework.sse.SSEPushManager;
import webapp.framework.sse.EventTarget;

/**
 * Simple example for PUSH notification by Server Sent Events<br>
 * Launcher page is "server_push_test_01.html"
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
@SuppressWarnings("serial")
@WebServlet(asyncSupported = true, name = "sse", urlPatterns = { "/" + "sse" })
public class SimpleSSEPushServlet extends HttpServlet {

	private final SSEPushManager mPushSender = new SSEPushManager();

	/**
	 * If you get thet get request from Java Script (SSE),register client into
	 * push target list.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		mPushSender.addTarget(new EventTarget(req));
	}

	/**
	 * If you get the post request from client,broadcast the message
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		mPushSender.broadcast("message", "This is a push message");
	}
}