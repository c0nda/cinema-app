package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.cinemaapp.data.local.room.entities.ActorDB

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertActor(actor: ActorDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllActors(actors: List<ActorDB>)

}