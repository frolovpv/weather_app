buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")//1.5.31
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0-alpha04")
    }
}

allprojects {
    repositories {
        maven("https://jitpack.io")
        google()
        mavenCentral()
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}