// build.gradle.kts (Module: app) - FIXED VERSION
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.app_selfcare"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.app_selfcare"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Chart library - MPAndroidChart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // KHÔNG cần CircleImageView nữa - đã thay bằng ImageView thông thường
    // implementation("de.hdodenhof:circleimageview:3.1.0")
}

// ===== QUAN TRỌNG: Thêm vào cuối file =====
// Cần thêm repository cho MPAndroidChart