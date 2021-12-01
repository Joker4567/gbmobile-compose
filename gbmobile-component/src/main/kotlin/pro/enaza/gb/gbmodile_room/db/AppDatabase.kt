package pro.enaza.gb.gbmodile_room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pro.enaza.gb.gbmodile_room.db.AppDatabase.Companion.DB_VERSION
import pro.enaza.gb.shared_model.room.GameCardEntity

@Database(
        entities = [
            GameCardEntity::class
        ],
        version = DB_VERSION,
        exportSchema = false
)
@TypeConverters(TypeDownloadConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_VERSION = 1
        const val NAME = "composeGb.db"
    }

    abstract fun gameCardDao(): GameCardDao
}