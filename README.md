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

### Configure in your project build.gradle

```groovy
buildscript {
	repositories {
		mavenLocal()
	}

	dependencies {
		classpath group: "be.gfi.liferay.dowab", name: "wab-generator-gradle-plugin", version: "1.0-SNAPSHOT"
	}
}

apply plugin: "be.gfi.liferay.dowab"
```

### Run task

```sh
.\gradlew clean build doWab
```
