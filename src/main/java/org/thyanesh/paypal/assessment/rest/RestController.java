package org.thyanesh.paypal.assessment.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thyanesh.paypal.assessment.service.IRestService;

/**
 * REST web service for archive service {@link IRestService}.
 * 
 * /rest/save?file={file}&person={person}&date={date}  Add a document  POST
 *   file: A file posted in a multipart request
 *   person: The name of the uploading person
 *   date: The date of the document
 *   
 * /archive/documents?person={person}&date={date}           Find documents  GET
 *   person: The name of the uploading person
 *   date: The date of the document
 * 
 */
@Controller
@RequestMapping(value = "/rest")
public class RestController {

    private static final Logger LOG = Logger.getLogger(RestController.class);
    
    @Autowired
    IRestService restService;

    public IRestService getRestService() {
        return restService;
    }

    public void setRestService(IRestService restService) {
        this.restService = restService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,produces={"application/json; charset=UTF-8"})
   	public @ResponseBody String save( @RequestParam(value="inputString", required=true) String inputString) {
       	try {
               return getRestService().save(inputString);
           } catch (RuntimeException e) {
               LOG.error("Error while retrieving registering string service.", e);
               throw e;
           } catch (Exception e) {
               LOG.error("Error while retrieving registering string service.", e);
               throw new RuntimeException(e);
           }    
   	}
    
    @RequestMapping(value = "/lookUp/{stringId}", method = RequestMethod.GET,produces={"application/json; charset=UTF-8"})
   	public @ResponseBody String lookUp(@PathVariable String stringId, HttpServletResponse response) throws IOException {
    	try {
    		return getRestService().lookUp(stringId);
        } catch (RuntimeException e) {
            LOG.error("Error while invoking Lookup service", e);
            throw e;
        } catch (Exception e) {
            LOG.error("Error while invoking Lookup service", e);
            throw new RuntimeException(e);
        }    
    }
}
