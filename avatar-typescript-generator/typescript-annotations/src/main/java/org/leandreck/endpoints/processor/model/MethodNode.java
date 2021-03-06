/**
 * Copyright © 2016 Mathias Kowalzik (Mathias.Kowalzik@leandreck.org)
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
package org.leandreck.endpoints.processor.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 */
public class MethodNode {

    private final String name;
    private final String url;
    private final boolean ignored;
    private final TypeNode returnType;
    private final TypeNode requestBodyType;
    private final List<TypeNode> pathVariableTypes;
    private final List<TypeNode> queryParameterTypes;
    private final List<String> httpMethods;
    private final Set<TypeNode> types;
    private final List<TypeNode> methodParameterTypes;
    private final String doc;

    MethodNode(final String name) {
        this.name = name;
        this.url = "";
        this.ignored = true;
        this.returnType = null;
        this.httpMethods = Collections.emptyList();
        this.requestBodyType = null;
        this.pathVariableTypes = Collections.emptyList();
        this.queryParameterTypes = Collections.emptyList();
        this.methodParameterTypes = Collections.emptyList();
        this.types = collectTypes();
        this.doc = null;
    }

    MethodNode(final String name, final String url, final String doc, final List<String> httpMethods,
                      final TypeNode returnType, final TypeNode requestBodyType, final List<TypeNode> pathVariableTypes,
                      final List<TypeNode> queryParameterTypes) {
        this.name = name;
        this.ignored = false;
        this.url = url;
        this.returnType = returnType;
        this.httpMethods = httpMethods;
        this.requestBodyType = requestBodyType;
        this.pathVariableTypes = pathVariableTypes;
        this.queryParameterTypes = queryParameterTypes;
        this.methodParameterTypes = new ArrayList<>(pathVariableTypes.size() + queryParameterTypes.size());
        this.methodParameterTypes.addAll(pathVariableTypes);
        this.methodParameterTypes.addAll(queryParameterTypes);
        this.types = collectTypes();
        this.doc = doc;
    }

    private Set<TypeNode> collectTypes() {
        final List<TypeNode> nodes = new ArrayList<>();
        if (returnType != null) {
            nodes.add(returnType);
        }

        if (requestBodyType != null) {
            nodes.add(requestBodyType);
        }

        nodes.addAll(methodParameterTypes);

        return nodes.stream().flatMap(it -> it.getTypes().stream()).filter(TypeNode::isDeclaredComplexType).collect(toSet());
    }

    public TypeNode getReturnType() {
        return returnType;
    }

    public List<String> getHttpMethods() {
        return Collections.unmodifiableList(httpMethods);
    }

    public String getName() {
        return name;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public String getUrl() {
        return url;
    }

    public TypeNode getRequestBodyType() {
        return requestBodyType;
    }

    public Set<TypeNode> getTypes() {
        return Collections.unmodifiableSet(types);
    }

    public List<TypeNode> getPathVariableTypes() {
        return Collections.unmodifiableList(pathVariableTypes);
    }

    public List<TypeNode> getQueryParameterTypes() {
        return Collections.unmodifiableList(queryParameterTypes);
    }

    /**
     * Returns the combined list of {@link #getPathVariableTypes()} and {@link #getQueryParameterTypes()}
     * @return All RequestParam and PathVariable {@link TypeNode}s which are parameters to this MethodNode.
     */
    public List<TypeNode> getMethodParameterTypes() {
        return Collections.unmodifiableList(methodParameterTypes);
    }

    /**
     * Returns the combined list of required {@link #getPathVariableTypes()} and {@link #getQueryParameterTypes()}
     * @return Required RequestParam and PathVariable {@link TypeNode}s which are parameters to this MethodNode.
     */
    public List<TypeNode> getRequiredMethodParameterTypes() {
        return Collections.unmodifiableList(methodParameterTypes.stream().filter(m -> !m.isOptional()).collect(Collectors.toList()));
    }

    /**
     * Returns the combined list of all optional {@link #getPathVariableTypes()} and {@link #getQueryParameterTypes()}
     * @return Optional RequestParam and PathVariable {@link TypeNode}s which are parameters to this MethodNode.
     */
    public List<TypeNode> getOptionalMethodParameterTypes() {
        return Collections.unmodifiableList(methodParameterTypes.stream().filter(TypeNode::isOptional).collect(Collectors.toList()));
    }

    /**
     * Returns the combined list of {@link #getRequestBodyType()}, {@link #getPathVariableTypes()} and {@link #getQueryParameterTypes()} ordered by {@link TypeNode#isOptional()}
     * so that all optional Parameters come after all required.<br>
     * Templates can safely use this Method to get all Parameters in proper order.
     *
     * @return All {@link TypeNode}s which are parameters to this MethodNode.
     */
    public List<TypeNode> getFunctionParameterTypes() {
        final List<TypeNode> functionParameters = Stream.concat(methodParameterTypes.stream(), Stream.of(requestBodyType))
                .filter(Objects::nonNull)
                .filter(it -> !it.isOptional())
                .collect(Collectors.toList());

        Stream.concat(Stream.of(requestBodyType), methodParameterTypes.stream())
                .filter(Objects::nonNull)
                .filter(TypeNode::isOptional)
                .forEach(functionParameters::add);

        return Collections.unmodifiableList(functionParameters);
    }

    /**
     * Documentation of this MethodNode, this is the pure content of the JavaDoc of the Java Method,
     * without any formatting characters or indentation.
     *
     * @return doc
     */
    public String getDoc() {
        return doc;
    }

	@Override
	public String toString() {
		return "MethodNode [name=" + name + ", url=" + url + ", ignored=" + ignored + ", returnType=" + returnType
				+ ", requestBodyType=" + requestBodyType + ", pathVariableTypes=" + pathVariableTypes
				+ ", queryParameterTypes=" + queryParameterTypes + ", httpMethods=" + httpMethods + ", types=" + types
				+ ", methodParameterTypes=" + methodParameterTypes + ", doc=" + doc + "]";
	}
}
