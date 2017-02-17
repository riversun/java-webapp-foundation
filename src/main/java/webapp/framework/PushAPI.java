package webapp.framework;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import webapp.def.ServiceDef;
import webapp.framework.sse.EventTarget;
import webapp.framework.sse.SSEPushManager;
import webapp.pages.view01_login.LoginInfo;

public abstract class PushAPI extends WebAPI {

	private SSEPushManager mPushSender;

	void setPushManager(SSEPushManager pushSender) {
		mPushSender = pushSender;
	}

	@Override
	public void doService() throws ServletException, IOException {

		final HttpMethod method = getHttpMethod();

		if (HttpMethod.GET == method) {

			if (onSubscribeRequest()) {
				mPushSender.addTarget(new EventTarget(request));
			} else {

				response.sendError(HttpServletResponse.SC_FORBIDDEN, "You can not register push service.");

			}

		} else if (HttpMethod.POST == method) {

			onBroadcastRequest(mPushSender);

		} else {

			returnNothing();

		}

	}

	public List<EventTarget> getPushTargetList() {
		final List<EventTarget> targetList = mPushSender.getTargetList();
		return targetList;
	}

	public abstract boolean onSubscribeRequest();

	public abstract void onBroadcastRequest(SSEPushManager sender);

	// //////
	/**
	 * Returns userInfo of logged-in user
	 * 
	 * @return
	 */
	public LoginInfo getUserInfo() {

		final LoginInfo loginInfo =
				(LoginInfo) session.getAttribute(ServiceDef.SESSION_KEY_LOGIN_INFO);

		return loginInfo;
	}

	/**
	 * Returns is user already logged-in
	 * 
	 * @return
	 */
	public boolean isUserLoggedIn() {
		return getUserId() != null;
	}

	/**
	 * Returns logged-in user's userId
	 * 
	 * @return
	 */
	public String getUserId() {

		LoginInfo userInfo = getUserInfo();
		if (userInfo != null) {
			return userInfo.userId;
		} else {
			return null;
		}

	}

	protected String getRawUserName() {

		LoginInfo userInfo = getUserInfo();
		if (userInfo != null) {
			return userInfo.userName;
		} else {
			return null;
		}

	}

}
