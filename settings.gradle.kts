pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("grapesLibs") {
            from(files("./gradle/libs.versions.toml"))
        }
    }
}

include(":sample")
include(":library")
include(":library-compose")
