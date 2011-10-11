package neocortex.builder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/**
 * 
 * @author Alessandro Leite
 */
@SuppressWarnings("deprecation")
public class RestUtility {

	private final static String DEFAULT_PROTOCOL = "http";
	private final static String SECURE_PROTOCOL = "https";

	protected static int BUFFER_SIZE = 2048;
	protected static HttpParams sParams;
	protected static ThreadSafeClientConnManager sManager;

	static {
		// sets up parameters
		sParams = new BasicHttpParams();
		HttpProtocolParams.setVersion(sParams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(sParams, "utf-8");
		sParams.setBooleanParameter("http.protocol.expect-continue", false);

		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme(DEFAULT_PROTOCOL, 80, PlainSocketFactory
				.getSocketFactory()));
		final SSLSocketFactory sslSocketFactory = SSLSocketFactory
				.getSocketFactory();
		sslSocketFactory
				.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		registry.register(new Scheme(SECURE_PROTOCOL, 443, sslSocketFactory));
		sManager = new ThreadSafeClientConnManager(registry);
	}

	private StringBuilder url;

	private HttpUriRequest request;
	private Object[] params;
	private HttpResponse response;

	public RestUtility with(String url, Object... params) {
		this.url = new StringBuilder(url);
		this.params = params;

		return this;
	}

	public RestUtility post() {
		this.request = new HttpPost(this.url.toString());

		if (this.params != null && this.params.length > 0) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			for (int i = 0; i < params.length; i += 2) {
				if (params[i + 1] != null) {
					nvps.add(new BasicNameValuePair("" + params[i], "" + params[i + 1]));
				}
			}
		}
		return this;
	}

	public RestUtility get() {

		if (params != null && params.length > 0) {
			this.url.append("&").append(encodeUrl(params));
		} else {
			this.url = new StringBuilder(this.url.toString());
		}

		this.request = new HttpGet(this.url.toString());
		return this;
	}

	public byte[] asByteArray() throws IOException {

		if (response == null) {
			this.execute();
		}

		HttpEntity ent = response.getEntity();
		byte[] content = null;
		if (ent != null) {
			content = IOUtils.toByteArray(ent.getContent());
			response.getEntity().consumeContent();
		}
		return content;
	}

	public String asText() throws IOException {
		if (this.response == null)
			this.response = execute();
		return this.read();
	}

	private String read() throws IOException {
		return new String(this.asByteArray());
	}

	private String encodeUrl(Object[] params) {
		String result = "";
		try {
			boolean firstTime = true;
			for (int i = 0; i < params.length; i += 2) {
				if (params.length >= i + 2 && params[i + 1] != null) {
					if (firstTime) {
						firstTime = false;
					} else {
						result += "&";
					}
					result += URLEncoder.encode("" + params[i], "UTF-8") + "="
							+ URLEncoder.encode("" + params[i + 1], "UTF-8");
				}
			}

		} catch (UnsupportedEncodingException e) {
		}
		return result;
	}

	private HttpResponse execute() throws IOException {
		HttpClient client = new DefaultHttpClient();
		this.response = client.execute(this.request);
		return this.response;
	}
}