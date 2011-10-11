package neocortex.builder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import neocortex.client.Category;
import neocortex.client.Format;
import neocortex.client.GeneralKnowledge;
import neocortex.client.NeocortexClient;

import com.google.common.base.Preconditions;

/**
 * Implementation of the Builder pattern, with a fluent interface that creates a
 * 
 * @author Alessandro Leite
 */
public class ServiceBuilder implements NeocortexClient {

	private static final String API_URL_BASE = "http://api.meaningtool.com/0.2/neocortex";

	private String apiKey;

	private URL url;

	private Category category;

	private String input;

	private boolean entities;

	private boolean keywords;

	private boolean language;

	private Format format = Format.JSON;

	/**
	 * Default constructor
	 */
	public ServiceBuilder() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NeocortexClient apiKey(String apiKey) {
		this.apiKey = Preconditions.checkNotNull(apiKey);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NeocortexClient input(URL url) {
		this.url = Preconditions.checkNotNull(url);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws MalformedURLException
	 */
	@Override
	public NeocortexClient input(String value) {
		this.input = Preconditions.checkNotNull(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NeocortexClient categories(Category category) {
		this.category = category;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NeocortexClient entities() {
		this.entities = true;
		return this;
	}

	@Override
	public NeocortexClient keywords() {
		this.keywords = true;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NeocortexClient language() {
		this.language = true;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NeocortexClient format(Format format) {
		this.format = Preconditions.checkNotNull(format);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @throws IOException 
	 */
	@Override
	public String meaningfy() throws IOException {
		
		if (this.url != null) {
			this.input = this.url.toString();
		}
		return new RestUtility().with(this.buildUrl(), "input", this.getInput()).get().asText();
	}

	public String getInput() {
		return this.input;
	}

	protected String buildUrl() {
		Preconditions.checkNotNull(this.apiKey, "You must specify a valid api key the apiKey() method");
		Preconditions.checkArgument((this.url != null || this.input != null), "You must specify a valid URL or input text value throught the input() method");

		StringBuilder functions = new StringBuilder();
		StringBuilder keys = new StringBuilder();

		if (entities)
			functions.append("entities");

		if (keywords)
			functions.append(functions.length() > 0 ? ";" : "").append("keywords");

		if (this.language)
			functions.append(functions.length() > 0 ? ";" : "").append("language");

		if (category != null) {
			functions.append(functions.length() > 0 ? ";" : "").append("categories");
			
			if (category.getTreeType() != null)
				keys.append("tree_key=").append(this.category.getTreeType().getTreeKey());

			if (category.getTreeType() != null) {
				functions.append(category.getTreeType().isTopTerms() ?  "+top-terms": "");
				functions.append(category.getTreeType().isClassifiers() ? "+classifiers" : "");
			}
		}

		keys.append("&api_key=").append(this.apiKey);

		StringBuilder url = new StringBuilder(API_URL_BASE);
		url.append(functions.length() > 0 ? "/" + functions : "").append(".").append(this.format.name().toLowerCase()).append("?");
		url.append(keys);

		return url.toString();
	}
}