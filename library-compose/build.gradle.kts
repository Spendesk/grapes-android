plugins {
    alias(grapesLibs.plugins.android.library)
    alias(grapesLibs.plugins.kotlin.android)
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
        kotlinCompilerExtensionVersion = grapesLibs.versions.compose.kotlin.compiler.get()
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

dependencies {
    testImplementation(grapesLibs.junit4)
    androidTestImplementation(grapesLibs.junit.android)
    androidTestImplementation(grapesLibs.espresso.core)


    api(grapesLibs.androidx.lifecycle.ktx)

    // Compose
    api(platform(grapesLibs.compose.bom))
    api(grapesLibs.bundles.compose)

    // UI Tests
    androidTestImplementation(grapesLibs.compose.tests.ui)
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
