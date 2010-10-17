package webapp.pages.sse02_api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.framework.sse.SSEPushManager;
import webapp.framework.sse.EventTarget;

@SuppressWarnings("serial")

@WebServlet(asyncSupported = true,name = "sse", urlPatterns = { "/" + "sse" })
public class SSEExampleServlet extends HttpServlet {

    SSEPushManager broadcaster = new SSEPushManager();

	// Attaches a subscriber
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        broadcaster.addTarget(new EventTarget(req));
    }

    // Broadcasts a message to all the subscribers
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        broadcaster.broadcast("message","broadcasting that I received a POST message from somebody");
    }      
}