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
package webapp.framework;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.riversun.d6.core.D6Logger;

/**
 * Servlet to generate a controller per access (= 1 thread)<br>
 * For servlet's multi-threaded safety improvement, delegate the service logic
 * to controller class.
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public abstract class ControllerFactoryServlet extends HttpServlet {

	private static final long serialVersionUID = 3916142351040599832L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		if (false) {
			D6Logger.setLogging(true);
		}
	}

	/**
	 * Create concrete controller thread by thread<br>
	 * 
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Class<? extends Controller> clazz = mapController();

		// For servlet's multi-threaded safety improvement,delegate the service
		// logic to controller class.
		// create Controller instance servlet#service by servlet#service
		try {

			final Controller controller = clazz.newInstance();

			// set req/res
			controller.request = request;
			controller.response = response;
			controller.session = request.getSession();

			controller.prepareDoService();

			// invoke controller logic
			controller.doService();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Map controller class for this servlet
	 * 
	 * @return
	 */
	public abstract Class<? extends Controller> mapController();

	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
