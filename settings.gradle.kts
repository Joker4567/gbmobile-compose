dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "compose_gbmobile"
include(":app")
include(":core")
include(":gbmobile-api")
include(":features:feature-catalog")
include(":gbmobile-theme")
