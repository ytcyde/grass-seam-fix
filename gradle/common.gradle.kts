val minecraftVersion = property("minecraft_version").toString()
val javaVersion = property("java_version").toString().toInt()
val minecraftDependency = property("minecraft_dependency").toString()

version = "${property("mod_version")}+$minecraftVersion"
group = property("maven_group").toString()

extensions.configure<BasePluginExtension> {
    archivesName.set(property("archives_base_name").toString())
}

extensions.configure<JavaPluginExtension> {
    withSourcesJar()
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(javaVersion)
    options.encoding = "UTF-8"
}

tasks.named<ProcessResources>("processResources") {
    val props = mapOf(
        "version" to project.version.toString(),
        "minecraft_dependency" to minecraftDependency,
        "java_version" to javaVersion.toString()
    )
    inputs.properties(props)
    filesMatching("fabric.mod.json") {
        expand(props)
    }
}

tasks.named<Jar>("jar") {
    from(rootProject.file("LICENSE"))
}
