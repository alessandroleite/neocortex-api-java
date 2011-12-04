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

public class TreeType {

	private String treeKey;

	private boolean topTerms;

	private boolean classifiers;

	public TreeType gadgetsTrends() {
		this.treeKey = "7fdc08c2-37e8-46a6-b8db-27e328d47320";
		return this;
	}

	public TreeType advertising() {
		this.treeKey = "def1d6b8-ac04-47de-8731-b35da631464f";
		return this;
	}

	public TreeType generalKnowledge() {
		this.treeKey = "b4678fa0-45ae-499d-8bc0-a0d3244a7d93";
		return this;
	}

	public TreeType topTerms() {
		this.topTerms = true;
		return this;
	}

	public TreeType classifiers() {
		this.classifiers = true;
		return this;
	}

	/**
	 * @return Get the tree key value
	 */
	public String getTreeKey() {
		return treeKey;
	}

	/**
	 * @return
	 */
	public boolean isClassifiers() {
		return classifiers;
	}

	/**
	 * @return
	 */
	public boolean isTopTerms() {
		return topTerms;
	}
}
