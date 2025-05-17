plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.xuatseg.yuanyin"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.xuatseg.yuanyin"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "2.1.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // ===================== 测试配置 =====================
    testOptions {
        // 包含Android资源在测试中
        unitTests.isIncludeAndroidResources = true
        // 未找到方法时返回默认值而不是null
        unitTests.isReturnDefaultValues = true

        // 为测试添加JVM参数，启用ByteBuddy实验性支持
        unitTests.all {
            it.jvmArgs("-Dnet.bytebuddy.experimental=true")
        }
    }
}

dependencies {
    val composeBomVersion = "2025.02.00"
    val coroutinesVersion = "1.7.3"
    val mockitoVersion = "5.10.0"
    val mockitoKotlinVersion = "4.1.0"

    implementation(platform("androidx.compose:compose-bom:$composeBomVersion"))

    // 核心依赖
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)
    implementation(libs.androidx.activity.compose.v181)
    implementation(libs.androidx.ui)
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Room数据库依赖
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    // Gson依赖
    implementation("com.google.code.gson:gson:2.10.1")

    // 协程
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

//    // ===================== 测试依赖 =====================
//    // === 单元测试依赖 ===
//    // JUnit测试框架
//    testImplementation("junit:junit:4.13.2")
//    // Mockito模拟框架
//    testImplementation("org.mockito:mockito-core:$mockitoVersion")
//    // Mockito的Kotlin扩展
//    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
//    // 协程测试工具
//    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
//    // 架构组件测试支持
//    testImplementation("androidx.arch.core:core-testing:2.2.0")
//    // Robolectric模拟Android环境
//    testImplementation("org.robolectric:robolectric:4.11.1")
//    // MockK Kotlin模拟框架
//    testImplementation("io.mockk:mockk:1.13.9")
//    // Android测试核心库
//    testImplementation("androidx.test:core:1.5.0")
//    testImplementation("androidx.test:core-ktx:1.5.0")
//
//    // === Android UI测试依赖 ===
//    // JUnit Android扩展
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    // Espresso UI测试框架
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    // Compose测试BOM
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
//    // Compose UI测试
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    // Mockito Android支持
//    androidTestImplementation("org.mockito:mockito-android:$mockitoVersion")
//    // MockK Android支持
//    androidTestImplementation("io.mockk:mockk-android:1.13.9")
//
//    // === 调试支持 ===
//    // Compose UI调试工具
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    // Compose UI测试清单
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
