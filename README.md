# POC: Spring Flyway

It demonstrates how to run database migrations using Flyway, Spring Boot and Gradle.

We want to write SQL files with instructions to set up a database and run them systematically, according to our needs.
For example, if the application needs a table to store customers, those scripts should execute the statements
to create that table, constraints, index and so on. The execution of those scripts should be managed by Flyway.

Also, we want the option to validate scripts whenever we want, as long as everything is automated and documented
properly.

## How to run

| Description                    | Command                                             |
|:-------------------------------|:----------------------------------------------------|
| Run tests                      | `./gradlew test`                                    |
| Run database migrations        | `./gradlew flywayMigrate`                           |
| Provision database¹            | `docker-compose up --detach`                        |
| Destroy database¹              | `docker-compose down --volumes`                     |

> ¹Required for manual testing only, automated tests provision and destroy a database automatically. Must run
> inside `infrastructure` folder.

## Preview

Running database migrations:

```text
3 SQL migrations were detected but not run because they did not follow the filename convention.
If this is in error, enable debug logging or 'validateMigrationNaming' to fail fast and see a list of the invalid file names.
Database: jdbc:postgresql://localhost:5432/poc_spring_flyway (PostgreSQL 14.4)
Successfully validated 3 migrations (execution time 00:00.001s)
Creating Schema History table "public"."flyway_schema_history" ...
Current version of schema "public": << Empty Schema >>
Migrating schema "public" to version "1 - create country table"
Migrating schema "public" to version "2 - create customer table"
Migrating schema "public" to version "3 - insert countries"
Successfully applied 3 migrations to schema "public", now at version v3 (execution time 00:00.031s)
```

Running migrations on application startup/testing:

