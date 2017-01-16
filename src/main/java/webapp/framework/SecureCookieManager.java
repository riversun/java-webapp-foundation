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

/**
 * 
 * SecureCookieManager
 *
 * @author Tom Misawa (riversun.org@gmail.com)
 */
class SecureCookieManager {

	private Controller mController;
	private final AesCrypter mCrypter = new AesCrypter();

	public SecureCookieManager(Controller controller) {
		mController = controller;
	}

	public void addSecureCookieAsEternal(String key, String value) {
		addSecureCookie(key, value, Integer.MAX_VALUE);
	}

	public void addSecureCookie(String key, String value, int maxAge) {
		mController.addCookie(
				encrypt(key),
				encrypt(value),
				maxAge);
	}

	public String getSecureCookieValue(String key) {
		final String encryptedCookieValue = mController.getCookieValue(encrypt(key));
		return decrypt(encryptedCookieValue);
	}

	private String decrypt(String encryptedText) {
		return mCrypter.decrypt(encryptedText);
	}

	private String encrypt(String str) {
		return mCrypter.encrypt(str);
	}

}
