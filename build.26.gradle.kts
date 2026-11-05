plugins {
    id("net.fabricmc.fabric-loom")
    id("dev.kikugie.stonecutter")
}

dependencies {
    "minecraft"("com.mojang:minecraft:${property("minecraft_version")}")
    "implementation"("net.fabricmc:fabric-loader:${property("loader_version")}")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))
