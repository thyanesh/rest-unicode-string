package org.thyanesh.paypal.assessment.client;

import org.apache.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.thyanesh.paypal.assessment.service.IRestService;
import org.thyanesh.paypal.assessment.utils.CommonUtils;

public class RestServiceClient implements IRestService {

    private static final Logger LOG = Logger.getLogger(RestServiceClient.class);
    
    String protocol = CommonUtils.getPropertyForKey("protocol");
    String hostname = CommonUtils.getPropertyForKey("hostname");
    Integer port = Integer.parseInt(CommonUtils.getPropertyForKey("port"));
    String baseUrl = CommonUtils.getPropertyForKey("baseUrl");
    
    RestTemplate restTemplate;
    
    @Override
    public String save(String inputString) {
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    	params.add("inputString", inputString);
        return getRestTemplate().postForObject(getServiceUrl() + "save",params,String.class);
    }
    
    @Override
	public String lookUp(String stringId) {
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("stringId", stringId);
        return getRestTemplate().getForObject(getServiceUrl() + "lookUp/{stringId}",String.class,params);
	}

    public String getServiceUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProtocol()).append("://");
        sb.append(getHostname());
        if(getPort()!=null) {
            sb.append(":").append(getPort());
        }
        sb.append("/").append(getBaseUrl()).append("/");
        return sb.toString();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RestTemplate getRestTemplate() {
        if(restTemplate==null) {
            restTemplate = createRestTemplate(); 
        }
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate createRestTemplate() {
        restTemplate = new RestTemplate();
        return restTemplate;
    }

}
