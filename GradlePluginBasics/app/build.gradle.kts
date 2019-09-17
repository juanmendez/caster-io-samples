
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}
plugins.apply(BumpReadMeVersionPlugin::class)

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "info.adavis.gradlepluginbasics"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0.0"
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
    testImplementation("junit:junit:4.12")
}

class BumpReadMeVersionPlugin:Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            project.task(name="bumpVersion", type=BumpVersion::class) {
                file = File(project.rootDir.absolutePath + "/README.md")
                versionName = project.android.defaultConfig.versionName ?: "0.0"
            }
        }
    }
}

open class BumpVersion:DefaultTask() {
    var file:File? = null
    var versionName: String = "0.0"

    init{
        group = "pluginBasics"
        description = "Updates ReadMe file with the latest version"
    }

    private fun getReadMe(): File? {
        return file?.run {
            project.file(this)
        }
    }


    @TaskAction
    fun bump() {
        getReadMe()?.let { readMeFile ->
            val contents = readMeFile.readText(charset=Charsets.UTF_8).replace(
                """pluginBasics:.*""".toRegex(),
                "pluginBasics:${versionName}"
            )

            readMeFile.writeText(contents, Charsets.UTF_8)
        }
    }
}