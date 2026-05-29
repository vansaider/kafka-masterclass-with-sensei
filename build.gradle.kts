// Корневой build-файл — пока минимальный
allprojects {
    repositories {
        mavenCentral()
    }
}

// Пустой блок, чтобы Gradle не ругался
tasks.register("hello") {
    println("Kafka masterclass is ready")
}