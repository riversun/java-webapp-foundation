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
package webapp.def;

/**
 * Constants for servlet/jsp page names
 *
 */
public class PageDef {

	// LOGIN
	public static final String SERVLET_PATH_LOGIN = "/" + "login.htm";
	public static final String JSP_PATH_LOGIN = "/" + "view_01_login.jsp";

	// LOGOUT
	public static final String SERVLET_PATH_LOGOUT = "/" + "logout.htm";
	public static final String JSP_PATH_LOGOUT = "/" + "view_02_logout_finished.jsp";

	// TOP PAGE
	public static final String TOP = "/" + "index.htm";
	public static final String TOP_JSP = "/" + "view_10_top_page.jsp";

	// SECRET PAGE
	public static final String SERVLET_PATH_SECRET_PAGE = "/" + "secret_page.htm";
	public static final String SECRET_PAGE_JSP = "/" + "view_20_secret_page.jsp";

	// FIRST PAGE after logged-in
	public static final String FIRST_PAGE_OF_LOGGED_IN_USER = TOP;

	// FILTER for COMMON filter
	public static final String COMMON_FILTER_PATTERN = "*.htm";

}
