plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.coachticket"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.coachticket"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.jakewharton.picasso:picasso2-okhttp3-downloader:1.1.0")
    implementation ("com.google.code.gson:gson:2.10")

    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    //date picker
    implementation ("com.google.android.material:material:1.11.0-alpha03")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation ("org.parceler:parceler-api:1.1.12")
    annotationProcessor ("org.parceler:parceler:1.1.12")

    implementation ("androidx.cardview:cardview:1.0.0")



//    implementation ("android.arch.lifecycle:extensions:1.1.1")
//    implementation ("android.arch.lifecycle:viewmodel:1.1.1")
}