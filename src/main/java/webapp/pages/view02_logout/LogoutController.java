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
package webapp.pages.view02_logout;

import javax.servlet.annotation.WebServlet;

import webapp.def.PageDef;
import webapp.framework.Controller;
import webapp.framework.ControllerFactoryServlet;

/**
 * Logout Controller servlet
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
@SuppressWarnings("serial")
@WebServlet(name = "Logout", urlPatterns = { PageDef.SERVLET_PATH_LOGOUT })
public class LogoutController extends ControllerFactoryServlet {

    @Override
    public Class<? extends Controller> mapController() {
        return LogoutControllerImpl.class;
    }

}
