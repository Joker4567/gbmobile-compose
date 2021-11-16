package pro.enaza.gb.mobile

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.enaza.gb.gbmobile_api.api.GBMobileApi
import pro.enaza.gb.gbmobile_api.api.ApiKeyProvider
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class AppModule {

    @[Provides Singleton]
    fun providerDevApi(apiKeyProvider: ApiKeyProvider) = GBMobileApi(apiKeyProvider)

    @Provides
    fun providerDevApiKeyProvider() = object : ApiKeyProvider {

        override val apiKey: String = ""
    }
}