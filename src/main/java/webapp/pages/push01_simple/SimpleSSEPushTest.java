package webapp.pages.push01_simple;

import webapp.pages.push01_simple.LiteHttpClient.LiteHttpClientException;

public class SimpleSSEPushTest {

	public static void main(String[] args) throws LiteHttpClientException {

		final String targetAddress = "http://localhost:8080/example/sse";

		LiteHttpClient lhc = new LiteHttpClient();

		lhc.open("POST", targetAddress);

		lhc.send("my message");

	}
}
