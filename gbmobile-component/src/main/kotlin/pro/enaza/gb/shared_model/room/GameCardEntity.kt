package pro.enaza.gb.shared_model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import pro.enaza.gb.shared_model.local.TypeDownload
import pro.enaza.gb.shared_model.room.contract.GameCardContract

@Entity(tableName = GameCardContract.table)
data class GameCardEntity(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = GameCardContract.Column.id)
        val id: Long,
        @ColumnInfo(name = GameCardContract.Column.name)
        val name: String = "",
        @ColumnInfo(name = GameCardContract.Column.imageUrl)
        val imageUrl: String = "",
        @ColumnInfo(name = GameCardContract.Column.age)
        val age:Int = 0,
        @ColumnInfo(name = GameCardContract.Column.tagsCategories)
        val tagsCategories: String,
        @ColumnInfo(name = GameCardContract.Column.type)
        val type: TypeDownload = TypeDownload.WAIT
)
