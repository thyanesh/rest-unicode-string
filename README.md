REST Service for String registration and lookup for it with String ID(unicode sum)
======================================================================================

A simple REST Service for String registration and lookup for it with String ID(unicode sum)

Build and run
-------------

```bash
git clone https://github.com/thyanesh/rest-unicode-string.git
cd rest-unicode-string
mvn package
java -jar target/rest-unicode-string-0.1.0.jar
```

REST API
--------

* **Register a string**

REQUEST:
   */rest/save?inputString={inputString} POST*
example:
http://localhost:8080/rest/save?inputString="hello"

JSON RESPONSE:
{  
   "data":"1058 : \"hello\"",
   "message":"String & StringID Registered Succesfully",
   "status":"success"
}
   
* **Lookup for a String with its String ID**

REQUEST:
   */rest/lookUp/{stringId} GET*
example:
http://localhost:8080/rest/lookUp/1058
   
JSON RESPONSE:
{  
   "data":[  
      "1058:\"hello\""
   ],
   "message":"List of Matching Strings Retrieved Succesfully",
   "status":"success"
}

Documentation
-------------

* REST Service Controller
  * [RestController.java](https://github.com/thyanesh/rest-unicode-string/blob/master/src/main/java/org/thyanesh/paypal/assessment/rest/RestController.java)
   
   Executes incoming request and defines URL to service method mappings. All remote call are delegated to the archive service.
   
* Service
  * Interface: [IRestService.java](https://github.com/thyanesh/rest-unicode-string/blob/master/src/main/java/org/thyanesh/paypal/assessment/service/IRestService.java)
  * Implementation: [RestService.java](https://github.com/thyanesh/rest-unicode-string/blob/master/src/main/java/org/thyanesh/paypal/assessment/service/RestService.java)
   1.)String Register Service:
  - A service to save the input string along with its computed string id
  - String id is a number calculated as follows:
String id is a sum of each character's id
A character id is a sum of the current and previous character's Unicode code point
The character id of the first character in the string is the character's Unicode code point
If the current and previous characters are the same, the character id is the current character's Unicode code point
String id must be calculated without using a loop
Example:
"abc" => 97 + (97 + 98) + (98 + 99)
"abbc" => 97 + (97 + 98) + (98) + (98 + 99)
  - Computed StringId(Key) and inputString(Value) is persisted into data.properties file for future retrievals.
  2. StringID Lookup service
   This service accepts a stringId as parameter and return all registered strings that match with the stringId by loading registered string from the data.properties
   
* Unit Test Client
   
   [RestServiceClient.java](https://github.com/thyanesh/rest-unicode-string/blob/master/src/main/java/org/thyanesh/paypal/assessment/client/RestServiceClient.java)
   
   A JUNIT test client for testing the service end points programmatically.
