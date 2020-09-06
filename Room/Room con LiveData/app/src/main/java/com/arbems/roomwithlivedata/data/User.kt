package com.arbems.roomwithlivedata.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String?,
    @ColumnInfo val lastName: String?
)