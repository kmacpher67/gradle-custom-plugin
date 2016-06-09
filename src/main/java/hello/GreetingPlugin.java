package hello;

import hello.tasks.DemoTask;
import org.gradle.api.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.DependencySet;
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency;

/**
 * Created by 310237766 on 6/8/2016.
 */
public class GreetingPlugin implements Plugin<Project> {

    public static final String FIRST_TASK = "build";  // or build or what? compileJava
    public static final String COMPILE_CONFIGURATION_NAME = "compile";

    @Override
    public void apply(Project project) {
        System.out.println(" GreetingPlugin apply for demo-time!!!!");
        project.getExtensions().create("demoSetting", DemoPluginExtension.class);

        project.getTasks().create("demo", DemoTask.class);
        project.getTasks().getByName("demo").setDescription("");
        System.out.println(" making the build task depend on demo");
        // We need to modify the versions before we do the pom get.
        // can we make our "demo" a pre-req for target module's compile
        project.getTasks().getByName(FIRST_TASK).dependsOn("demo");

        ConfigurationContainer configurations = project.getConfigurations();
        Configuration compileConfiguration = configurations.getByName(COMPILE_CONFIGURATION_NAME);
        showDependencies(compileConfiguration);
        //"org.projectlombok:lombok:1.16.8"
        Dependency dependency = new DefaultExternalModuleDependency("org.projectlombok", "lombok", "1.16.8", "compile");
//        Dependency dependency2 = new DefaultExternalModuleDependency("org.springframework.boot", "spring-boot-starter-test", "1.3.2.RELEASE", "compile");
//        Dependency dependency3 = new DefaultExternalModuleDependency("org.springframework.boot", "spring-boot-starter-web", "1.3.2.RELEASE", "compile");

        compileConfiguration.getDependencies().add(dependency);
        compileConfiguration.getDependencies().add( new DefaultExternalModuleDependency("org.springframework.boot", "spring-boot-starter-test", "1.3.2.RELEASE", "compile"));
        compileConfiguration.getDependencies().add(new DefaultExternalModuleDependency("org.springframework.boot", "spring-boot-starter-web", "1.3.2.RELEASE", "compile"));
        compileConfiguration.getDependencies().add(new DefaultExternalModuleDependency("org.springframework", "spring-beans", "4.2.4.RELEASE", "compile"));
        compileConfiguration.getDependencies().add(new DefaultExternalModuleDependency("org.springframework", "spring-core", "4.2.4.RELEASE", "compile"));
        compileConfiguration.getDependencies().add(new DefaultExternalModuleDependency("org.springframework", "spring-test", "4.2.4.RELEASE", "compile"));
        compileConfiguration.getDependencies().add(new DefaultExternalModuleDependency("org.springframework", "spring-webmvc", "4.2.4.RELEASE", "compile"));
        compileConfiguration.getDependencies().add(new DefaultExternalModuleDependency("org.springframework", "spring-web", "4.2.4.RELEASE", "compile"));
        compileConfiguration.getDependencies().add(new DefaultExternalModuleDependency("com.jayway.jsonpath", "json-path", "2.0.0", "compile"));
        compileConfiguration.getDependencies().add(new DefaultExternalModuleDependency("com.jayway.jsonpath", "json-path-assert", "2.0.0", "compile"));


        System.out.println(" AFTER add ing goodies");
        showDependencies(compileConfiguration);
        System.out.println(" It is DONE!! The magic has started. ");
}
    private void showDependencies(Configuration compileConfiguration){
        DependencySet compiledependencySet = compileConfiguration.getAllDependencies();
        System.out.println("COMPILE compiledependencySet");
        for(Dependency dependency : compiledependencySet){
            System.out.print("dependency get Group()=" + dependency.getGroup() );
            System.out.print(" : Name=" + dependency.getName() );
            System.out.println(" : Version()=" + dependency.getVersion() );
        }
    }

}
