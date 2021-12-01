plugins {
    id("com.android.library")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.tagetSdk.get().toInt()

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.uiUtil)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.retrofit2)

    implementation(libs.google.material)
    implementation(libs.google.accompanist.insets)

    implementation(libs.coil.compose)

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtine)
    kapt(libs.androidx.room.compiler)

    implementation(libs.commons.codec)
}
