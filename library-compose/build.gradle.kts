import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    alias(grapesLibs.plugins.android.library)
    alias(grapesLibs.plugins.kotlin.android)
    alias(grapesLibs.plugins.detekt)
    id("maven-publish")
}

android {
    namespace = "com.spendesk.grapes.compose"

    compileSdk = grapesLibs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = grapesLibs.versions.androidMinSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = grapesLibs.versions.androidxComposeCompiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                *(fileTree("./proguard") { include("*.pro") }).toList().toTypedArray()
            )
        }
    }

    publishing {
        singleVariant("release")
    }
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config.setFrom("$rootDir/config/detekt/detekt-configuration.yml")
    baseline = file("$projectDir/config/baseline.xml")
    toolVersion = grapesLibs.plugins.detekt.get().version.requiredVersion

    dependencies {
        detektPlugins(grapesLibs.detekt.formatting)
    }
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = JavaVersion.VERSION_17.toString()
    reports {
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
        md.required.set(false)
    }
}

tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = JavaVersion.VERSION_17.toString()
}

dependencies {
    testImplementation(grapesLibs.junit4)
    androidTestImplementation(grapesLibs.androidx.test.junit)
    androidTestImplementation(grapesLibs.androidx.espresso.core)

    api(grapesLibs.androidx.lifecycle.ktx)

    // Compose
    api(platform(grapesLibs.androidx.compose.bom))
    api(grapesLibs.bundles.androidxcompose)

    // UI Tests
    androidTestImplementation(grapesLibs.androidx.compose.ui.test)
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                group = "com.github.Spendesk"
                artifactId = "grapes-android-compose"
                version = grapesLibs.versions.grapes.version.get()

                from(components["release"])
            }
        }
    }
}
