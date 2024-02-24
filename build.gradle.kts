plugins {
    java
    war
    application
}

group = "org.example"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.servlet:javax.servlet-api:3.1.0")
    implementation("com.github.spullara.mustache.java:compiler:0.9.10")
    implementation("org.flywaydb:flyway-core:7.7.3")
    implementation("com.zaxxer:HikariCP:3.4.5")
    implementation("mysql:mysql-connector-java:8.0.26")
    implementation("org.eclipse.jetty:jetty-servlet:9.4.31.v20200723")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
