package webapp.framework.sse;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SSE dispatcher for one-to-one connections from Server to client-side
 * subscriber
 *
 * @author <a href="http://github.com/mariomac">Mario Macías</a>
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class EventTarget {

	private final AsyncContext mAsyncContext;
	private boolean mIsCompleted = false;
	/**
	 * Builds a new dispatcher from an {@link HttpServletRequest} object.
	 * 
	 * @param req
	 *            The {@link HttpServletRequest} reference, as sent by the
	 *            subscriber.
	 */
	public EventTarget(HttpServletRequest req) {
		mAsyncContext = req.startAsync();
		mAsyncContext.setTimeout(0);
		mAsyncContext.addListener(new AsyncListenerImpl());
	}

	/**
	 * If the connection is accepted, the server sends the 200 (OK) status
	 * message, plus the next HTTP headers:
	 * 
	 * <pre>
	 *     Content-type: text/event-stream;charset=utf-8
	 *     Cache-Control: no-cache
	 *     Connection: keep-alive
	 * </pre>
	 * 
	 * @return The same {@link EventTarget} object that received the method call
	 */

	public EventTarget ok() {
		final HttpServletResponse res = getAsyncResponse();
		res.setStatus(200);
		res.setContentType("text/event-stream");
		res.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Connection", "keep-alive");
		return this;
	}

	/**
	 * Responds to the client-side subscriber that the connection has been open
	 *
	 * @return The same {@link EventTarget} object that received the method call
	 * @throws IOException
	 *             if there was an error writing into the response's
	 *             {@link java.io.OutputStream}. This may be a common exception:
	 *             e.g. it will be thrown when the SSE subscriber closes the
	 *             connection
	 */

	public EventTarget open() throws IOException {
		final ServletOutputStream os = getAsyncResponseStream();
		os.print("event: open\n\n");
		os.flush();

		return this;
	}

	/**
	 * Sends a {@link MessageEvent} to the subscriber, containing only 'event'
	 * and 'data' fields.
	 * 
	 * @param event
	 *            The descriptor of the 'event' field.
	 * @param data
	 *            The content of the 'data' field.
	 * @return The same {@link EventTarget} object that received the method call
	 * @throws IOException
	 *             if there was an error writing into the response's
	 *             {@link java.io.OutputStream}. This may be a common exception:
	 *             e.g. it will be thrown when the SSE subscriber closes the
	 *             connection
	 */

	public EventTarget send(String event, String data) throws IOException {

		final ServletOutputStream os = getAsyncResponseStream();

		os.print(new MessageEvent.Builder().setData(data).setEvent(event).build().toString());
		os.flush();

		return this;
	}

	/**
	 * Sends a {@link MessageEvent} to the subscriber
	 * 
	 * @param messageEvent
	 *            The instance that encapsulates all the desired fields for the
	 *            {@link MessageEvent}
	 * @return The same {@link EventTarget} object that received the method call
	 * @throws IOException
	 *             if there was an error writing into the response's
	 *             {@link java.io.OutputStream}. This may be a common exception:
	 *             e.g. it will be thrown when the SSE subscriber closes the
	 *             connection
	 */

	public EventTarget send(MessageEvent messageEvent) throws IOException {
		final ServletOutputStream os = getAsyncResponseStream();
		os.print(messageEvent.toString());
		os.flush();
		return this;
	}

	private HttpServletResponse getAsyncResponse() {
		final HttpServletResponse res = (HttpServletResponse) mAsyncContext.getResponse();
		return res;

	}

	private ServletOutputStream getAsyncResponseStream() throws IOException {
		final HttpServletResponse res = (HttpServletResponse) mAsyncContext.getResponse();
		ServletOutputStream os = res.getOutputStream();
		return os;
	}



	/**
	 * Closes the connection between the server and the client.
	 */

	public void close() {
		if (!mIsCompleted) {
			mIsCompleted = true;
			mAsyncContext.complete();
		}
	}

	private class AsyncListenerImpl implements AsyncListener {
		@Override
		public void onComplete(AsyncEvent event) throws IOException {
			System.out.println("onComplete");
			mIsCompleted = true;
		}

		@Override
		public void onTimeout(AsyncEvent event) throws IOException {
		}

		@Override
		public void onError(AsyncEvent event) throws IOException {
		}

		@Override
		public void onStartAsync(AsyncEvent event) throws IOException {
		}
	}
}
