plugins {
    id(Plugins.Android.application)
    id(Plugins.Android.jetbrains)
}

android {
    namespace = "effective.office.marvelproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "effective.office.marvelproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.activity)
    implementation(platform(Dependencies.AndroidX.compose_bom))
    implementation(Dependencies.AndroidX.compose_ui)
    implementation(Dependencies.AndroidX.compose_ui_graphics)
    implementation(Dependencies.AndroidX.compose_ui_tooling)
    implementation(Dependencies.AndroidX.material3)
    implementation(Dependencies.AndroidX.navigation)
    implementation(Dependencies.Coil.coil)
    implementation(Dependencies.AndroidX.compose_tool)
}