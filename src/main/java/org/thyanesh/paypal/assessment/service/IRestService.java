package org.thyanesh.paypal.assessment.service;

/**
 * A service to save strings and find it back. 
 * 
 * 
 */
public interface IRestService {
    
    /**
     * Saves given string in to the File System as properties files.
     * 
     * @param inputString to be saved
     * @return Status of the save document
     */
    String save(String inputString);
    
    /**
     * Returns the list of all matching string with the given id.
     * Returns null if no matching string id was found.
     * 
     * @param id - sum of each character's id in the given string
     * @return A List of Matched String for input StringId 
     */
    String lookUp(String stringId);
}
