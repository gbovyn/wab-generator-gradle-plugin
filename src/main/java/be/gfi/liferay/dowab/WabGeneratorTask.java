package be.gfi.liferay.dowab;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class WabGeneratorTask extends DefaultTask {


    @TaskAction
    public void greet() {
        System.out.println("GREETINGS");
    }
}
