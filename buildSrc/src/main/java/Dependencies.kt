object Versions {
    const val compileSdk = 30
    const val targetSdk = 30
    const val minSdk = 26

    const val gradle = "4.1.3"
    const val kotlin = "1.4.31"
    const val detekt = "1.14.0"
    const val ktlint = "0.41.0"
    const val koin = "2.2.2"

    const val ktx = "1.3.2"
    const val appcompat = "1.2.0"

    const val material = "1.2.1"
}

object Deps {
    val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
    val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    val jetpack = JetpackDeps
    val google = GoogleDeps
    val koin = KoinDeps
}

object JetpackDeps {
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
}

object GoogleDeps {
    val material = "com.google.android.material:material:${Versions.material}"
}

object KoinDeps {
    val core = "org.koin:koin-android:${Versions.koin}"
    val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}