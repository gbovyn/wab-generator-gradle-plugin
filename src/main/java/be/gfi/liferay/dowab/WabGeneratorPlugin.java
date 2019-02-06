package be.gfi.liferay.dowab;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class WabGeneratorPlugin implements Plugin<Project> {

    private static final String DO_WAB_TASK_NAME = "doWab";

    @Override
    public void apply(final Project project) {
        project.getTasks().create(DO_WAB_TASK_NAME, WabGeneratorTask.class);
    }
}
