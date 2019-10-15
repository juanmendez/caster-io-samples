package info.adavis.plugin.tasks

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File

/**
 * @author Annyce Davis on 2/16/16.
 * in Kotlin by Juan Mendez 10/14/2019
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BumpVersionTest {
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
    fun afterSetup() {
        temporaryFolder.delete()
    }

    @Test
    fun `should be able to create test`() {
        task = project.tasks.create("bumpVersion", BumpVersion::class.java)
        assertTrue(task is BumpVersion)
    }

    @Test
    fun `should be able to modify readMe`() {
        val readMeFile = temporaryFolder.newFile("README.md")
        readMeFile.writeText("plugin:0.0")

        task = project.tasks.create("bumpVersion", BumpVersion::class.java) { bumpVersion ->
            bumpVersion.apply {
                readMe = readMeFile
                versionName = "1.1"
                overrideContent = "plugin:"
            }
        }

        (task as? BumpVersion)?.bump()

        assertEquals("plugin:1.1", readMeFile.readText())
    }
}