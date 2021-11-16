package pro.enaza.gb.gbmobile_api.api

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

class GBMobileApi(private val apiKey: ApiKeyProvider) {

    private val okHttpClient = OkHttpClient()
    private val json = Json {
        ignoreUnknownKeys = true
    }
}
