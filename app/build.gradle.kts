plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
}

android {
    namespace = "com.softwareit.sduhub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.softwareit.sduhub"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }

        release {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//            signingConfig = signingConfigs.getByName(AppConfigs.name)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget =  JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//    timber
    val timberVersion = "5.0.1"
    implementation("com.jakewharton.timber:timber:$timberVersion")

//    lottie
    implementation("com.airbnb.android:lottie-compose:6.1.0")

//    storyly
    implementation("com.appsamurai.storyly:storyly:2.13.0")

////    network
//    val chuckerVersion = "4.0.0"
//    debugImplementation("com.github.chuckerteam.chucker:library:$chuckerVersion")
//    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:$chuckerVersion")


//    koin
    val koinVersion = "3.5.3"
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")

//    navigation
    val navVersion = "1.9.63"
    implementation("io.github.raamcosta.compose-destinations:animations-core:$navVersion")
    implementation("io.github.raamcosta.compose-destinations:core:$navVersion")
    ksp("io.github.raamcosta.compose-destinations:ksp:$navVersion")

//    core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

//    test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}