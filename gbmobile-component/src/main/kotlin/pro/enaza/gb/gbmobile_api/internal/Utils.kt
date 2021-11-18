package pro.enaza.gb.gbmobile_api.internal

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import pro.enaza.gb.gbmobile_api.result.retrofit.ResultAdapterFactory
import retrofit2.Converter
import retrofit2.Retrofit

internal fun defaultJson() = Json {
    ignoreUnknownKeys = true
}

internal fun retrofit(
    okHttpClient: OkHttpClient,
    vararg factories: Converter.Factory
): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl(API_URL)
        addCallAdapterFactory(ResultAdapterFactory())
        client(okHttpClient)
        factories.forEach(this::addConverterFactory)
    }.build()
}

internal fun OkHttpClient.authorizedOkHttClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}