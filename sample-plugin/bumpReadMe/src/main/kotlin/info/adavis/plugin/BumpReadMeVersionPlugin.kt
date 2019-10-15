package info.adavis.plugin

import info.adavis.plugin.tasks.BumpVersion
import info.adavis.plugin.tasks.DisplayVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class BumpReadMeVersionPlugin : Plugin<Project> {
    companion object {
        const val PLUGIN_NAME = "bumpReadMeVersion"
        const val BUMP_VERSION = "bumpVersion"
        const val DISPLAY_VERSION = "displayVersion"
    }

    private lateinit var plugin: VersionPluginExtension
    override fun apply(project: Project) {
        plugin = project.extensions.create(PLUGIN_NAME, VersionPluginExtension::class.java)

        project.afterEvaluate {
            makeTasks(project)
        }
    }

    fun makeTasks(project: Project) {
        project.tasks.create(BUMP_VERSION, BumpVersion::class.java) {
            it.readMe = File(project.projectDir.absolutePath + "/README.md")
            it.versionName = plugin.versionName
            it.overrideContent = plugin.overrideContent
        }

        project.tasks.create(DISPLAY_VERSION, DisplayVersion::class.java) {
            it.versionName = plugin.versionName
        }
    }
}