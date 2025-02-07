package com.betulesen.phototag.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoTag::class] , version = 1)
abstract class PhotoTagDatabase : RoomDatabase() {
    abstract fun photoTagDao() : PhotoTagDao
}