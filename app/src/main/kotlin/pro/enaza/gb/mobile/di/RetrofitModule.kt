package pro.enaza.gb.mobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.enaza.gb.gbmobile_api.service.CatalogService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofitServiceCatalog(): CatalogService {
        return CatalogService()
    }
}