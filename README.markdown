# Neocortex API Java Client

This is a library that implements [meaningtool's neocortex API][apidocs] in Java.

All API access begins with the creation of a ServiceBuilder object.

You must first get a builder:

ServiceBuilder builder = new ServiceBuilder();

Then, you can chain any api method, for example:

String payload = builder.apiKey("yourkey").categories(new GeneralKnowledge())
						.entities()
						.keywords().language().format(Format.XML)
						.input("<url> or your input text")
						.meaningfy()); 	 

[meaningtool]: http://www.meaningtool.com
[apidocs]: http://www.meaningtool.com/developers/api
[treedirectory]: http://www.meaningtool.com/developers/directory

