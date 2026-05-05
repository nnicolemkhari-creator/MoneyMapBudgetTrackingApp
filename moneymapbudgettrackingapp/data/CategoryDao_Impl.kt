package com.example.moneymapbudgettrackingapp.`data`

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CategoryDao_Impl(
  __db: RoomDatabase,
) : CategoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCategoryEntity: EntityInsertAdapter<CategoryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCategoryEntity = object : EntityInsertAdapter<CategoryEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `categories` (`id`,`CategoryName`) VALUES (nullif(?, 0),?)"

      protected override fun bind(statement: SQLiteStatement, entity: CategoryEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.CategoryName)
      }
    }
  }

  public override suspend fun insertCategory(category: CategoryEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCategoryEntity.insert(_connection, category)
  }

  public override suspend fun getAllCategories(): List<CategoryEntity> {
    val _sql: String = "SELECT * FROM categories"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "CategoryName")
        val _result: MutableList<CategoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpCategoryName: String
          _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          _item = CategoryEntity(_tmpId,_tmpCategoryName)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
