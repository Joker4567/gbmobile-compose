package pro.enaza.gb.mobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import pro.enaza.gb.gbmobile_api.repository.CatalogRepository
import pro.enaza.gb.gbmobile_api.service.CatalogService
import pro.enaza.gb.gbmodile_room.db.AppDatabase
import pro.enaza.gb.gbmodile_room.db.GameCardDao

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCatalog(
            catalogService: CatalogService,
            db: GameCardDao
    ): CatalogRepository {
        return CatalogRepository(
                catalogService = catalogService,
                db = db
        )
    }
}