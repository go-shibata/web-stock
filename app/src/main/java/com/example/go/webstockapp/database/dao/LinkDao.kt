package com.example.go.webstockapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.go.webstockapp.database.entity.Link

@Dao
interface LinkDao {
    @Query("select * from link")
    fun getAllLinks(): LiveData<List<Link>>

    @Query("select * from link where id = :id")
    fun getLink(id: Long): LiveData<Link>

    @Insert
    fun insert(link: Link)

    @Update
    fun update(link: Link)

    @Delete
    fun delete(link: Link)
}