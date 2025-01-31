@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.paradise.flickspick"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.paradise.flickspick"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        viewBinding = true
//        dataBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.material3)
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.databinding.runtime)
    implementation(libs.logging.interceptor)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("androidx.core:core-splashscreen:1.0.0-beta02")
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
//    androidTestImplementation("com.google.dagger:hilt-android-testing:2.47")
//    kaptAndroidTest("com.google.dagger:hilt-compiler:2.47")
//    testImplementation("com.google.dagger:hilt-android-testing:2.47")
//    kaptTest("com.google.dagger:hilt-compiler:2.47")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("io.coil-kt:coil-compose:2.4.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

}