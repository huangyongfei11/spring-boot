/*
 * Copyright 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.cloudnativebuildpack.toml;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Very simple TOML markup builder.
 *
 * @author Phillip Webb
 * @since 2.3.0
 */
public class Toml {

	private final StringBuilder toml = new StringBuilder();

	public void table(String name) {
		append("[" + name + "]");
	}

	public void string(String name, String value) {
		append(name + " = " + quote(value));
	}

	public void array(String name, String... value) {
		if (value != null && value.length > 0) {
			append(name + " = " + Arrays.stream(value).map(this::quote).collect(Collectors.toList()));
		}
	}

	private void append(String line) {
		this.toml.append(line).append('\n');
	}

	private String quote(String string) {
		return "\"" + string + "\"";
	}

	@Override
	public String toString() {
		return this.toml.toString();
	}

}
