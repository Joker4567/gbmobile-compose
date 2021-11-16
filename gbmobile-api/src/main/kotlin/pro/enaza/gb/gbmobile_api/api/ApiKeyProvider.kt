package pro.enaza.gb.gbmobile_api.api

interface ApiKeyProvider {

    val apiKey: String?
}

fun ApiKeyProvider.requireApiKey(): String {
    return checkNotNull(apiKey)
}
