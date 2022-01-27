import org.gradle.kotlin.dsl.`kotlin-dsl`


// with this we imply that we will use gradle dsl as our main build
// it's supposed to be better than gradle ruby
repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}