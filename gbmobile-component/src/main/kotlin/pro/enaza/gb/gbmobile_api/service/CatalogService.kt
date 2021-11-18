package pro.enaza.gb.gbmobile_api.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import pro.enaza.gb.shared_model.network.GameCardDto
import pro.enaza.gb.gbmobile_api.internal.MIMETYPE_JSON
import pro.enaza.gb.gbmobile_api.internal.authorizedOkHttClient
import pro.enaza.gb.gbmobile_api.internal.defaultJson
import pro.enaza.gb.gbmobile_api.internal.retrofit
import pro.enaza.gb.gbmobile_api.result.Result
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.create

interface CatalogService {

    @GET(API_GET_PRODUCTS)
    suspend fun getProducts(@Query("enableNotForSale") enableNotForSale: Int): Result<List<GameCardDto>>

    companion object {
        const val API_GET_PRODUCTS = "shopwindow/getProducts/?device=android"
    }
}

fun CatalogService(
        okHttpClient: OkHttpClient = OkHttpClient(),
        json: Json = defaultJson()
): CatalogService {
    val retrofit = retrofit(
            okHttpClient.authorizedOkHttClient(),
            json.asConverterFactory(MIMETYPE_JSON)
    )
    return retrofit.create()
}