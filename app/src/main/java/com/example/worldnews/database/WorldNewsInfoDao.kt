package com.example.worldnews.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.worldnews.data.Results

@Dao
interface WorldNewsInfoDao {

    @Query("SELECT * FROM world_news_list")
    fun getWorldNewsList(): LiveData<List<Results>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorldNewsList(worldNewsList: List<Results> )

    @Query("DELETE FROM world_news_list")
    fun clearedWorldNewsList(): Int
}