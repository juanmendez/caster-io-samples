import info.adavis.plugin.BumpReadMeVersionPlugin
import info.adavis.plugin.BumpReadMeVersionPlugin.Companion.BUMP_VERSION
import info.adavis.plugin.BumpVersion
import info.adavis.plugin.DisplayVersion

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}
apply<BumpReadMeVersionPlugin>()

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.1.0")
}

tasks.getByName<DisplayVersion>(BumpReadMeVersionPlugin.DISPLAY_VERSION) {
    versionName = android.defaultConfig.versionName
}

tasks.getByName<BumpVersion>(BUMP_VERSION) {
    overrideContent = "my-library:"
    versionName = android.defaultConfig.versionName
}