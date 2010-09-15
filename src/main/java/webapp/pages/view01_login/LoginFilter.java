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

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webapp.def.PageDef;
import webapp.def.ServiceDef;

/**
 * 
 * Login Filter<br>
 * Add Login Required URL into following urlPatterns<br>
 *
 * @author Tom Misawa (riversun.org@gmail.com)
 */
@WebFilter(filterName = "login-filter",
		urlPatterns = { PageDef.SERVLET_PATH_SECRET_PAGE, })
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		final boolean alreadyLoggedIn = checkIsAlreadyLoggedIn((HttpServletRequest) request, (HttpServletResponse) response);

		if (alreadyLoggedIn) {
			chain.doFilter(request, response);
		} else {
			// -- nothing.
			// response already commit by {#checkisAlreadyLoggedIn} method
		}

	}

	/**
	 * Check if user has been logged in.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private final boolean checkIsAlreadyLoggedIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final HttpSession session = request.getSession();

		final LoginInfo loginInfo =
				(LoginInfo) session.getAttribute(ServiceDef.SESSION_KEY_LOGIN_INFO);

		if (loginInfo == null) {

			// -- loginInfo null means use has not been logged in yet.

			// Remember servletPath(for forward dispatcher) and requestURL(for
			// redirect dispatcher)

			String url = request.getRequestURL().toString();
			String query = request.getQueryString();
			if (query != null && !"".equals(query)) {
				url += "?" + query;
			}

			session.setAttribute(ServiceDef.SESSION_KEY_LAST_REQUEST_SERVLET_PATH, request.getServletPath());
			session.setAttribute(ServiceDef.SESSION_KEY_LAST_REQUEST_URL, url);

			final String forwardToServletPath = PageDef.SERVLET_PATH_LOGIN;

			RequestDispatcher dispatch = request.getRequestDispatcher(forwardToServletPath);

			dispatch.forward(request, response);

			return false;
		} else {

			// -- user already logged in

			// call controller logic of this URL
			// doService(request, response);
			return true;
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}