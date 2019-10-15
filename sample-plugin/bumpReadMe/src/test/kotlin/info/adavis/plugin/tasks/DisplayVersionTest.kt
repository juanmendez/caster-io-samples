package info.adavis.plugin.tasks

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Annyce Davis on 2/16/16.
 * in Kotlin by Juan Mendez 10/14/2019
 */
class DisplayVersionTest {

    private lateinit var project: Project
    private lateinit var task: Task

    @BeforeEach
    fun setUp() {
        project = ProjectBuilder.builder().build()
    }

    @Test
    fun shouldBeAbleToCreateTask() {
        task = project.tasks.create("DisplayVersion", DisplayVersion::class.java)
        assertTrue(task is DisplayVersion)
    }
}