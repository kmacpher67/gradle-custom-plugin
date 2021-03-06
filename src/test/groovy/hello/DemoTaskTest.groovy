package hello

import hello.tasks.DemoTask
import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class DemoTaskTest {
    @Test
    public void should_be_able_to_add_task_to_project() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('demo', type: DemoTask)
        println("task.name=" + task.name)
        println("task.actions=" + task.actions.toString())

        assertTrue(task instanceof DemoTask)
    }
}