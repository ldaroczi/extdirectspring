##ExtDirectSpring 

ExtDirectSpring is a library that connects Ext JS 3.x, Ext JS 4.x and Sencha Touch 2 applications with a Java/Spring 3 back end, by implementing the [Ext Direct](http://www.sencha.com/products/js/direct.php) specification. 
The library supports all the features of Ext Direct:
  * Configuration with annotations
  * Simple remote calls
  * Named parameters
  * Method batching
  * Form post
  * Form post with file upload
  * Polling

###See the library in action: http://eds.rasc.ch/demo/

##Maven
ExtDirectSpring is available from the Central Maven Repository. 
```
    <dependency>
      <groupId>ch.ralscha</groupId>
      <artifactId>extdirectspring</artifactId>
      <version>1.1.3</version>
    </dependency>
```

There are two *Archetypes* available. Run the following commands in a shell.

Simple project with Spring and ExtDirectSpring: [Online](http://e4ds.rasc.ch/eds-starter-simple/) / [Sourcecode](https://github.com/ralscha/archetypes/tree/master/eds-starter-simple-app):
```
mvn archetype:generate -DarchetypeArtifactId=eds-starter-simple-archetype -DarchetypeGroupId=ch.rasc -DarchetypeVersion=1.0.3 -DarchetypeRepository=http://rasc.ch/archetypes  -DgroupId=com.mycompany -DartifactId=mynewapp -Dversion=0.0.1
```

Complete project with Spring, ExtDirectSpring, Spring Security, JPA (Hibernate), Spring Data JPA and I18n: [Online](http://e4ds.rasc.ch/) / [Sourcecode](https://github.com/ralscha/e4ds-template): 
```
mvn archetype:generate -DarchetypeArtifactId=eds-starter-archetype -DarchetypeGroupId=ch.rasc -DarchetypeVersion=1.0.3 -DarchetypeRepository=http://rasc.ch/archetypes  -DgroupId=com.mycompany -DartifactId=mynewapp -Dversion=0.0.1
```

After the generate command is finished, change into the project directory, type `mvn tomcat7:run` and open a browser with the URL: `http://localhost:8080`


##Minimal Requirements
 * Spring 3.0.7+
 * Servlet 2.4+ Container
 * Java 1.5+
 * Ext JS 3+ 