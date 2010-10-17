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
package webapp.pages.api01_simple;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import webapp.framework.Controller;
import webapp.framework.ControllerFactoryServlet;
import webapp.framework.WebAPI;

/**
 * Web API Example
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
@SuppressWarnings("serial")
@WebServlet(name = "datetime", urlPatterns = { "/" + "datetime" })
public class DateTimeAPI extends ControllerFactoryServlet {

	@Override
	public Class<? extends Controller> mapController() {
		return DateTimeAPIImpl.class;
	}

	/**
	 * 
	 * Model class for API
	 *
	 */
	public static class DataTimeResponse {
		public long id;
		public String query;
		public String value;

	}

	/**
	 * You can call this API like below.
	 * 
	 * http://localhost:8080/example/datetime?tellme=date&fmt=xml
	 *
	 */
	public static class DateTimeAPIImpl extends WebAPI {

		private static final Log LOG = LogFactory.getLog(DateTimeAPIImpl.class);

		@Override
		public void doService() throws ServletException, IOException {

			// get query param as string
			final String tellme = asString("tellme");
			final String fmt = asString("fmt");

			final String callback = asString("callback");

			boolean requireJSONP = (callback != null && !callback.isEmpty());

			final DataTimeResponse res = new DataTimeResponse();

			res.id = System.currentTimeMillis();
			res.query = tellme;

			if ("date".equals(tellme)) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				res.value = df.format(new Date());
			} else if ("time".equals(tellme)) {
				DateFormat df = new SimpleDateFormat("HH:mm:ss");
				res.value = df.format(new Date());
			}
			else if ("datetime".equals(tellme)) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				res.value = df.format(new Date());
			} else {
				res.value = "Error: call me like ?tellme=date (options from date/time/datetime)";
			}

			if ("xml".equals(fmt)) {
				// XML response
				returnAsXML(res);
			} else {
				// JSON response

				if (requireJSONP) {
					// JSONP

					// Add "Access-Control-Allow-Origin" policy to HTTP header
					response.setHeader("Access-Control-Allow-Origin", "*");

					returnAsJSONP(callback, res);
				} else {
					// JSON
					returnAsJSON(res);
				}
			}

		}
	}

}
