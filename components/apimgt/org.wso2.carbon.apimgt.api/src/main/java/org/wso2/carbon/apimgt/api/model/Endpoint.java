/*
*  Copyright (c) 2005-2011, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.apimgt.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Endpoint configutation holder class.
 *  */
@SuppressWarnings("unused")
public class Endpoint {
    private String name;
    private String description;
    private boolean endpointSecured;
    private String endPointAuthDigest;
    private String endpointConfig;
    private String endpointUsername;
    private char[] endpointPassword;
    private String[] visibleRoles;
    private String endpointType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEndpointSecured() {
        return endpointSecured;
    }

    public void setEndpointSecured(boolean endpointSecured) {
        this.endpointSecured = endpointSecured;
    }

    public String getEndPointAuthDigest() {
        return endPointAuthDigest;
    }

    public void setEndPointAuthDigest(String endPointAuthDigest) {
        this.endPointAuthDigest = endPointAuthDigest;
    }

    public String getEndpointConfig() {
        return endpointConfig;
    }

    public void setEndpointConfig(String endpointConfig) {
        this.endpointConfig = endpointConfig;
    }

    public String getEndpointUsername() {
        return endpointUsername;
    }

    public void setEndpointUsername(String endpointUsername) {
        this.endpointUsername = endpointUsername;
    }

    public char[] getEndpointPassword() {
        return endpointPassword;
    }

    public void setEndpointPassword(char[] endpointPassword) {
        this.endpointPassword = endpointPassword;
    }

    public String[] getVisibleRoles() {
        return visibleRoles;
    }

    public void setVisibleRoles(String[] visibleRoles) {
        this.visibleRoles = visibleRoles;
    }

    public String getEndpointType() {
        return endpointType;
    }

    public void setEndpointType(String endpointType) {
        this.endpointType = endpointType;
    }
}
