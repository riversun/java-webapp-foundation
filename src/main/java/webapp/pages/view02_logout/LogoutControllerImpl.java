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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.def.PageDef;
import webapp.pages.UserStayController;

/**
 * 
 * To handle logout
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class LogoutControllerImpl extends UserStayController {

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

        session.invalidate();

        dispatch(PageDef.JSP_PATH_LOGOUT);

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
        doGet(request, response);
    }

}
