package effective.office.marvelproject.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    @ColumnInfo("label") val label: String,
    @ColumnInfo("next_key") val nextKey: Int?
)
