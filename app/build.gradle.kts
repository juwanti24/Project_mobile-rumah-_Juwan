plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.project_juwan"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.project_juwan"
        minSdk = 27
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main") {
            res.setSrcDirs(
                listOf(
                    "src/main/res",
                    "src/main/res_icons",
                    "src/main/res_bg",
                    "src/main/res_images"
                )
            )
        }
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures{
        viewBinding=true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.cardview)
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    implementation("com.github.bumptech.glide:glide:4.16.0")
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}