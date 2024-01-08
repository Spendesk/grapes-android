import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    id("maven-publish")
}

android {
    namespace = "com.spendesk.grapes.compose"

    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.kotlin.compiler.get()
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
    toolVersion = libs.plugins.detekt.get().version.requiredVersion

    dependencies {
        detektPlugins(libs.detekt.formatting)
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
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit.android)
    androidTestImplementation(libs.espresso.core)


    api(libs.androidx.lifecycle.ktx)

    // Compose
    api(platform(libs.compose.bom))
    api(libs.bundles.compose)

    // UI Tests
    androidTestImplementation(libs.compose.tests.ui)
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                group = "com.github.Spendesk"
                artifactId = "grapes-android-compose"
                version = libs.versions.grapes.version.get()

                from(components["release"])
            }
        }
    }
}
