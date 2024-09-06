import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id ("com.github.johnrengelman.shadow")
}

dependencies {
    implementation ("com.google.guava:guava")
    implementation ("org.slf4j:slf4j-api:2.0.13")
    implementation ("ch.qos.logback:logback-classic:1.4.5")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("otusJavaProfessionalHW")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.CalcDemo"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}