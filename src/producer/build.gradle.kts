allprojects {
    repositories { mavenCentral() }
}

plugins {
    kotlin("jvm") version "2.4.0"
    application
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:4.3.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")
}

application {
    mainClass.set("producer.MetricsProducerKt")
}