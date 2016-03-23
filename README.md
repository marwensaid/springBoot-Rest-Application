# Spring Boot REST Project 

c'est une application Java / Maven / Spring Boot qui peut être utilisé comme un démarreur pour créer un Microservice complet avec built-in health check, metrics et bien d'autre.
## Comment faire le RUN 

Cette application est packagé en war et posséde un Tomcat 7 embarqué. Aucune installation de Tomcat ou JBoss n'est obligatoire, Spring Boot possède un Tomcat embarqué. utilisez la commande ```java -jar```.

* Cloner le repository git
* Assurez vous d'utiliser JDK 1.8 et Maven 3.x
* Pour builder le projet et lancer les tests ```mvn clean package```
* Une fois le build passe bien, utilisez l'une de ces commandes pour lancer le service:
```
        java -jar -Dspring.profiles.active=test target/spring-boot-rest-example-0.2.0.war
ou
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Vérifier le stdout pour vous assurer qu'aucune des exceptions n'est levées

Une fois que l'application fonctionne, vous devriez voir quelque chose comme ça

```
2016-03-23 10:24:58.870  INFO 10190 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090/http
2016-03-23 10:24:58.872  INFO 10190 --- [           main] com.khoubyari.example.Application        : Started Application in 6.764 seconds (JVM running for 7.06)
```

## A propos du Service

Le service est tout simplement un service REST d'hôtel simple. Il utilise une base de données en mémoire pour stocker les données. Vous pouvez également faire avec une base de données relationnelle comme MySQL ou PostgreSQL. Si vos propriétés de connexion de base de données fonctionnent, vous pouvez appeler certains paramètres REST définis dans ```com.tprest.app.api.rest.hotelController``` sur le port **8090**. (voir ci-dessous)

Plus intéressant, vous pouvez commencer à appeler certains des paramètres opérationnels (voir la liste complète ci-dessous) comme ```/ metrics``` et``` / health``` (ceux-ci sont disponibles sur le port **8091**)
 
Voici ce que démontre cette application: 

* Construction d'une application avec une des dernière version de **Spring** Framework: inversion de controle et passer par l'injection de dépendance.
* Packaging en un seul war avec un Tomcat 7 embarqué: Pas besoin d'aucune utilisation ou installation d'un serveur à part, utilisez la commande ``java -jar``
* Démontre comment configurer healthcheck, metrics, info, environment, etc. paramètres automatiquement sur un port configuré. Injecter l'information health / metrics avec quelques lignes de code.
* Rédaction d'un service RESTful utilisant l'annotation: prend en charge XML et JSON en resquest/response.
* Exception mapping : utilisation des message HTTP
* *Spring Data* Intégration avec JPA/Hibernate avec quelques ligne de code et quelques annotation. 
* Fonctionnalité de CRUD against utilisant le pattern Spring *Repository*
* Démonstration avec les MockMVC et les lib associées
* toute les API sont "self-documented" avec Swagger

Voici quelques paramètres que vous pouvez appeler:

### Obtenir des informations sur le system health, configurations, etc.

```
http://localhost:8091/env
http://localhost:8091/health
http://localhost:8091/info
http://localhost:8091/metrics
```

### Créer une source d'hôtel

```
POST /example/v1/hotels
Accept: application/json
Content-Type: application/json

{
"name" : "Beds R Us",
"description" : "Very basic, small rooms but clean",
"city" : "Santa Ana",
"rating" : 2
}

RESPONSE: HTTP 201 (crée)
Location header: http://localhost:8090/example/v1/hotels/1
```

### Récupération d'une liste d'hôtels paginé

```
http://localhost:8090/example/v1/hotels?page=0&size=10

Response: HTTP 200
Content: paginated list 
```

### Update la ressource Hôtel

```
PUT /example/v1/hotels/1
Accept: application/json
Content-Type: application/json

{
"name" : "Beds R Us",
"description" : "Very basic, small rooms but clean",
"city" : "Santa Ana",
"rating" : 3
}

RESPONSE: HTTP 204 (No Content)
```

# A propos de Spring Boot

Spring Boot est un framework starter pour les application spring qui le rend facile de créer de nouveaux services RESTful (parmi d'autres types d'applications). Il fournit un grand nombre des installations habituelles de spring qui peuvent être configurés facilement habituellement sans XML. En plus de manipulation facile des contrôleurs Spring, Data Spring, etc. Spring Boot est livré avec le module d'actionneur qui donne à l'application des critères d'évaluation suivants utiles dans le suivi et l'exploitation du service:

**/metrics** voir “metrics” informations pour l'application en cours.

**/health** Afficher des informations sur le health de l'application.

**/info** Afficher arbitrairement des infos sur l'application.

**/configprops** Afficher une liste de tous les configuration : @ConfigurationProperties.

**/mappings** Afficher une liste de tous les chemins collationnées @RequestMapping.

**/beans** Afficher une liste de tous les Spring Beans dans l'application.

**/env** Expose les propriétés de ConfigurableEnvironment Spring.

**/trace** Afficher des informations de traçage (par défaut les quelques derniers requêtes HTTP).


# Run le projet avec Mysql

Ce projet utilise une base de données en mémoire afin que vous ne n'avez pas à installer une base de données pour l'exécuter. Toutefois, la conversion pour fonctionner avec une autre base de données relationnelle comme MySQL est très facile. Étant donné que le projet utilise Spring data et le Repository pattern, il est encore assez facile de sauvegarder le même service avec Couchbase ou MongoDB.

Voici ce que vous devez faire pour sauvegarder les services avec MySQL, par exemple: 

### dans pom.xml il faut ajouter: 

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

###  Ajoutez ceci à la fin de application.yml: 

```
---
spring:
  profiles: mysql

  datasource:
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://<your_mysql_host_or_ip>/bootexample
    username: <your_mysql_username>
    password: <your_mysql_password>

  jpa:
    hibernate:
      dialect: org.hibernate.dialect.MySQLInnoDBDialect
      ddl-auto: update # todo: in non-dev environments, comment this out:


hotel.service:
  name: 'test profile:'
```

### Run avec un profile 'mysql' :

```
        java -jar -Dspring.profiles.active=mysql target/spring-boot-rest-example-0.2.0.war
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=mysql"
```

# Fixation à l'application à distance à partir de votre IDE

Exécutez le service avec les options de ligne de commande:

```
mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```
Et puis vous pouvez vous connectez à distance à l'aide de votre IDE. Par exemple, à partir de IntelliJ Vous devez ajouter la configuration de débogage à distance: Modifier la configuration -> à distance.
# Pour les question : marwensaidi1@gmail.com





