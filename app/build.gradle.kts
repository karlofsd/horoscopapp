plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.horoscopapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.horoscopapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.horoscopapp.CustomTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "env_app_name","HoroscopApp")
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }
        debug {
            isDebuggable = true
            resValue("string", "env_app_name","[DEBUG] HoroscopApp")
            buildConfigField("String", "BASE_URL", "\"https://newastro-debug.vercel.app/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    kotlin {
        jvmToolchain(8)
    }

}

dependencies {

    val navVersion = "2.7.1"
    val retrofitVersion = "2.9.0"
    val daggerVersion = "2.48"
    val cameraVersion = "1.3.1"

    implementation("com.squareup.okhttp3:logging-interceptor:4.3.1")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerVersion")
    implementation("androidx.camera:camera-core:$cameraVersion")
    implementation("androidx.camera:camera-camera2:$cameraVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraVersion")
    implementation("androidx.camera:camera-view:$cameraVersion")
    implementation("androidx.camera:camera-extensions:$cameraVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation ("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    testImplementation ("io.mockk:mockk:1.12.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.4.0")
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.48")
    androidTestImplementation ("androidx.fragment:fragment-testing:1.6.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48")
}

kapt {
    correctErrorTypes = true
}