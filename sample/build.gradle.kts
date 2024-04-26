plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt)
    alias(libs.plugins.firebase.appdistribution)
}

android {
    namespace = "com.spendesk.grapes.samples"

    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.spendesk.grapes.samples"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = libs.versions.grapes.version.get()

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
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.google.material)

    // JUNIT
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // HILT
    implementation(libs.hilt.android)
    ksp(libs.hilt.ksp)

    // REFLECTION
    implementation(libs.kotlinx.reflect)
}
