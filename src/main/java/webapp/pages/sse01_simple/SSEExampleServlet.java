package webapp.pages.sse01_simple;

import info.macias.sse.EventBroadcast;
import info.macias.sse.servlet3.ServletEventTarget;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

@WebServlet(asyncSupported = true,name = "sse", urlPatterns = { "/" + "sse" })
public class SSEExampleServlet extends HttpServlet {

    EventBroadcast broadcaster = new EventBroadcast();

	// Attaches a subscriber
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        broadcaster.addSubscriber(new ServletEventTarget(req));
    }

    // Broadcasts a message to all the subscribers
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        broadcaster.broadcast("message","broadcasting that I received a POST message from somebody");
    }      
}