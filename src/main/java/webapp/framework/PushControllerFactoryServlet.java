package webapp.framework;

import webapp.framework.sse.SSEPushManager;

@SuppressWarnings("serial")
public abstract class PushControllerFactoryServlet extends ControllerFactoryServlet {

	protected final SSEPushManager mPushSender = new SSEPushManager();

	@Override
	public final Class<? extends PushAPI> mapController() {
		return mapPushAPI();
	}

	@Override
	protected void configureControllerOnAccess(Controller controller) {
		super.configureControllerOnAccess(controller);

		final PushAPI pushAPI = (PushAPI) controller;
		pushAPI.setPushManager(mPushSender);

	}

	public abstract Class<? extends PushAPI> mapPushAPI();

}
