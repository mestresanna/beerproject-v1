# Beer Project
By Anna Mestres Casadesus, ASC202

## Domain

**Entities**
* **Beer** -> has a many-to-many relationship with Order.
* **Customer** -> has a one-to-many relationship with Order.
* **Order** -> has a many-to-one relationship with Customer and a many-to-many relationship with Beer.

To handle the relationship between Order and Beer using JPA, two additional classes have been created:
* **BeerOrder** -> represents the quantity of each Beer selected within an Order.
* **BeerOrderId** -> an embedded class for the IDs of Beer and Order.

**ENUMS** --> both are implemented in Beer class
* **Containers** 
* **Quantities**

---

## Profiles

* **collections** -> implements the Java Collections.
* **dev** -> uses the H2 database with `schema_dev.sql` and `data_dev.sql`. This works with JDBC.
* **jdbc** -> uses JDBC implementation.
* **prod** -> uses the PostgreSQL database with JPA. Data is uploaded initially using `data_prod.sql`.
* **jpa** -> implements the EntityManager JPA.
* **jpa_rep** -> uses the JPA Interface Repositories.

To switch between profiles, just need to decomment the one and comment the rest in application.properties. 
```
spring.profiles.active= collections  
spring.profiles.active= dev, jdbc  
spring.profiles.active= prod, jpa  
spring.profiles.active= prod, jpa_rep
````

---

## Database

Two databases are implemented:

### H2 Database

All settings are defined in the `application-dev.properties` file.

**url** = jdbc:h2:file:./db/beerdb  
**username** = sa  
**password** = sa


As said before, this database runs the schema_dev.sql and data_dev.sql to set it up at the beginning. 

To access to the console once running the dev and jdbc profile, this is the url; http://localhost:8080/h2-console

### PostgresQL

All settings are defined in the `application-prod.properties` file.

**url** = jdbc:postgresql://localhost:5432/beerdb_postgres  
**username** = postgres  
**password** = Student_1234  
**schema**= demo_orm

You can access to the database by intellij, by setting to url-only and selecting to the schema `demo_orm`.

This database runs the `data_prod.sql` for initial data. 
The DDL is set on create, to set it on update just change this line, and comment the initial data in `application-prod.properties`.
```
#Update DDL
spring.jpa.hibernate.ddl-auto=update
#For UPDATE DDL comment the next line
#spring.sql.init.data-locations=classpath:data_prod.sql
```

## Start Project

The project runs locally on port 8080. The home page URL is: 
http://localhost:8080/

## Parts Completed and Not completed
All weeks have been implemented in the final code, except for:

**Week 1:** The code for this week is available in GitLab as week1, but only partially implemented in the final code. The console application is missing.

**Week 5 - Second Language:** I added `message.properties` and `messages_ca.properties` and configured them in `application.properties`. However, there is an error: when implemented in HTML, it displays `??beer.header_ca??` in the browser. One line in the code is commented to demonstrate the error and how to implement messages in `beers.html`.

### Comments

**Week 6 - Session Scope**   
Initially, I implemented this feature using Session Parameters. Later, I realized it needed to be Session Scope. Both implementations are available in the code, and you can switch between them by uncommenting the respective methods.