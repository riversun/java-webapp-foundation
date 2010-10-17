/*  "java-webapp-foundation"(https://github.com/riversun/java-webapp-foundation)
 * 
 *  Easy to develop a micro Web Apps and Web APIs with JSP/Servlet
 * 
 *  Copyright (c) 2006-2017 Tom Misawa, riversun.org@gmail.com
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a
 *  copy of this software and associated documentation files (the "Software"),
 *  to deal in the Software without restriction, including without limitation
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *  and/or sell copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *  DEALINGS IN THE SOFTWARE.
 *  
 */
package webapp.pages.push02_use_fw;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import webapp.framework.PushAPI;
import webapp.framework.PushControllerFactoryServlet;
import webapp.framework.annotation.CORS;
import webapp.framework.sse.EventTarget;
import webapp.framework.sse.SSEPushManager;

/**
 * PUSH API Example
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
@SuppressWarnings("serial")
@WebServlet(asyncSupported = true, name = "push_service", urlPatterns = { "/" + "push" })
public class SimplePushService extends PushControllerFactoryServlet {

	@Override
	public Class<? extends PushAPI> mapPushAPI() {

		return PushServiceImpl.class;
	}

	@CORS(allowFrom = "*", allowCredentials = true)
	public static class PushServiceImpl extends PushAPI {

		@Override
		public boolean onSubscribeRequest() {

			// String attr=request.getAttribute("foo");
			// String attr2=asString("foo");

			// if you want to check if user logged in.write like this.
			if (false) {
				if (isUserLoggedIn()) {
					return true;
				} else {
					System.err.println("User not logged in.");
					return false;
				}
			}

			// allow subscribe
			return true;

		}

		@Override
		public void onBroadcastRequest(SSEPushManager sender) {

			List<EventTarget> pushTargetList = getPushTargetList();

			final String message = asString("message");

			List<EventTarget> targetList = sender.broadcast("message", message);

		}

	}

}
