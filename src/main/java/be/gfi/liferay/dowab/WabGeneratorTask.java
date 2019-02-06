package be.gfi.liferay.dowab;

import aQute.bnd.osgi.Constants;
import com.liferay.portal.osgi.web.wab.generator.WabProcessorTask;
import com.liferay.portal.tools.ToolDependencies;
import org.apache.commons.io.FilenameUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.tasks.TaskAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WabGeneratorTask extends DefaultTask {

    private static Logger logger = LoggerFactory.getLogger(WabGeneratorTask.class);

    private static final String SLASH = "/";
    private static final String WEB_CONTEXT_PATH = "Web-ContextPath";

    @TaskAction
    public void doWab() throws IOException {
        ToolDependencies.wire();

        final Task warTask = getProject().getTasks().getByName(WarPlugin.WAR_TASK_NAME);
        final File warFile = warTask.getOutputs().getFiles().getSingleFile();
        final String warFileName = FilenameUtils.getBaseName(warFile.getName());

        logger.info("Processing {}", warFileName);

        String[] bundleSymbolicName = { warFileName };
        String[] webContextPath = { SLASH + warFileName };

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Constants.BUNDLE_SYMBOLICNAME, bundleSymbolicName);
        parameters.put(WEB_CONTEXT_PATH, webContextPath);

        WabProcessorTask wabProcessor = new WabProcessorTask(getClass().getClassLoader(), warFile, parameters);
        wabProcessor.getProcessedFile();

        logger.info("doWab done");
    }
}
