package hello;

import hello.tasks.DemoTask;
import org.gradle.api.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by 310237766 on 6/8/2016.
 */
public class GreetingPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions().create("demoSetting", DemoPluginExtension.class);
        project.getTasks().create("demo", DemoTask.class);
    }

}
