package com.example.shoppingapp.data.db.dao

import androidx.room.*
import com.example.shoppingapp.data.entity.product.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
     fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE id=:id")
     fun getById(id: Long): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(ProductEntity: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(ProductEntityList: List<ProductEntity>)

    @Query("DELETE FROM ProductEntity WHERE id=:id")
     fun delete(id: Long)

    @Query("DELETE FROM ProductEntity")
     fun deleteAll()

    @Update
     fun update(ProductEntity: ProductEntity)

}