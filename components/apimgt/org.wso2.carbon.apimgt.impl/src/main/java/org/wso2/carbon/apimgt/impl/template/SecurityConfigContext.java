/*
 *  Copyright WSO2 Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.apimgt.impl.template;

import org.apache.velocity.VelocityContext;
import org.wso2.carbon.apimgt.api.model.API;
import org.wso2.carbon.apimgt.api.model.Endpoint;
import org.wso2.carbon.apimgt.impl.APIConstants;
import org.wso2.carbon.apimgt.impl.internal.ServiceReferenceHolder;
import org.apache.commons.codec.binary.Base64;
/**
 * Set the parameters for secured endpoints
 */
public class SecurityConfigContext extends ConfigContextDecorator {

    private API api;

    public SecurityConfigContext(ConfigContext context,API api) {
        super(context);
        this.api = api;
    }

    public VelocityContext getContext() {
        VelocityContext context = super.getContext();

        String alias =  api.getId().getProviderName() + "--" + api.getId().getApiName()
                        + api.getId().getVersion();
        String sandboxAlias =  api.getId().getProviderName() + "--" + api.getId().getApiName()
                        + api.getId().getVersion() + "_" + APIConstants.SANDBOX;

        boolean isSecureVaultEnabled = Boolean.parseBoolean(ServiceReferenceHolder.getInstance().
                                                     getAPIManagerConfigurationService().getAPIManagerConfiguration().
                                                     getFirstProperty(APIConstants.API_SECUREVAULT_ENABLE));
        if(!APIConstants.DEFINED.equalsIgnoreCase(api.getEndpointType())) {
            String unpw = api.getEndpointUTUsername() + ":" + api.getEndpointUTPassword();
            context.put("isEndpointSecured", api.isEndpointSecured());
            context.put("isEndpointAuthDigest", api.isEndpointAuthDigest());
            context.put("username", api.getEndpointUTUsername());
            context.put("securevault_alias", alias);
            context.put("base64unpw", new String(Base64.encodeBase64(unpw.getBytes())));
            context.put("isSecureVaultEnabled", isSecureVaultEnabled);
        } else {
            Endpoint productionEndpoint = api.getProductionEndpoint();
            Endpoint sandboxEndpoint = api.getSandboxEndpoint();
            //Production endpoint related properties
            String productionPassword = "";
            char[] productionPasswordCharSeq = productionEndpoint.getEndpointPassword();
            if(productionPasswordCharSeq != null) {
                productionPassword = new String(productionPasswordCharSeq);
            }
            String unpw = productionEndpoint.getEndpointUsername() + ":" + productionPassword;
            context.put("isEndpointSecured", productionEndpoint.isEndpointSecured());
            boolean isDigestAuth = false;
            if(APIConstants.DIGEST_AUTH.equalsIgnoreCase(productionEndpoint.getAuthType())) {
                isDigestAuth = true;
            }
            context.put("isEndpointAuthDigest", isDigestAuth);
                context.put("username", productionEndpoint.getEndpointUsername());
            context.put("securevault_alias", alias);
            context.put("base64unpw", new String(Base64.encodeBase64(unpw.getBytes())));
            context.put("isSecureVaultEnabled", isSecureVaultEnabled);

            //sandbox endpoint related properties
            String sandboxPassword = "";
            char[] sandboxPasswordCharSeq = productionEndpoint.getEndpointPassword();
            if(sandboxPasswordCharSeq != null) {
                sandboxPassword = new String(sandboxPasswordCharSeq);
            }
            String unpwsandbox = sandboxEndpoint.getEndpointUsername() + ":" + sandboxPassword;
            context.put("isSandboxEndpointSecured", sandboxEndpoint.isEndpointSecured());
            boolean isSandboxDigestAuth = false;
            if(APIConstants.DIGEST_AUTH.equalsIgnoreCase(sandboxEndpoint.getAuthType())) {
                isSandboxDigestAuth = true;
            }
            context.put("isSandboxEndpointAuthDigest", isSandboxDigestAuth);
            context.put("sandboxUsername", productionEndpoint.getEndpointUsername());
            context.put("sandbox_securevault_alias", sandboxAlias);
            context.put("sandboxbase64unpw", new String(Base64.encodeBase64(unpwsandbox.getBytes())));
        }
        return context;
    }

}
