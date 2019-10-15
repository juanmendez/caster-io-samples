package info.adavis.plugin

import info.adavis.plugin.tasks.BumpVersion
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

/**
 * @author Annyce Davis on 2/17/16.
 * in Kotlin by Juan Mendez 10/14/2019
 */
class BumpReadMeVersionPluginTest {

    private val temporaryFolder = TemporaryFolder()
    private lateinit var projectDir: File
    private lateinit var project: Project
    private lateinit var task: Task

    @BeforeEach
    fun setUp() {
        temporaryFolder.create()
        projectDir = temporaryFolder.root
        projectDir.mkdirs()

        project = ProjectBuilder.builder().withProjectDir(projectDir).build()
    }

    @AfterEach
    fun afterEach() {
        temporaryFolder.delete()
    }

    @Test
    fun pluginShouldBeApplied() {
        project.apply {
            it.plugin(BumpReadMeVersionPlugin::class.java)
        }

        project.plugins.findPlugin(BumpReadMeVersionPlugin::class.java)?.apply {
            makeTasks(project)
        }

        assertNotNull(project.tasks.findByName(BumpReadMeVersionPlugin.BUMP_VERSION))
        assertNotNull(project.tasks.findByName(BumpReadMeVersionPlugin.DISPLAY_VERSION))
    }

    @Test
    fun pluginShouldHandleExtensionVersionInfo() {
        project.apply {
            it.plugin(BumpReadMeVersionPlugin::class.java)
        }

        project.plugins.findPlugin(BumpReadMeVersionPlugin::class.java)?.apply {
            // project.evaluate() is nowhere to be found.
            // it was not officially available
            // instead on testing we can call makeTasks to append tasks to the plugin
            makeTasks(project)
        }

        project.tasks.findByName(BumpReadMeVersionPlugin.BUMP_VERSION)?.apply {
            if (this is BumpVersion) {
                versionName = "1.1"
                assertEquals("1.1", versionName)
            }
        }
    }
}