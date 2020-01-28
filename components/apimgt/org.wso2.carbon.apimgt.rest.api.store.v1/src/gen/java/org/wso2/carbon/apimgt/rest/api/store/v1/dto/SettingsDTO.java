package org.wso2.carbon.apimgt.rest.api.store.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;

import javax.xml.bind.annotation.*;
import org.wso2.carbon.apimgt.rest.api.util.annotations.Scope;



public class SettingsDTO   {
  
    private List<String> grantTypes = new ArrayList<>();
    private List<String> scopes = new ArrayList<>();
    private Boolean applicationSharingEnabled = false;
    private Boolean mapExistingAuthApps = false;
    private String apiGatewayEndpoint = null;
    private Boolean enableEmailUsername = false;

  /**
   **/
  public SettingsDTO grantTypes(List<String> grantTypes) {
    this.grantTypes = grantTypes;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("grantTypes")
  public List<String> getGrantTypes() {
    return grantTypes;
  }
  public void setGrantTypes(List<String> grantTypes) {
    this.grantTypes = grantTypes;
  }

  /**
   **/
  public SettingsDTO scopes(List<String> scopes) {
    this.scopes = scopes;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("scopes")
  public List<String> getScopes() {
    return scopes;
  }
  public void setScopes(List<String> scopes) {
    this.scopes = scopes;
  }

  /**
   **/
  public SettingsDTO applicationSharingEnabled(Boolean applicationSharingEnabled) {
    this.applicationSharingEnabled = applicationSharingEnabled;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("applicationSharingEnabled")
  public Boolean isApplicationSharingEnabled() {
    return applicationSharingEnabled;
  }
  public void setApplicationSharingEnabled(Boolean applicationSharingEnabled) {
    this.applicationSharingEnabled = applicationSharingEnabled;
  }

  /**
   **/
  public SettingsDTO mapExistingAuthApps(Boolean mapExistingAuthApps) {
    this.mapExistingAuthApps = mapExistingAuthApps;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("mapExistingAuthApps")
  public Boolean isMapExistingAuthApps() {
    return mapExistingAuthApps;
  }
  public void setMapExistingAuthApps(Boolean mapExistingAuthApps) {
    this.mapExistingAuthApps = mapExistingAuthApps;
  }

  /**
   **/
  public SettingsDTO apiGatewayEndpoint(String apiGatewayEndpoint) {
    this.apiGatewayEndpoint = apiGatewayEndpoint;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("apiGatewayEndpoint")
  public String getApiGatewayEndpoint() {
    return apiGatewayEndpoint;
  }
  public void setApiGatewayEndpoint(String apiGatewayEndpoint) {
    this.apiGatewayEndpoint = apiGatewayEndpoint;
  }

  /**
   **/
  public SettingsDTO enableEmailUsername(Boolean enableEmailUsername) {
    this.enableEmailUsername = enableEmailUsername;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("enableEmailUsername")
  public Boolean isEnableEmailUsername() {
    return enableEmailUsername;
  }
  public void setEnableEmailUsername(Boolean enableEmailUsername) {
    this.enableEmailUsername = enableEmailUsername;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettingsDTO settings = (SettingsDTO) o;
    return Objects.equals(grantTypes, settings.grantTypes) &&
        Objects.equals(scopes, settings.scopes) &&
        Objects.equals(applicationSharingEnabled, settings.applicationSharingEnabled) &&
        Objects.equals(mapExistingAuthApps, settings.mapExistingAuthApps) &&
        Objects.equals(apiGatewayEndpoint, settings.apiGatewayEndpoint) &&
        Objects.equals(enableEmailUsername, settings.enableEmailUsername);
  }

  @Override
  public int hashCode() {
    return Objects.hash(grantTypes, scopes, applicationSharingEnabled, mapExistingAuthApps, apiGatewayEndpoint, enableEmailUsername);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettingsDTO {\n");
    
    sb.append("    grantTypes: ").append(toIndentedString(grantTypes)).append("\n");
    sb.append("    scopes: ").append(toIndentedString(scopes)).append("\n");
    sb.append("    applicationSharingEnabled: ").append(toIndentedString(applicationSharingEnabled)).append("\n");
    sb.append("    mapExistingAuthApps: ").append(toIndentedString(mapExistingAuthApps)).append("\n");
    sb.append("    apiGatewayEndpoint: ").append(toIndentedString(apiGatewayEndpoint)).append("\n");
    sb.append("    enableEmailUsername: ").append(toIndentedString(enableEmailUsername)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

