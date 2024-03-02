object Dependencies{
    object Version{
        const val core = "1.12.0"
        const val lifecycle = "2.7.0"
        const val activity = "1.8.2"
        const val compose_bom = "2023.08.00"
        const val navigation = "2.7.7"
        const val compose_ui = "1.6.2"
        const val coil = "2.6.0"
        const val material3 = "1.2.0"
    }

    object AndroidX{
        const val core = "androidx.core:core-ktx:${Version.core}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val activity = "androidx.activity:activity-compose:${Version.activity}"
        const val compose_bom = "androidx.compose:compose-bom:${Version.compose_bom}"
        const val compose_ui = "androidx.compose.ui:ui:${Version.compose_ui}"
        const val compose_tool = "androidx.compose.ui:ui-tooling:${Version.compose_ui}"
        const val compose_ui_graphics = "androidx.compose.ui:ui-graphics:${Version.compose_ui}"
        const val compose_ui_tooling = "androidx.compose.ui:ui-tooling-preview:${Version.compose_ui}"
        const val material3 = "androidx.compose.material3:material3:${Version.material3}"
        const val navigation = "androidx.navigation:navigation-compose:${Version.navigation}"
    }

    object Coil{
        const val coil = "io.coil-kt:coil-compose:${Version.coil}"
    }
}