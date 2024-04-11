plugins {
    alias(grapesLibs.plugins.android.application)
    alias(grapesLibs.plugins.kotlin.android)
    alias(grapesLibs.plugins.ksp)
    alias(grapesLibs.plugins.kotlin.parcelize)
    alias(grapesLibs.plugins.hilt)
    alias(grapesLibs.plugins.firebase.appdistribution)
}

android {
    namespace = "com.spendesk.grapes.samples"

    compileSdk = grapesLibs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.spendesk.grapes.samples"
        minSdk = grapesLibs.versions.androidMinSdk.get().toInt()
        targetSdk = grapesLibs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = grapesLibs.versions.grapes.version.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = grapesLibs.versions.androidxComposeCompiler.get()
    }

    signingConfigs {
        create("release") {
            storeFile = file("spendesk-grapes.keystore")
            storePassword = System.getenv("STORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }

        getByName("debug") {
            storeFile = file("debug.keystore")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                *(fileTree("./proguard") { include("*.pro") }).toList().toTypedArray()
            )

            firebaseAppDistribution {
                groups = "release-candidate"
                appId = "1:1044115290075:android:0ab502faa6ab13f24b59fe"
            }
        }

        debug {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
        }
    }

    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    implementation(project(":library"))
    implementation(project(":library-compose"))

    implementation(grapesLibs.androidx.core.ktx)
    implementation(grapesLibs.androidx.fragment.ktx)

    implementation(grapesLibs.material)

    // JUNIT
    testImplementation(grapesLibs.junit4)
    androidTestImplementation(grapesLibs.junit.android)
    androidTestImplementation(grapesLibs.espresso.core)

    // HILT
    implementation(grapesLibs.hilt.android)
    ksp(grapesLibs.hilt.compiler)

    // REFLECTION
    implementation(grapesLibs.reflection)
}
