object Dependencies {
    object Version {
        const val core = "1.12.0"
        const val lifecycle = "2.7.0"
        const val activity = "1.8.2"
        const val compose_bom = "2023.08.00"
        const val navigation = "2.7.7"
        const val compose_ui = "1.6.2"
        const val coil = "2.6.0"
        const val material3 = "1.2.0"
        const val view_model = "2.6.1"
        const val retrofit = "2.9.0"
        const val moshi = "1.15.1"
        const val okhttp = "4.12.0"
        const val timber = "4.7.1"
        const val paging = "3.2.1"
        const val paging_jetpack = "3.3.0-alpha05"
        const val room = "2.6.1"
        const val dagger = "2.51.1"
        const val dagger_navigation = "1.0.0"
        const val firebase = "33.1.0"
        const val services = "18.5.0"
        const val permission = "0.33.1-alpha"
        const val accompanist = "0.32.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Version.core}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val activity = "androidx.activity:activity-compose:${Version.activity}"
        const val compose_bom = "androidx.compose:compose-bom:${Version.compose_bom}"
        const val compose_ui = "androidx.compose.ui:ui:${Version.compose_ui}"
        const val compose_tool = "androidx.compose.ui:ui-tooling:${Version.compose_ui}"
        const val compose_ui_graphics = "androidx.compose.ui:ui-graphics:${Version.compose_ui}"
        const val compose_ui_tooling =
            "androidx.compose.ui:ui-tooling-preview:${Version.compose_ui}"
        const val material3 = "androidx.compose.material3:material3:${Version.material3}"
        const val navigation = "androidx.navigation:navigation-compose:${Version.navigation}"
        const val view_model =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.view_model}"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:${Version.coil}"
    }

    object OkHttp {
        const val okthhp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val converter = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
    }

    object Timber {
        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    }

    object Pager {
        const val paging_runtime = "androidx.paging:paging-runtime:${Version.paging}"
        const val paging = "androidx.paging:paging-compose:${Version.paging_jetpack}"
    }

    object Room {
        const val room_runtime = "androidx.room:room-runtime:${Version.room}"
        const val room_compiler = "androidx.room:room-compiler:${Version.room}"
        const val room_paging = "androidx.room:room-paging:${Version.room}"
        const val room = "androidx.room:room-ktx:${Version.room}"
    }

    object Dagger {
        const val hilt = "com.google.dagger:hilt-android:${Version.dagger}"
        const val compiler = "com.google.dagger:hilt-compiler:${Version.dagger}"
        const val navigation = "androidx.hilt:hilt-navigation-compose:${Version.dagger_navigation}"
    }

    object Firebase {
        const val platform = "com.google.firebase:firebase-bom:${Version.firebase}"
        const val messaging = "com.google.firebase:firebase-messaging"
        const val analytics = "com.google.firebase:firebase-analytics"
    }

    object Services {
        const val base = "com.google.android.gms:play-services-base:${Version.services}"
    }

    object Permissions {
        const val permissions =
            "com.google.accompanist:accompanist-permissions:${Version.permission}"
    }

    object Accompanist {
        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:${Version.accompanist}"
    }
}