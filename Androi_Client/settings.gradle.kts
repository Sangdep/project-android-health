// settings.gradle.kts (Project level) - PHẢI CÓ FILE NÀY
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io") // ✅ thêm ở đây cũng được
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") // ✅ bắt buộc
    }
}

rootProject.name = "App_Selfcare"
include(":app")
