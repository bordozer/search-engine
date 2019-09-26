# Search engine

### Requirements
A simple search engine
Service works with small documents where each document contains a series of tokens (words) separated by spaces.

The usage model of the service:
1. Put documents into the search engine by key
2. Get document by key
3. Search on a string of tokens (words) to return keys of all documents that contain all tokens in the set


### Download
`````` 
$ git clone git@github.com:bordozer/search-engine.git
$ cd search-engine
``````

### Run
``````
$ ./deploy.sh
``````

### Test
Run Postman and import collection from *postman* dir in the project root.

There are some stored request there
- *Add new document* - creates new document with key 33
- *Get existing document* - get earlie created document with key 33
- *Get NOT existing document* - tries to get document with rey 777 that does not exists. Error is shown.
- *Search by tokens* - does search by list of tokens.

 ### Swagger
Swagger REST API
``````
http://localhost:8944//v2/api-docs
``````
Swagger UI
`````` 
http://localhost:8944/swagger-ui.html 
``````