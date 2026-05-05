package com.example.moneymapbudgettrackingapp.`data`

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Double
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
public class ExpenseDao_Impl(
  __db: RoomDatabase,
) : ExpenseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfExpenseEntity: EntityInsertAdapter<ExpenseEntity>

  private val __deleteAdapterOfExpenseEntity: EntityDeleteOrUpdateAdapter<ExpenseEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfExpenseEntity = object : EntityInsertAdapter<ExpenseEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `expenses` (`id`,`amount`,`description`,`date`,`categoryId`,`photoPath`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ExpenseEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindDouble(2, entity.amount)
        statement.bindText(3, entity.description)
        statement.bindText(4, entity.date)
        statement.bindLong(5, entity.categoryId.toLong())
        val _tmpPhotoPath: String? = entity.photoPath
        if (_tmpPhotoPath == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpPhotoPath)
        }
      }
    }
    this.__deleteAdapterOfExpenseEntity = object : EntityDeleteOrUpdateAdapter<ExpenseEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `expenses` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ExpenseEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(expense: ExpenseEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfExpenseEntity.insert(_connection, expense)
  }

  public override suspend fun delete(expense: ExpenseEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfExpenseEntity.handle(_connection, expense)
  }

  public override suspend fun getAllExpenses(): List<ExpenseEntity> {
    val _sql: String = "SELECT * FROM expenses"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _result: MutableList<ExpenseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExpenseEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDate: String
          _tmpDate = _stmt.getText(_columnIndexOfDate)
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          _item = ExpenseEntity(_tmpId,_tmpAmount,_tmpDescription,_tmpDate,_tmpCategoryId,_tmpPhotoPath)
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
