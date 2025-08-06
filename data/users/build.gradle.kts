plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
}

android {
    namespace = "id.assessment.data.users"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":cores:database"))
    implementation(project(":cores:di"))
    implementation(project(":cores:network"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /* Hilt */
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation)

    /* Retrofit */
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    /* Moshi */
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.codegen)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}