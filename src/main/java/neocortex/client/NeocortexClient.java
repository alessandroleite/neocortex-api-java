package neocortex.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public interface NeocortexClient {

	/**
	 * 
	 * @param url
	 * @return
	 */
	NeocortexClient input(URL url);

	/**
	 * 
	 * @param value
	 * @return
	 */
	NeocortexClient input(String value) throws MalformedURLException;

	/**
	 * Configures the {@link Category}
	 * 
	 * @param category
	 *            Category for your application
	 * @return the {@link NeocortexClient} instance for method chaining
	 */
	NeocortexClient categories(Category category);

	/**
	 * Configures to include entities in result.
	 * 
	 * @return the {@link NeocortexClient} instance for method chaining
	 */
	NeocortexClient entities();

	/**
	 * Configures to include keywords in result of input.
	 * 
	 * @return the {@link NeocortexClient} instance for method chaining
	 */
	NeocortexClient keywords();

	/**
	 * Configures to include language in result.
	 * 
	 * @return the {@link NeocortexClient} instance for method chaining
	 */
	NeocortexClient language();

	/**
	 * Configures the api key
	 * 
	 * @param apiKey
	 *            The API key for your application
	 * @return the {@link NeocortexClient} instance for method chaining
	 */
	NeocortexClient apiKey(String apiKey);

	/**
	 * Configure the result format
	 * 
	 * @param format
	 *            The format of output for your application.
	 * 
	 * @return the {@link NeocortexClient} instance for method chaining.
	 */
	NeocortexClient format(Format format);
	
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public Object get() throws IOException;

}
