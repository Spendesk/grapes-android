plugins {
    alias(grapesLibs.plugins.android.library)
    alias(grapesLibs.plugins.kotlin.android)
    alias(grapesLibs.plugins.ksp)
    id("maven-publish")
}

android {
    namespace = "com.spendesk.grapes"

    compileSdk = grapesLibs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = grapesLibs.versions.androidMinSdk.get().toInt()
        targetSdk = grapesLibs.versions.androidTargetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    implementation(grapesLibs.androidx.core.ktx)
    implementation(grapesLibs.androidx.appcompat)
    implementation(grapesLibs.material)
    implementation(grapesLibs.androidx.constraintlayout)

    implementation(grapesLibs.glide)
    ksp(grapesLibs.glide.compiler)
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                group = "com.github.Spendesk"
                artifactId = "grapes-android"
                version = grapesLibs.versions.grapes.version.get()

                from(components["release"])
            }
        }
    }
}
