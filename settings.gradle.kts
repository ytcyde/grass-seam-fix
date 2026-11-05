pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        maven("https://maven.kikugie.dev/snapshots")
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.5"
}

stonecutter {
    create(rootProject) {
        versions("1.21.11")
        version("26.1.2", "26.1.2").buildscript("build.26.gradle.kts")
        version("26.2", "26.2").buildscript("build.26.gradle.kts")
        version("26.3", "26.3").buildscript("build.26.gradle.kts")
        vcsVersion = "1.21.11"
    }
}

rootProject.name = "grass-seam-fix"
