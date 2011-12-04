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

public class Category {

	private TreeType treeType;
	
	public Category() {
		super();
	}

	public Category(TreeType treeType) {
		this.treeType = treeType;
	}

	public TreeType getTreeType() {
		return treeType;
	}

}
