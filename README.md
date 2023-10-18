
# code structure
1. Maven default packaging structure is used for this project.  
2. Spring boot starter web, test and data jpa have been added as main dependencies.
3. All logic has been kept under productfeed package so that it would be easier to refer a specific feature.
4. JAXB maven plugin is used to generate Java classes from the provided xsd schema. Generated java code is kept directly under default maven package structure.
5. DB related configuration properties are kept in application.properties file
6. Lombok is used to take care of boilerplate code.
7. Apache common lang3 is added to provide additional util methods.
8. H2 Db is used for testing.
9. Jackson is used for object mapping and Jakarta is used for javax based binding functionalities.


# product-feed High level overview of codebase
1. I have built the requested service using JAXb as This is what I have previously used.
2. Layered architecture pattern here to build the service.
3. File upload code is written in scalable way to support other type of file uploads if needed in the future.
4. Spring boot data JPA is used along with mysql to support DB related tasks.
5. Exception handling is taken care using Spring controller advice
7. Junits are added for all classes except DTO and entities.

# How to run the Application
1. You can extract the zip file and execute below commands to run the application.
2. mvn clean install
3. mvn spring-boot:run
4. If you are using an IDE, you can first build the project and then run the application.

# curl

1. File upload  - curl --location 'http://localhost:8080/api/upload/xml' \
   --form 'file=@"{{filePath}}/product-sample.xml"'
2. Get Product details via product feed id - curl --location 'http://localhost:8080/api/feed/1'
# Scope of improvement
1. Profiling can be added to have local and higher environments.
2. JAXB based parsers load whole object in memory so it would be inefficient in case we get big files. In that scenario we can use STAX parser to validate and convert the xml to java objects.
3. We can also store the upload file and devise archive logic for the same so that if needed we can refer to the uploaded files.
