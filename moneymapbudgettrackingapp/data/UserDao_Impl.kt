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
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUserEntity: EntityInsertAdapter<UserEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfUserEntity = object : EntityInsertAdapter<UserEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `users` (`id`,`fullname`,`email`,`password`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: UserEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.fullname)
        statement.bindText(3, entity.email)
        statement.bindText(4, entity.password)
      }
    }
  }

  public override suspend fun insert(user: UserEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfUserEntity.insert(_connection, user)
  }

  public override suspend fun loginUser(email: String, password: String): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE email = ? AND password = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        _argIndex = 2
        _stmt.bindText(_argIndex, password)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfFullname: Int = getColumnIndexOrThrow(_stmt, "fullname")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpFullname: String
          _tmpFullname = _stmt.getText(_columnIndexOfFullname)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          _result = UserEntity(_tmpId,_tmpFullname,_tmpEmail,_tmpPassword)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserByEmail(email: String): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE email = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfFullname: Int = getColumnIndexOrThrow(_stmt, "fullname")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpFullname: String
          _tmpFullname = _stmt.getText(_columnIndexOfFullname)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          _result = UserEntity(_tmpId,_tmpFullname,_tmpEmail,_tmpPassword)
        } else {
          _result = null
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
