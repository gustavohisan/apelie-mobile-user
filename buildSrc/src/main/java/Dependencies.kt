object Versions {
    const val compileSdk = 30
    const val targetSdk = 30
    const val minSdk = 26

    const val gradle = "4.1.3"
    const val kotlin = "1.4.31"
    const val detekt = "1.14.0"
    const val ktlint = "0.41.0"
    const val koin = "2.2.2"
    const val retrofit = "2.9.0"
    const val gson = "2.8.6"

    const val timber = "4.7.1"

    const val ktx = "1.3.2"
    const val appcompat = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val security = "1.0.0"
    const val navigation = "2.3.5"

    const val material = "1.2.1"
}

object Deps {
    val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
    val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val retrofit = RetrofitDeps
    val jetpack = JetpackDeps
    val google = GoogleDeps
    val koin = KoinDeps
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
}

object GoogleDeps {
    val material = "com.google.android.material:material:${Versions.material}"
}

object RetrofitDeps {
    val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}

object KoinDeps {
    val core = "org.koin:koin-android:${Versions.koin}"
    val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}
