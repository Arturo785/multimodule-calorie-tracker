apply {
    from("$rootDir/compose-module.gradle")
}

/*additional ones that are used only by this module*/
dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))

    "implementation"(Coil.coilCompose)
}