package info.adavis.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import java.io.File

class BumpReadMeVersionPlugin : Plugin<Project> {
    companion object {
        const val BUMP_READ_ME_VERSION = "bumpReadMeVersion"
        const val BUMP_VERSION = "bumpVersion"
        const val DISPLAY_VERSION = "displayVersion"
    }

    override fun apply(project: Project) {
        project.extensions.create(BUMP_READ_ME_VERSION, VersionPluginExtension::class.java)

        /**
         * project.afterEvaluate { tasks } doesn't allow for the tasks to be recognized in
         * the app gradle file. So the tasks are just out as it appears below
         * @see https://guides.gradle.org/writing-gradle-plugins/
         */
        project.tasks.create(name = BUMP_VERSION, type = BumpVersion::class) {
            readMe = File(project.rootDir.absolutePath + "/README.md")
        }

        project.tasks.create(name = DISPLAY_VERSION, type = DisplayVersion::class) {
            // versionName = project.android.defaultConfig.versionName
            // this is not recognized in this kt file. instead it is provided from the app's
            // `build.gradle.kt` file instead
        }
    }
}