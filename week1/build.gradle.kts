plugins {
    id("java")
    id("application")
}

application {
    mainClass = "StartApplication"
}

group = "be.kdg.programming3"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

// add this line to be sure that it works the Scanner gradle
tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}