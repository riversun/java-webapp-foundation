package webapp.framework.sse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class implements a one-to-many connection for broadcasting messages
 * across multiple subscribers.
 *
 * @author <a href="http://github.com/mariomac">Mario Macías</a>
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class SSEPushManager {

	protected List<EventTarget> mEventTargetList = new CopyOnWriteArrayList<EventTarget>();

	public List<EventTarget> getTargetList() {
		return mEventTargetList;
	}

	/**
	 * <p>
	 * Adds a target from a <code>connectionRequest</code> that contains the
	 * information to allow sending back information to the subsbriber (e.g. an
	 * </p>
	 *
	 * @param eventTarget
	 *            an event target to be subscribed to the broadcast messages
	 *
	 * @throws IOException
	 *             if there was an error during the acknowledge process between
	 *             broadcaster and subscriber
	 */
	public void addTarget(EventTarget eventTarget) throws IOException {

		mEventTargetList.add(eventTarget.ok().open());

	}

	/**
	 * <p>
	 * Adds a target to the broadcaster from a
	 * </p>
	 *
	 * @param eventTarget
	 *            an event target to be subscribed to the broadcast messages
	 * @param welcomeMessage
	 *            The welcome message
	 * @throws IOException
	 *             if there was an error during the acknowledge process between
	 *             broadcaster and subscriber, or if the subscriber immediately
	 *             closed the connection before receiving the welcome message
	 */
	public void addTarget(EventTarget eventTarget, MessageEvent welcomeMessage) throws IOException {
		mEventTargetList.add(eventTarget.ok().open().send(welcomeMessage));
	}

	/**
	 * <p>
	 * Broadcasts a {@link MessageEvent} to all the subscribers, containing only
	 * 'event' and 'data' fields.
	 * </p>
	 *
	 * <p>
	 * This method relies on the {@link EventTarget#send(MessageEvent)} method.
	 * If this method throws an {@link IOException}, the broadcaster assumes the
	 * subscriber went offline and silently detaches it from the collection of
	 * subscribers.
	 * </p>
	 *
	 * @param event
	 *            The descriptor of the 'event' field.
	 * @param data
	 *            The content of the 'data' field.
	 */
	public List<EventTarget> broadcast(String event, String data) {
		return broadcast(new MessageEvent.Builder()
				.setEvent(event)
				.setData(data)
				.build());
	}

	/**
	 * Broadcast message to targets
	 * 
	 * @param messageEvent
	 * @return target that lives now.
	 */
	public List<EventTarget> broadcast(MessageEvent messageEvent) {

		for (EventTarget eventTarget : mEventTargetList) {
			try {

				eventTarget.send(messageEvent);
			} catch (IOException e) {
				// This target is disconnected. Removing from targetList
				e.printStackTrace();
				mEventTargetList.remove(eventTarget);
			}
		}
		return mEventTargetList;
	}

	/**
	 * Clear(=forget) targets<br>
	 * Closes all the connections between the broadcaster and the subscribers,
	 * and detaches all of them from the collection of subscribers.
	 */
	public void clearTargets() {

		for (EventTarget eventTarget : mEventTargetList) {
			try {
				eventTarget.close();
			} catch (Exception e) {
				// Uncontrolled exception when closing a dispatcher.
				// Removing anyway and ignoring.
			}
		}
		mEventTargetList.clear();

	}
}
