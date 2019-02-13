package be.gfi.liferay.dowab;

import aQute.bnd.osgi.Constants;
import com.liferay.portal.osgi.web.wab.generator.internal.WabGenerator;
import org.apache.commons.io.FilenameUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.tasks.TaskAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WabGeneratorTask extends DefaultTask {


    private static Logger logger = LoggerFactory.getLogger(WabGeneratorTask.class);

    private static final String SLASH = "/";
    private static final String WEB_CONTEXT_PATH = "Web-ContextPath";
    private static final String DEPLOY_DIR = "DEPLOY_DIR";
    private static final String TOMCAT_DIR = "TOMCAT_DIR";

    @TaskAction
    public void doWab() throws IOException {
        final Task warTask = getProject().getTasks().getByName(WarPlugin.WAR_TASK_NAME);
        final File warFile = warTask.getOutputs().getFiles().getSingleFile();
        final String warFileName = FilenameUtils.getBaseName(warFile.getName());

        final WabGeneratorExtension extension = getProject().getExtensions().getByType(WabGeneratorExtension.class);

        Properties properties = new Properties();
        properties.load(new FileInputStream(System.getenv("USERPROFILE") + "/.gradle/gradle.properties"));
        if (extension.getDeployFolder() == null) {
            extension.setDeployFolder(properties.getProperty("deployFolder"));
            System.out.println("Deploy folder loaded from property file");
        }
        if (extension.getTomcatFolder() == null) {
            extension.setTomcatFolder(properties.getProperty("tomcatFolder"));
            System.out.println("Tomcat folder loaded from property file");
        }

        logger.info("Processing {}", warFileName);

        String[] bundleSymbolicName = { warFileName };
        String[] webContextPath = { SLASH + warFileName };
        String[] tomcatDir = { extension.getTomcatFolder() };
        String[] deployDir = { extension.getDeployFolder() };

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Constants.BUNDLE_SYMBOLICNAME, bundleSymbolicName);
        parameters.put(WEB_CONTEXT_PATH, webContextPath);
        parameters.put(TOMCAT_DIR, tomcatDir);
        parameters.put(DEPLOY_DIR, deployDir);

        WabGenerator wabProcessor = new WabGenerator();
        wabProcessor.generate(null, warFile, parameters);

        logger.info("doWab done");
    }
}
