package pro.enaza.gb.gbmobile_api

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import pro.enaza.gb.gbmobile_api.service.CatalogService
import pro.enaza.gb.gbmodile_room.db.AppDatabase

class GBMobileApi {

    private val okHttpClient = OkHttpClient()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    val catalog by lazy { CatalogService(okHttpClient, json) }
}
