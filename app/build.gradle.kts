plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "com.cappasity.weatherapp"
        minSdk = 24
        targetSdk = 30
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        renderscriptTargetApi = 22
        renderscriptSupportModeEnabled = true
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }
    compileOptions {
//        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
//    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")


    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("androidx.multidex:multidex:2.0.1")

    // Dagger
    implementation("com.google.dagger:dagger:2.40.5")
    kapt("com.google.dagger:dagger-compiler:2.40.5")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.8.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

    // Room
    implementation("androidx.room:room-runtime:2.4.1")
    kapt("androidx.room:room-compiler:2.4.1")
    implementation("androidx.room:room-ktx:2.4.1")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0-alpha04")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0-alpha04")
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.0-alpha04")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

//    // Glide
//    implementation ("com.github.bumptech.glide:glide:4.13.0")
//    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.0")

    //view binding delegate
    implementation("com.github.kirich1409:viewbindingpropertydelegate:1.5.6")
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6")

    //swipe-layout
    implementation("com.github.NaikSoftware:accordion-swipe-layout:0.5.19")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
