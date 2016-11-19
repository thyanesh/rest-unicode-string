package org.thyanesh.paypal.assessment.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.thyanesh.paypal.assessment.utils.CommonUtils;

@Service("restService")
public class RestService implements IRestService, Serializable {

    private static final long serialVersionUID = 8119784722798361327L;
    
    private static final Logger LOG = Logger.getLogger(RestService.class);
    
	@Override
	public String save(String inputString) {
		Map<String,Object> response = new HashMap<String,Object>();
		LOG.info("Entering Register String Service");
		try {
			if(null!=inputString){
				int strId = computeStringID(inputString,0,0);
				Map<Integer, String> stringList = new HashMap<Integer, String>();
				stringList.put(strId, inputString);
				CommonUtils.saveProps(stringList);
				response.put("status", "success");
				response.put("data",strId+" : "+inputString);
				response.put("message", "String & StringID Registered Succesfully");
			}else{
				response.put("status", "failure");
				response.put("data", "No Input String");
				response.put("message", "User List Service Failed");
			}
		}  catch (Exception e) {
			response.put("status", "failure");
			response.put("data", null);
			response.put("message", "User List Service Failed");
			LOG.error(e);
		}
		LOG.info("Exiting Register String Service");
		return CommonUtils.convertJavaObjectToJsonString(response);
	}

	@Override
	public String lookUp(String stringId) {
		Map<String,Object> response = new HashMap<String,Object>();
		LOG.info("Entering StringID Lookup Service");
		try {
			Map<Integer, String> propStrings =CommonUtils.loadProps();
			List<String> stringList = new ArrayList<String>();
			if(null!=propStrings){
				for (Map.Entry<Integer,String> entry : propStrings.entrySet()) {
				    if(Integer.parseInt(stringId) == entry.getKey()){
				    	stringList.add(entry.getKey().toString()+":"+ entry.getValue());
				    }
				}	
			}
			if(!(stringList.isEmpty())){
				response.put("status", "success");
				response.put("data", stringList);
				response.put("message", "List of Matching Strings Retrieved Succesfully");
			}else{
				response.put("status", "failure");
				response.put("data", "No String Id Found"); 
				response.put("message", "No Matching Strings Found");
			}
		}  catch (Exception e) {
			response.put("status", "failure");
			response.put("data", null);
			response.put("message", "Lookup Service Failed");
			LOG.error(e);
		}
		LOG.info("Exiting StringID Lookup Service");
		return CommonUtils.convertJavaObjectToJsonString(response);
	}
	
	private static int computeStringID(String str, int pos,int unicodeSum){
		return recursiveUnicodeSum(str,pos,unicodeSum);
	}
	
	private static int recursiveUnicodeSum(String str, int pos,int unicodeSum){
		if(pos < str.length()){
		    if(pos==0){
		    	unicodeSum = unicodeSum+(int)str.charAt(pos);
		    }else{
		    	if(str.charAt(pos-1)!= str.charAt(pos))
		    		unicodeSum = unicodeSum+((int)str.charAt(pos-1) + (int)str.charAt(pos));
		    	if(str.charAt(pos-1)== str.charAt(pos))
		    		unicodeSum = unicodeSum+((int)str.charAt(pos));
		    }
		} 
		if(pos > str.length()){
			return unicodeSum;
		}
		return computeStringID(str,pos+1,unicodeSum);
	}
}
