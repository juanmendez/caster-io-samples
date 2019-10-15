package info.adavis.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DisplayVersion : DefaultTask() {

    var versionName: String? = null

    init {
        group = "plugin v4"
    }

    @TaskAction
    fun display() {
        description = "Prints out the current version number"
        println("**** My Version: $versionName ****")
    }
}