package be.gfi.liferay.dowab;

public class WabGeneratorExtension {
    private String deployFolder;
    private String tomcatFolder;

    public String getDeployFolder() {
        return deployFolder;
    }

    public void setDeployFolder(final String deployFolder) {
        this.deployFolder = deployFolder;
    }

    public String getTomcatFolder() {
        return tomcatFolder;
    }

    public void setTomcatFolder(final String tomcatFolder) {
        this.tomcatFolder = tomcatFolder;
    }
}
