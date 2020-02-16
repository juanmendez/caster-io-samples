package info.adavis.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class BumpVersion : DefaultTask() {

    @JvmField
    var readMe : File? = null
    var versionName: String = "0.0"
    var overrideContent = ""

    init {
        group = "plugin v2"
        description = "Updates ReadMe file with the latest version"
    }

    private fun getReadMe(): File? {
        return readMe?.run {
            project.file(this)
        }
    }


    @TaskAction
    fun bump() {
        getReadMe()?.let { readMeFile ->
            val contents = readMeFile.readText(charset = Charsets.UTF_8).replace(
                """$overrideContent.*""".toRegex(),
                "$overrideContent$versionName"
            )

            readMeFile.writeText(contents, Charsets.UTF_8)
        }
    }
}