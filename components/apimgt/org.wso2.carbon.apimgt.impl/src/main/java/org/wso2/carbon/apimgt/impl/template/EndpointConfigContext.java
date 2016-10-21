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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.model.API;
import org.wso2.carbon.apimgt.api.model.Endpoint;
import org.wso2.carbon.apimgt.impl.APIConstants;

/**
 * Set endpoint config in context
 */
public class EndpointConfigContext extends ConfigContextDecorator {

    private API api;
    private JSONObject endpoint_config;
    protected Log log = LogFactory.getLog(getClass());
    public EndpointConfigContext(ConfigContext context, API api) {
        super(context);
        this.api = api;
    }

    @Override
    public void validate() throws APITemplateException, APIManagementException {
        super.validate();

        JSONParser parser = new JSONParser();
        //check if endpoint config exists
        String config_json = api.getEndpointConfig();

        if (config_json != null && !"".equals(config_json)) {
            try {
                Object config = parser.parse(config_json);
                this.endpoint_config = (JSONObject) config;
                if(APIConstants.DEFINED.equalsIgnoreCase(api.getEndpointType())) {
                    //Production Endpoint config setting up
                    Endpoint prodEndpoint = api.getProductionEndpoint();
                    if (prodEndpoint != null) {
                        String productionEndpointString = prodEndpoint.getEndpointConfig();
                        if (!StringUtils.isEmpty(productionEndpointString)) {
                            Object productionConfigOb = parser.parse(productionEndpointString);
                            JSONObject productionEndpoint = (JSONObject) productionConfigOb;
                            String productionEndpointTYpe = productionEndpoint.get(APIConstants.ENDPOINT_TYPE).toString();
                            //Default HTTP endpoint case
                            if (APIConstants.ENDPOINT_TYPE_HTTP.equalsIgnoreCase(productionEndpointTYpe)) {
                                this.endpoint_config.put(APIConstants.ENDPOINT_TYPE, APIConstants.ENDPOINT_TYPE_HTTP);
                                this.endpoint_config.put(APIConstants.PRODUCTION_ENDPOINTS, productionEndpoint.get(APIConstants.ENDPOINTS));
                            } else if (APIConstants.ENDPOINT_TYPE_LOAD_BALANCE.equalsIgnoreCase(productionEndpointTYpe)) {
                                this.endpoint_config.put(APIConstants.ENDPOINT_TYPE, APIConstants.ENDPOINT_TYPE_LOAD_BALANCE);
                                this.endpoint_config.put(APIConstants.PRODUCTION_ENDPOINTS, productionEndpoint.get(APIConstants.ENDPOINTS));
                                this.endpoint_config.put(APIConstants.ALGO_COMBO, productionEndpoint.get(APIConstants.ALGO_COMBO));
                                this.endpoint_config.put(APIConstants.ALGO_CLASS_NAME, productionEndpoint.get(APIConstants.ALGO_CLASS_NAME));
                                this.endpoint_config.put(APIConstants.SESSION_MANAGEMENT, productionEndpoint.get(APIConstants.SESSION_MANAGEMENT));
                                this.endpoint_config.put(APIConstants.SESSION_TIME_OUT, productionEndpoint.get(APIConstants.SESSION_TIME_OUT));
                            } else if (APIConstants.ENDPOINT_TYPE_FAIL_OVER.equalsIgnoreCase(productionEndpointTYpe)) {
                                this.endpoint_config.put(APIConstants.ENDPOINT_TYPE, APIConstants.ENDPOINT_TYPE_FAIL_OVER);
                                this.endpoint_config.put(APIConstants.PRODUCTION_ENDPOINTS, productionEndpoint.get(APIConstants.ENDPOINTS));
                                this.endpoint_config.put(APIConstants.PRODUCTION_FAILOVERS, productionEndpoint.get(APIConstants.FAILOVERS));
                            } else {
                                this.endpoint_config.put(APIConstants.ENDPOINT_TYPE, APIConstants.ENDPOINT_TYPE_HTTP);
                                this.endpoint_config.put(APIConstants.PRODUCTION_ENDPOINTS, productionEndpoint.get(APIConstants.ENDPOINTS));
                            }
                        }
                        //Sandbox endpoint config setting up

                        Endpoint sandboxEndpoint = api.getProductionEndpoint();
                        if (sandboxEndpoint != null) {
                            String sandboxEndpointString = sandboxEndpoint.getEndpointConfig();
                            if (!StringUtils.isEmpty(sandboxEndpointString)) {
                                Object sandboxConfigOb = parser.parse(sandboxEndpointString);
                                JSONObject sandboxEndpointOb = (JSONObject) sandboxConfigOb;
                                String sandboxEndpointTYpe = sandboxEndpointOb.get(APIConstants.ENDPOINT_TYPE).toString();
                                //Default HTTP endpoint case
                                if (APIConstants.ENDPOINT_TYPE_HTTP.equalsIgnoreCase(sandboxEndpointTYpe)) {
                                    this.endpoint_config.put(APIConstants.SANDBOX_ENDPOINT_TYPE, APIConstants.ENDPOINT_TYPE_HTTP);
                                    this.endpoint_config.put(APIConstants.SANDBOX_ENDPOINTS, sandboxEndpointOb.get(APIConstants.ENDPOINTS));
                                } else if (APIConstants.ENDPOINT_TYPE_LOAD_BALANCE.equalsIgnoreCase(sandboxEndpointTYpe)) {
                                    this.endpoint_config.put(APIConstants.SANDBOX_ENDPOINT_TYPE, APIConstants.ENDPOINT_TYPE_LOAD_BALANCE);
                                    this.endpoint_config.put(APIConstants.SANDBOX_ENDPOINTS, sandboxEndpointOb.get(APIConstants.ENDPOINTS));
                                    this.endpoint_config.put(APIConstants.SANDBOX_ALGO_COMBO, sandboxEndpointOb.get(APIConstants.ALGO_COMBO));
                                    this.endpoint_config.put(APIConstants.SANDBOX_ALGO_CLASS_NAME, sandboxEndpointOb.get(APIConstants.ALGO_CLASS_NAME));
                                    this.endpoint_config.put(APIConstants.SANDBOX_SESSION_MANAGEMENT, sandboxEndpointOb.get(APIConstants.SESSION_MANAGEMENT));
                                    this.endpoint_config.put(APIConstants.SANDBOX_SESSION_TIME_OUT, sandboxEndpointOb.get(APIConstants.SESSION_TIME_OUT));
                                } else if (APIConstants.ENDPOINT_TYPE_FAIL_OVER.equalsIgnoreCase(sandboxEndpointTYpe)) {
                                    this.endpoint_config.put(APIConstants.SANDBOX_ENDPOINT_TYPE, APIConstants.ENDPOINT_TYPE_FAIL_OVER);
                                    this.endpoint_config.put(APIConstants.SANDBOX_ENDPOINTS, sandboxEndpointOb.get(APIConstants.ENDPOINTS));
                                    this.endpoint_config.put(APIConstants.SANDBOX_FAILOVERS, sandboxEndpointOb.get(APIConstants.FAILOVERS));
                                } else {
                                    this.endpoint_config.put(APIConstants.SANDBOX_ENDPOINT_TYPE, APIConstants.ENDPOINT_TYPE_HTTP);
                                    this.endpoint_config.put(APIConstants.SANDBOX_ENDPOINTS, sandboxEndpointOb.get(APIConstants.ENDPOINTS));
                                }
                            }
                        }

                    }
                }
                log.info("CONFIG" + endpoint_config.toString());
            } catch (ParseException e) {
                this.handleException("Unable to pass the endpoint JSON config", e);
            }
        }
    }

    public VelocityContext getContext() {
        VelocityContext context = super.getContext();

        context.put("endpoint_config", this.endpoint_config);
        if(APIConstants.DEFINED.equalsIgnoreCase(this.api.getEndpointType())) {
            context.put("definedEndpoint", Boolean.TRUE);
        } else {
            context.put("definedEndpoint", Boolean.FALSE);
        }
        return context;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
