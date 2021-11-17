package pro.enaza.gb.mobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import pro.enaza.gb.feature_catalog.CatalogRepository
import pro.enaza.gb.gbmobile_api.api.service.CatalogService

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCatalog(
            catalogService: CatalogService
    ): CatalogRepository {
        return CatalogRepository(
                catalogService = catalogService
        )
    }
}