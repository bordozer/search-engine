# search-engine

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

 
