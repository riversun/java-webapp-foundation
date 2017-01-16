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

import org.riversun.d6.DBConnInfo;

/**
 * My Web Application Database connection information<br>
 * <br>
 * Notice 1.<br>
 * If you want to create a new model, using JPA(like with hibernate-JPA-impl) is
 * strongly recommended. <br>
 * <br>
 * Notice 2.<br>
 * This implementation uses RAW connection for prototype usage. For production
 * ,use connection pooling like DBCP or hibernate or plugin-connection-pooling
 * mechanism on App server like Tomcat is recommended.
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class LegacyJDBCDef {
	public static final DBConnInfo DB_CONN_INFO = new DBConnInfo();

	// Example for mysql
	static {

		DB_CONN_INFO.DBDriver = "org.gjt.mm.mysql.Driver";

		/**
		 * If column contains like '0000-00-00 00:00:00' , JDBC throws
		 * java.sql.SQLException: Cannot convert value ’0000-00-00 00:00:00′.
		 * So, add '?zeroDateTimeBehavior=convertToNull' into the tail to
		 * prevent throwing above exception.
		 */
		DB_CONN_INFO.DBUrl = "jdbc:mysql://localhost:3306/xxxx?zeroDateTimeBehavior=convertToNull";
		DB_CONN_INFO.DBUser = "[your_username]";
		DB_CONN_INFO.DBPassword = "[your_password";
	}
}
