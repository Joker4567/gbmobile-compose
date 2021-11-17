package pro.enaza.gb.gbmobile_api.api

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import pro.enaza.gb.gbmobile_api.api.service.CatalogService

class GBMobileApi {

    private val okHttpClient = OkHttpClient()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    val catalog by lazy { CatalogService(okHttpClient, json) }
}
