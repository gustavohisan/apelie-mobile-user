object Versions {
    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 26

    const val gradle = "7.0.2"
    const val kotlin = "1.5.30"
    const val coroutines = "1.5.2"
    const val detekt = "1.14.0"
    const val ktlint = "0.41.0"
    const val koin = "3.1.2"
    const val retrofit = "2.9.0"
    const val gson = "2.8.6"
    const val glide = "4.12.0"

    const val timber = "4.7.1"

    const val ktx = "1.3.2"
    const val appcompat = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val security = "1.0.0"
    const val navigation = "2.3.5"
    const val fragment = "1.3.3"
    const val splashScreen = "1.0.0-alpha01"

    const val material = "1.2.1"

    const val compose = "1.1.0-alpha03"
    const val composeActivity = "1.3.0-rc02"
    const val composeViewModel = "1.0.0-alpha07"
    const val composeNavigation = "2.4.0-alpha06"
    const val accompanist = "0.18.0"
    const val coil = "1.3.2"
}

object Deps {
    val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
    val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val retrofit = RetrofitDeps
    val jetpack = JetpackDeps
    val google = GoogleDeps
    val koin = KoinDeps
    val compose = ComposeDeps
}

object JetpackDeps {
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val security = "androidx.security:security-crypto:${Versions.security}"
    val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
}

object GoogleDeps {
    val material = "com.google.android.material:material:${Versions.material}"
}

object RetrofitDeps {
    val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}

object KoinDeps {
    val core = "io.insert-koin:koin-core:${Versions.koin}"
    val android = "io.insert-koin:koin-android:${Versions.koin}"
    val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object ComposeDeps {
    val core = "androidx.compose.ui:ui:${Versions.compose}"
    val compiler = "androidx.compose:compose-compiler:${Versions.compose}"
    val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    val material = "androidx.compose.material:material:${Versions.compose}"
    val materialIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
    val materialIconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    val test = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    val placeholder = "com.google.accompanist:accompanist-placeholder:${Versions.accompanist}"
    val coil = "io.coil-kt:coil-compose:${Versions.coil}"
}
