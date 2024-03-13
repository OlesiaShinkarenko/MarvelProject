import java.util.Properties


plugins {
    id(Plugins.Android.application)
    id(Plugins.Android.jetbrains)
    id(Plugins.Android.kapt)
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

        val keystoreFile = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        val apiKey = properties.getProperty("API_KEY") ?:""

        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = apiKey
        )
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
    buildFeatures {
        compose = true
        buildConfig = true
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
kapt {
    generateStubs = false
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
    implementation(Dependencies.AndroidX.view_model)


    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.converter)

    implementation(Dependencies.OkHttp.okthhp)
    implementation(Dependencies.OkHttp.interceptor)

    implementation(Dependencies.Moshi.moshi)
    kapt(Dependencies.Moshi.codegen)
}