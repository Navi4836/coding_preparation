plugins {
    /*id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"*/
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
   /* implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")*/
}

/*
tasks.test {
    useJUnitPlatform()
}*/
