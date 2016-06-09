package hello

import hello.tasks.DemoTask
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.internal.artifacts.dependencies.DefaultDependencyArtifact
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class DemoPluginTest {
    @Test
    public void demo_plugin_should_add_task_to_project() {
        println(" TESTING first task" + GreetingPlugin.FIRST_TASK)
        Project project = ProjectBuilder.builder().build()

        // add a dummy task to project for dependency check
        project.getTasks().create(GreetingPlugin.FIRST_TASK, DefaultTask.class);
        project.configurations.create(GreetingPlugin.COMPILE_CONFIGURATION_NAME)
        project.getPlugins().apply 'greetingPlugin'
        println("project.tasks build is...:" + project.tasks.build.actions.size())
        assertTrue(project.tasks.demo instanceof DemoTask)
    }

    private Project setupProject(Project project){

        String dependencyNotation = "compile 'o.demo:DoItDemo:6.6.6'";
        //Configuration dependencyNotation = project.getConfigurations().maybeCreate(GreetingPlugin.COMPILE_CONFIGURATION_NAME);
//        dependency.name = "DoItDemo";
//        dependency.version = "6.6.6";
//        def dependencyNotation = [:];//'group': 'com.google.code.guice', 'name': 'guice', 'version': '1.0'
//        dependencyNotation.put(group,'com.google.code.guice')
//        dependencyNotation.put(name,'guice')
//        dependencyNotation.put(version: '1.0')
        //Dependency dependency = new DefaultExternalModuleDependency('com.google.code.guice', 'guice','6.6.6', 'compile');
        //dependencyNotation.dependencies.add(dependency)
        Dependency dependency = project.dependencies.add(GreetingPlugin.COMPILE_CONFIGURATION_NAME, dependencyNotation);
        println(" dependency.name=" + dependency.name)
        return project;
    }
}