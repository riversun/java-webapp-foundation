package webapp.framework;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;

/**
 * Base class for Web API(REST) <br>
 * Easy to handle XML/JSON request and XML/JSON response
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public abstract class WebAPI extends WebAppBaseController {

	private static final Logger LOGGER = Logger.getLogger(WebAPI.class.getName());

	@Override
	public boolean prepareDoService() throws ServletException, IOException {
		if (isPreFlightRequest()) {

			LOGGER.fine("preflight request");

			if (isCORSEnabled()) {
				returnNothing();
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns if crossOriginRequest
	 * 
	 * @return
	 */
	protected boolean isPreFlightRequest() {

		final HttpMethod httpMethod = getHttpMethod();
		return (HttpMethod.OPTIONS == httpMethod);

	}

	/**
	 * Returns whether CORS is enabled or not.
	 * 
	 * @return
	 */
	protected boolean isCORSEnabled() {

		// final HttpParam header =
		// response.getHeader("Access-Control-Allow-Origin");

		// gets the value of response header
		final String header = response.getHeader("Access-Control-Allow-Origin");

		return header != null;

	}

	/**
	 * Set CORS policy
	 * 
	 * @param value
	 *            specify like "*","https://xxxxxx.com"
	 */
	protected void setAccessControlAllowOrigin(String value) {
		response.addHeader("Access-Control-Allow-Origin", value);
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	}

	/**
	 * Enable Cookie while CORS connection
	 * 
	 * @param enabled
	 */
	protected void setAccessControlAllowCredentials(boolean enabled) {
		if (enabled) {
			response.addHeader("Access-Control-Allow-Credentials", String.valueOf(enabled));
		}
	}

}
