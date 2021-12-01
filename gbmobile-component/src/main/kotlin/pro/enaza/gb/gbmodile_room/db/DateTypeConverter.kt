package pro.enaza.gb.gbmodile_room.db

import androidx.room.TypeConverter
import pro.enaza.gb.shared_model.local.TypeDownload
import java.util.*

class TypeDownloadConverter {

    @TypeConverter
    fun toTypeDownload(value: String?): TypeDownload? = if (value == null) null else TypeDownload.valueOf(value)

    @TypeConverter
    fun toString(value: TypeDownload?): String? = value?.name
}
