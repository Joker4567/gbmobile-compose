package pro.enaza.gb.gbmodile_room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pro.enaza.gb.shared_model.room.GameCardEntity
import pro.enaza.gb.shared_model.room.contract.GameCardContract

@Dao
interface GameCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: GameCardEntity)

    @Query("SELECT * FROM ${GameCardContract.table} WHERE ${GameCardContract.Column.name} LIKE '%' || :query || '%'")
    suspend fun getQuery(query: String): List<GameCardEntity>

    @Query("DELETE FROM ${GameCardContract.table}")
    suspend fun clear()
}