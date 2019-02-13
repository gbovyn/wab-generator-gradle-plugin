# wab-generator-gradle-plugin

## Usage

### Install wab-generator library

```sh
git clone https://github.com/gbovyn/wab-generator
cd wab-generator
.\gradlew clean build install
```

### Install wab-generator-gradle-plugin

```sh
git clone https://github.com/gbovyn/wab-generator-gradle-plugin
cd wab-generator-gradle-plugin
.\gradlew clean build install
```

### Deploy liferay-dowab

Liferay cannot deploy WAB files. This OSGi bundle will take care of that for us:

```sh
git clone https://github.com/sueastside/liferay-dowab
cd liferay-dowab
.\gradlew clean build
```
and copy the generated `liferay-dowab.jar` into your Liferay deploy folder.

### Configure in your project build.gradle

```groovy
buildscript {
	repositories {
		mavenLocal()
	}

	dependencies {
		classpath group: 'be.gfi.liferay.dowab', name: 'wab-generator-gradle-plugin', version: '1.0-SNAPSHOT'
	}
}

apply plugin: 'be.gfi.liferay.dowab'

doWabSettings {
	deployFolder = 'E:/liferay71/bundles/dowab/'
	tomcatFolder = 'E:/liferay71/bundles/tomcat-9.0.6/'
}
```

Tomcat folder location is required for the WAB to include all necessary TLD files.
Deploy folder location is optional. In case the value is not present the file will always be written in `{PROJECT}/liferay/osgi/wabs`.

### Run task

```sh
.\gradlew clean build doWab
```
