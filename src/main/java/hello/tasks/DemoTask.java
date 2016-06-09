package hello.tasks;

import hello.DemoPluginExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.*;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.tasks.TaskAction;

/**
 * Created by 310237766 on 6/8/2016.
 */
public class DemoTask extends DefaultTask {

    public ConfigurationContainer configurations = getProject().getConfigurations();
    public String COMPILE_CONFIGURATION_NAME = "compile";

    @TaskAction
    public void greet() {
        DemoPluginExtension extension = getProject().getExtensions().findByType(DemoPluginExtension.class);
        if (extension == null) {
            extension = new DemoPluginExtension();
        }

        String message = extension.getMessage();

        System.out.println(message);
    }

    @TaskAction
    public void mutateDependency() {

        Configuration configuration = getProject().getConfigurations().getByName(Dependency.ARCHIVES_CONFIGURATION);
        Configuration compileConfiguration = configurations.getByName(COMPILE_CONFIGURATION_NAME);
        System.out.println(" getProject().getConfigurations().getByName(Dependency.ARCHIVES_CONFIGURATION)=" + configuration.getName()  );
        DependencySet dependencySet = configuration.getAllDependencies();
        System.out.println("dependencySet=" + dependencySet.size() );

        for(Dependency dependency : dependencySet){
            System.out.print("dependency get Group()=" + dependency.getGroup() );
            System.out.print(" : Name=" + dependency.getName() );
            System.out.println(" : Version()=" + dependency.getVersion() );
        }

        DependencySet compiledependencySet = compileConfiguration.getAllDependencies();
        System.out.println("COMPILE compiledependencySet");
        for(Dependency dependency : compiledependencySet){
            System.out.print("dependency get Group()=" + dependency.getGroup() );
            System.out.print(" : Name=" + dependency.getName() );
            System.out.println(" : Version()=" + dependency.getVersion() );
        }

        System.out.println(" getProject().getConfigurations().getByName(compile="+getProject().getConfigurations().getByName("compile") );

        DependencyHandler dependencyHandler = getProject().getDependencies();
//        getProject().dependencies().findAll {
//            it in ModuleDependency && it.version
//        }.each {
//            log.debug(
//                    "Adding managed version in configuration '{}' for dependency '{}'",
//                    configuration.name, it)
//            dependencyManagementContainer.

        // configurationName  = compile, runtime, testCompile, etc
//        System.out.println("dependencyHandler()=" + dependencyHandler.)



//        ManagedDependencies dependencies = ManagedDependencies.get();
//        ModuleVersionSelector target = resolveDetails.getTarget();

    }
}
