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
package webapp.pages.view01_login;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.riversun.string_grabber.StringGrabber;

import webapp.def.CookieDef;
import webapp.def.PageDef;
import webapp.def.ServiceDef;
import webapp.pages.WebAppBaseController;

/**
 * 
 * To handle login auth and paging
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class LoginControllerImpl extends WebAppBaseController {

	private static final String QUERY_PARAM_PASSWORD = "password";
	private static final String QUERY_PARAM_USER_NAME = "user_name";

	private static final String REQUEST_KEY_COOKIE_PASSWORD = "cpassword";
	private static final String REQUEST_KEY_COOKIE_USERNAME = "cusername";

	// keys for request scope
	public static final String REQUEST_KEY_LOGIN_MESSAGE = "loginMessage";

	private static final String LOGIN_FAILURE_MESSAGE = "Incorrect username/passwod.";

	private static final Log LOG = LogFactory.getLog(LoginControllerImpl.class);

	@Override
	public void doService() throws ServletException, IOException {

		final HttpMethod method = getHttpMethod();

		switch (method) {
		case GET:
			doGet(request, response);
			break;
		case POST:
			doPost(request, response);
			break;
		case OTHER:
		default:
			break;
		}
	}

	/**
	 * To Handle GET method
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final LoginInfo loginInfo = (LoginInfo) session.getAttribute(ServiceDef.SESSION_KEY_LOGIN_INFO);

		if (loginInfo == null) {

			// retrieve batch execution info from DB

			requestScope("timeInfo", new Date());

			if (CookieDef.REMEMBER_USERNAME_AND_PASSWORD_ON_COOKIE) {

				requestScope(REQUEST_KEY_COOKIE_USERNAME, getSecureCookie(CookieDef.COOKIE_KEY_SRC_MYSERVICE_USER_NAME));
				requestScope(REQUEST_KEY_COOKIE_PASSWORD, getSecureCookie(CookieDef.COOKIE_KEY_SRC_MYSERVICE_PASSWORD));

			}

			final String fowardToPath = PageDef.JSP_PATH_LOGIN;

			// do dispatch
			dispatch(fowardToPath);

		} else {

			// forward to last visit path if last visit path not set, set top
			// page.
			String fowardToPath = (String) session.getAttribute(ServiceDef.SESSION_KEY_LAST_REQUEST_URL);

			if (fowardToPath == null || "".equals(fowardToPath)) {
				fowardToPath = PageDef.FIRST_PAGE_OF_LOGGED_IN_USER;
			}
			// do dispatch
			redirect(fowardToPath);
		}

	}

	/**
	 * To Handle POST method
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST method means generally login request by FORM-POST
		final String userName = asString(QUERY_PARAM_USER_NAME);
		final String password = asString(QUERY_PARAM_PASSWORD);

		final LoginInfo loginInfo = isUserPassCorrect(userName, password);

		if (loginInfo != null) {

			// --login success

			// set user-logged-in state to the session
			session.setAttribute(ServiceDef.SESSION_KEY_LOGIN_INFO, loginInfo);
			session.setAttribute(ServiceDef.SESSION_KEY_IS_USER_LOGGED_IN, Boolean.TRUE);

			// remove message of login failure
			request.setAttribute(REQUEST_KEY_LOGIN_MESSAGE, null);

			// Get redirect url from last memory
			final String lastRequestServletURL = (String) session.getAttribute(ServiceDef.SESSION_KEY_LAST_REQUEST_URL);

			String redirectToPath = lastRequestServletURL;

			if (redirectToPath == null || "".equals(redirectToPath)) {
				redirectToPath = PageDef.FIRST_PAGE_OF_LOGGED_IN_USER;
			}
			// Want to change the URL, to send a redirect
			redirect(redirectToPath);

		} else {

			// --login failure
			final LoginMessage loginErrorMessage = new LoginMessage();
			loginErrorMessage.isLoginFailure = true;
			loginErrorMessage.loginMessage = LOGIN_FAILURE_MESSAGE;

			// add message of login failure
			request.setAttribute(REQUEST_KEY_LOGIN_MESSAGE, loginErrorMessage);

			final String fowardToPath = PageDef.JSP_PATH_LOGIN;

			// do dispatch
			dispatch(fowardToPath);
		}

	}

	/**
	 * Check if userName and password are correct.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	private LoginInfo isUserPassCorrect(String userName, String password) {
		final LoginInfo loginInfo = new LoginInfo();

		if (LOG.isInfoEnabled())
			LOG.info("src=" + remoteHost() + " userName=" + userName + " password=" + password);

		final StringGrabber sgUserName = new StringGrabber(userName);
		final StringGrabber sgPassword = new StringGrabber(password);

		if (sgUserName.isEmpty() || sgPassword.isEmpty()) {

			if (LOG.isInfoEnabled())
				LOG.info("src=" + remoteHost() + "username or password is empty. login failure");

			return null;
		}

		/*
		 * Connect to the RDBMS and check if user id and password is correct.
		 */
		boolean isLoginSuccess = "guest".equals(userName) && "guest".equals(password);

		if (isLoginSuccess) {

			if (LOG.isInfoEnabled())
				LOG.info("userName=" + userName + " login success!");

			loginInfo.userName = userName;

			loginInfo.userId = "0";

			return loginInfo;
		}
		return null;
	}

}
