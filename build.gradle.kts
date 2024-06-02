// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.Android.application) version "8.2.2" apply false
    id(Plugins.Android.jetbrains) version "1.9.0" apply false
    id(Plugins.Android.dagger) version "2.51.1" apply false
    id(Plugins.Android.ksp) version "1.9.0-1.0.13" apply false
    id(Plugins.Android.services) version "4.4.1" apply false
}