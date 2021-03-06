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
package org.leandreck.endpoints.processor.printer;

import org.leandreck.endpoints.processor.model.EndpointNode;
import org.leandreck.endpoints.processor.model.TypeNode;

import java.util.Set;

/**
 */
public class TypesPackage {

    private final Set<EndpointNode> endpoints;
    private final Set<TypeNode> types;
    private final String apiModuleName;
    public TypesPackage(final Set<EndpointNode> endpoints, final Set<TypeNode> types, final String apiModuleName) {
        this.endpoints = endpoints;
        this.types = types;
        this.apiModuleName = apiModuleName;
    }

    public Set<EndpointNode> getEndpoints() {
        return endpoints;
    }

    public Set<TypeNode> getTypes() {
        return types;
    }
    
    public String getApiModuleName() {
    	return apiModuleName;
    }
}
