plugins {
    id("dev.kikugie.stonecutter")
    id("fabric-loom") version "1.17-SNAPSHOT" apply false
}

stonecutter active "1.21.11" /* [SC] DO NOT EDIT */

tasks.register<Copy>("buildAll") {
    group = "build"
    description = "Builds every Stonecutter version into build/libs."

    val versionsDir = file("versions")
    subprojects.filter { it.projectDir.parentFile == versionsDir }.forEach { versionProject ->
        dependsOn(versionProject.tasks.named("build"))
        from(versionProject.layout.buildDirectory.dir("libs")) {
            exclude("*-sources.jar")
        }
    }

    into(layout.buildDirectory.dir("libs"))
}
