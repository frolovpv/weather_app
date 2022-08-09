package com.cappasity.weatherapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cappasity.weatherapp.data.local.database.entity.CityWeatherEntity

@Dao
interface CityWeatherEntityDao {

    @Query("SELECT * FROM cityweatherentity")
    fun getAll(): List<CityWeatherEntity>

    @Query("SELECT * FROM cityweatherentity WHERE id=:id")
    fun get(id: Long): CityWeatherEntity

    @Insert()
    fun insert(vararg cityweatherentity: CityWeatherEntity)

    @Query("UPDATE cityweatherentity SET temperature=:temperature, updateat=:updateAt, weather=:weather, feelslike=:feelslike, sunrise=:sunrise, sunset=:sunset, tempmin=:tempmin, tempmax=:tempmax,windspeed=:windspeed  WHERE id=:primaryKeyId")
    fun updateItem(primaryKeyId: Long,
                   updateAt: Long,
                   temperature: String,
                   weather: String,
                   feelslike: String,
                   windspeed: String,
                   sunrise: String,
                   sunset: String,
                   tempmin: String,
                   tempmax: String
    )

//    @Query("UPDATE cityweatherentity SET temperature=:temperature, updateat=:updateAt, weather=:weather, feelslike=:feelslike, sunrise=:sunrise, sunset=:sunset, tempmin=:tempmin, tempmax=:tempmax,windspeed=:windspeed  WHERE id=:primaryKeyId")
//    fun updatetheItem(primaryKeyId: Long,
//                   updateAt: Long,
//                   temperature: String,
//                   weather: String,
//                   feelslike: String,
//                   windspeed: String,
//                   sunrise: String,
//                   sunset: String,
//                   tempmin: String,
//                   tempmax: String
//    )


    @Insert()
    fun insert(cityWeatherEntity: List<CityWeatherEntity>)

    @Query("DELETE FROM cityweatherentity WHERE id=:id")
    fun delete(id: Long)

    @Query("DELETE FROM cityweatherentity")
    fun deleteAll()
}