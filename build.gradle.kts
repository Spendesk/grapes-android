import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(grapesLibs.plugins.android.application) apply false
    alias(grapesLibs.plugins.android.library) apply false
    alias(grapesLibs.plugins.kotlin.android) apply false
    alias(grapesLibs.plugins.kotlin.parcelize) apply false
    alias(grapesLibs.plugins.kotlin.jvm) apply false
    alias(grapesLibs.plugins.hilt) apply false
    alias(grapesLibs.plugins.firebase.appdistribution) apply false
    alias(grapesLibs.plugins.ksp) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
