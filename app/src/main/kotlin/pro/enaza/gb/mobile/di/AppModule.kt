package pro.enaza.gb.mobile.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pro.enaza.gb.gbmobile_api.GBMobileApi
import pro.enaza.gb.gbmodile_room.db.AppDatabase
import pro.enaza.gb.gbmodile_room.db.GameCardDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApi(): GBMobileApi = GBMobileApi()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                AppDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameCardDao(appDatabase: AppDatabase): GameCardDao = appDatabase.gameCardDao()
}