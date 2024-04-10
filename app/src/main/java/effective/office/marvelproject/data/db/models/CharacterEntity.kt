package effective.office.marvelproject.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo("character_id") val id: Int,
    @ColumnInfo("character_name") val name: String,
    @ColumnInfo("character_description") val description: String,
    @ColumnInfo("character_image") val image: String
)
