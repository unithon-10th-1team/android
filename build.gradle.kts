// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
}
buildscript{
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
//        classpath("com.android.tools.build:gradle:7.2.2")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
//        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
    }
}
true // Needed to make the Suppress annotation work for the plugins block