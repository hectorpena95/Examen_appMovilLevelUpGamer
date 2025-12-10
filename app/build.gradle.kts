plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.appmovillevelupgamer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appmovillevelupgamer"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    // *** IMPORTANTE PARA TESTS CON COROUTINES ***
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    // ===== ANDROIDX =====
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation("androidx.compose.material:material-icons-extended:1.6.0")

    // ===== COMPOSE =====
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)

    // ===== VIEWMODEL =====
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // ===== NAVEGACIÓN =====
    implementation(libs.navigation.compose)

    // ===== RETROFIT =====
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // ===== OKHTTP LOGGING =====
    implementation(libs.okhttp.logging)

    // ===== CORRUTINAS =====
    implementation(libs.coroutines.android)

    // ===== COIL =====
    implementation(libs.coil.compose)

    // ======================================================
    // 🧪 TEST UNITARIOS (100% CONFIGURADOS)
    // ======================================================

    // JUnit 4
    testImplementation(libs.junit4)

    // Mockito – versiones compatibles con Android
    testImplementation("org.mockito:mockito-core:4.11.0")
    testImplementation("org.mockito:mockito-inline:4.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")

// JUnit
    testImplementation("junit:junit:4.13.2")

// Core Testing (ViewModel)
    testImplementation("androidx.arch.core:core-testing:2.2.0")


    // Coroutines Test (muy importante)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // LiveData + ViewModel Testing
    testImplementation(libs.core.testing)

    // Android Framework Mocks
    testImplementation("org.robolectric:robolectric:4.12.2") // ← Opcional pero recomendado

    // ======================================================
    // 🧪 TESTS INSTRUMENTADOS (AndroidTest)
    // ======================================================
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.junit4)

    // Herramientas Debug
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    testImplementation(kotlin("test"))
}