```text
20:40:26.746 [Test worker] INFO 🐳 [postgres:latest] - Container postgres:latest started in PT1.061560928S
20:40:26.752 [Test worker] DEBUG org.springframework.test.context.support.DependencyInjectionTestExecutionListener - Performing dependency injection for test context [[DefaultTestContext@1bc53649 testClass = ApplicationTest, testInstance = com.example.ApplicationTest@272ce069, testMethod = [null], testException = [null], mergedContextConfiguration = [MergedContextConfiguration@88d6f9b testClass = ApplicationTest, locations = '{}', classes = '{class com.example.Application}', contextInitializerClasses = '[]', activeProfiles = '{}', propertySourceLocations = '{}', propertySourceProperties = '{org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true}', contextCustomizers = set[org.springframework.boot.test.autoconfigure.actuate.metrics.MetricsExportContextCustomizerFactory$DisableMetricExportContextCustomizer@5c8eee0f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@67ab1c47, org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@5b444398, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@a776e, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@60d1a32f, org.springframework.test.context.support.DynamicPropertiesContextCustomizer@e65ed12b, org.springframework.boot.test.context.SpringBootTestArgs@1, org.springframework.boot.test.context.SpringBootTestWebEnvironment@7ac296f6], contextLoader = 'org.springframework.boot.test.context.SpringBootContextLoader', parent = [null]], attributes = map['org.springframework.test.context.event.ApplicationEventsTestExecutionListener.recordApplicationEvents' -> false]]].

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::             (v3.0.0-M3)

2022-06-30T20:40:26.907-03:00  INFO 5507 --- [    Test worker] com.example.ApplicationTest              : Starting ApplicationTest using Java 18.0.1.1 on pc with PID 5507 (started by lucas in /home/lucas/Downloads/poc-spring-flyway)
2022-06-30T20:40:26.908-03:00  INFO 5507 --- [    Test worker] com.example.ApplicationTest              : No active profile set, falling back to 1 default profile: "default"
2022-06-30T20:40:27.148-03:00  INFO 5507 --- [    Test worker] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2022-06-30T20:40:27.170-03:00  INFO 5507 --- [    Test worker] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 18 ms. Found 2 JPA repository interfaces.
2022-06-30T20:40:27.376-03:00  INFO 5507 --- [    Test worker] o.f.c.internal.license.VersionPrinter    : Flyway Community Edition 8.5.11 by Redgate
2022-06-30T20:40:27.376-03:00  INFO 5507 --- [    Test worker] o.f.c.internal.license.VersionPrinter    : See what's new here: https://flywaydb.org/documentation/learnmore/releaseNotes#8.5.11
2022-06-30T20:40:27.376-03:00  INFO 5507 --- [    Test worker] o.f.c.internal.license.VersionPrinter    : 
2022-06-30T20:40:27.379-03:00  INFO 5507 --- [    Test worker] o.f.c.i.resource.ResourceNameValidator   : 3 SQL migrations were detected but not run because they did not follow the filename convention.
2022-06-30T20:40:27.380-03:00  INFO 5507 --- [    Test worker] o.f.c.i.resource.ResourceNameValidator   : If this is in error, enable debug logging or 'validateMigrationNaming' to fail fast and see a list of the invalid file names.
2022-06-30T20:40:27.380-03:00  INFO 5507 --- [    Test worker] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-06-30T20:40:27.483-03:00  INFO 5507 --- [    Test worker] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@1426370c
2022-06-30T20:40:27.484-03:00  INFO 5507 --- [    Test worker] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2022-06-30T20:40:27.492-03:00  INFO 5507 --- [    Test worker] o.f.c.i.database.base.BaseDatabaseType   : Database: jdbc:postgresql://localhost:49154/test (PostgreSQL 14.4)
2022-06-30T20:40:27.507-03:00  INFO 5507 --- [    Test worker] o.f.core.internal.command.DbValidate     : Successfully validated 3 migrations (execution time 00:00.007s)
2022-06-30T20:40:27.514-03:00  INFO 5507 --- [    Test worker] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table "public"."flyway_schema_history" ...
2022-06-30T20:40:27.529-03:00  INFO 5507 --- [    Test worker] o.f.core.internal.command.DbMigrate      : Current version of schema "public": << Empty Schema >>
2022-06-30T20:40:27.532-03:00  INFO 5507 --- [    Test worker] o.f.core.internal.command.DbMigrate      : Migrating schema "public" to version "1 - create country table"
2022-06-30T20:40:27.543-03:00  INFO 5507 --- [    Test worker] o.f.core.internal.command.DbMigrate      : Migrating schema "public" to version "2 - create customer table"
2022-06-30T20:40:27.549-03:00  INFO 5507 --- [    Test worker] o.f.core.internal.command.DbMigrate      : Migrating schema "public" to version "3 - insert countries"
2022-06-30T20:40:27.553-03:00  INFO 5507 --- [    Test worker] o.f.core.internal.command.DbMigrate      : Successfully applied 3 migrations to schema "public", now at version v3 (execution time 00:00.025s)
2022-06-30T20:40:27.599-03:00  INFO 5507 --- [    Test worker] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2022-06-30T20:40:27.633-03:00  INFO 5507 --- [    Test worker] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.6.9.Final
2022-06-30T20:40:27.733-03:00  INFO 5507 --- [    Test worker] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2022-06-30T20:40:27.799-03:00  INFO 5507 --- [    Test worker] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.PostgreSQL10Dialect
2022-06-30T20:40:28.081-03:00  INFO 5507 --- [    Test worker] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2022-06-30T20:40:28.085-03:00  INFO 5507 --- [    Test worker] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2022-06-30T20:40:28.309-03:00  INFO 5507 --- [    Test worker] com.example.ApplicationTest              : Started ApplicationTest in 1.545 seconds (process running for 3.924)
2022-06-30T20:40:28.839-03:00  INFO 5507 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2022-06-30T20:40:28.840-03:00  INFO 5507 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2022-06-30T20:40:28.842-03:00  INFO 5507 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
```
