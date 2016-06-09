package hello

import hello.tasks.DemoTask
import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class DemoPluginTest {
    @Test
    public void demo_plugin_should_add_task_to_project() {
        Project project = ProjectBuilder.builder().build()
        project.getPlugins().apply 'greetingPlugin'
        println("project.tasks is...:" + project.defaultTasks.toString())
        assertTrue(project.tasks.demo instanceof DemoTask)
    }
}