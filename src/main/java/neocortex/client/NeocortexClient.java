/**
 * Copyright 2011 Alessandro Leite <alessandro.leite@alessandro.cc>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	String meaningfy() throws IOException;

}
