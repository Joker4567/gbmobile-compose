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
include(":features:feature-catalog")
include(":gbmobile-component")
include(":features:feature-card")
