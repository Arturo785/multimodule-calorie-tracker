apply {
    from("$rootDir/base-module.gradle")
}

/*additional ones that are used only by this module*/
dependencies {
    "implementation"(project(Modules.core))
    "implementation"(Coroutines.coroutines)
}