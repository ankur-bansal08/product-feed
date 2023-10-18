# product-feed
1. I have built the requested service using JAXb as This is what I have previously used.
2. I have used 3 layer architecture pattern here to build the service.
3. File upload code is written in scalable way to support other type of file uploads if needed in future.
4. I have used Spring boot data JPA along with mysql to support DB related tasks.
5. Exception handling is taken care using Spring controller advice
6. I have also used Lombok to take care of boilerplate code.
7. Junits are added for all classes except DTO and entities.  
8. H2 Db is used for testing.



# Scope of improvement
1. Profiling can be added to have local and higher environments.
2. JAXB based parsers load whole object in memory so it would be inefficient in case we get big files. In that scenario we can use STAX parser to validate and convert the xml to java objects.
3. We can also store the upload file and devise archive logic for the same so that if needed we can refer to the uploaded files.