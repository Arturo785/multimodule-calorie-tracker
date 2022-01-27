apply {
    from("$rootDir/compose-module.gradle")
}

/*additional ones that are used only by this module*/
dependencies {
    "implementation"(project(Modules.core))
    /*the presentation needs the domain to show data*/
    "implementation"(project(Modules.onboardingDomain))
}