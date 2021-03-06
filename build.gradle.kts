import java.util.Properties

plugins {
    id("org.springframework.boot") version "3.0.0-M3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.flywaydb.flyway") version "8.5.13"
    java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

configurations {
    compileOnly {
        extendsFrom(annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/milestone")
}

ext {
    set("testcontainersVersion", "1.17.2")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.flywaydb:flyway-core")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.assertj:assertj-core")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${ext["testcontainersVersion"]}")
    }
}

tasks.test {
    useJUnitPlatform()
}

flyway {
    val resourcesPath = "src/main/resources"
    val applicationPropertiesFile = file(resourcesPath).resolve("application.properties")
    val applicationProperties = Properties()
    applicationProperties.load(applicationPropertiesFile.reader())

    url = applicationProperties["spring.datasource.url"].toString()
    user = applicationProperties["spring.datasource.username"].toString()
    password = applicationProperties["spring.datasource.password"].toString()
}