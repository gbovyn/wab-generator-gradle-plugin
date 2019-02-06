package be.gfi.liferay.dowab;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.JavaBasePlugin;

public class WabGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(final Project project) {
        //project.getExtensions().create("demoSetting", DemoPluginExtension.class);
        project.getTasks().create("demo", WabGeneratorTask.class);
    }
}
