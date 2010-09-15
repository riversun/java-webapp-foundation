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
package webapp.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * Date Utility methods for java.sql.(Date/Time/Timestamp)
 *
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class DaoDateUtil {

	private final SimpleDateFormat mDateCompareSdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Returns Current time as java.sql.Timestamp
	 * 
	 * @return
	 */
	public Timestamp getNowAsTimestamp() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return ts;
	}

	/**
	 * Returns TODAY's start time as java.sql.Timestamp
	 * 
	 * @return
	 */
	public Timestamp getTodayAsTimestamp() {

		long todayTime = 0;
		try {
			todayTime = mDateCompareSdf.parse(mDateCompareSdf.format(new java.util.Date())).getTime();
		} catch (ParseException e) {
		}
		Timestamp ts = new Timestamp(todayTime);
		return ts;
	}

	/**
	 * Returns TODAY's date as java.sql.Date
	 * 
	 * @return
	 */
	public java.sql.Date getTodayAsSqlDate() {
		return new java.sql.Date(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * Returns java.sql.Date from Calendar object
	 * 
	 * @return
	 */
	public java.sql.Date getTodayAsSqlDate(Calendar cal) {
		return new java.sql.Date(cal.getTimeInMillis());
	}

	/**
	 * Get the Timestamp to show the N days after the "now"
	 * 
	 * @param numofShiftDays
	 * @return
	 */
	public java.sql.Date getTimeShiftedTimestampFromNow(Calendar calendar, int numofShiftDays)
	{

		final Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DAY_OF_MONTH, numofShiftDays);
		java.sql.Date sqlDate = new Date(cal.getTimeInMillis());
		return sqlDate;
	}

	/**
	 * To compare which is the latest day between today and timeStamp
	 * 
	 * @param timeStamp
	 * @return ret>0 means timestamp is after today<br>
	 *         ret==0 means timestamp is today<br>
	 *         ret<0 means timestamp is before today
	 */
	public long compareToTodayWith(Timestamp timeStamp) {

		try {
			long todayTime = mDateCompareSdf.parse(mDateCompareSdf.format(new java.util.Date())).getTime();
			long destTime = mDateCompareSdf.parse(mDateCompareSdf.format(new java.util.Date(timeStamp.getTime()))).getTime();

			return (destTime - todayTime);

		} catch (ParseException e) {
		}
		return 0;
	}

}
