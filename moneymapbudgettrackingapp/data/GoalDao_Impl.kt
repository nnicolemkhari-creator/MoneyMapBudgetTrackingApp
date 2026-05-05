package com.example.moneymapbudgettrackingapp.`data`

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
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class GoalDao_Impl(
  __db: RoomDatabase,
) : GoalDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfGoalEntity: EntityInsertAdapter<GoalEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfGoalEntity = object : EntityInsertAdapter<GoalEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `goals` (`id`,`userId`,`minGoal`,`maxGoal`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GoalEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.userId.toLong())
        statement.bindDouble(3, entity.minGoal)
        statement.bindDouble(4, entity.maxGoal)
      }
    }
  }

  public override suspend fun insertGoal(goal: GoalEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfGoalEntity.insert(_connection, goal)
  }

  public override suspend fun getGoalByUser(userId: Int): GoalEntity? {
    val _sql: String = "SELECT * FROM goals WHERE userId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfMinGoal: Int = getColumnIndexOrThrow(_stmt, "minGoal")
        val _columnIndexOfMaxGoal: Int = getColumnIndexOrThrow(_stmt, "maxGoal")
        val _result: GoalEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId).toInt()
          val _tmpMinGoal: Double
          _tmpMinGoal = _stmt.getDouble(_columnIndexOfMinGoal)
          val _tmpMaxGoal: Double
          _tmpMaxGoal = _stmt.getDouble(_columnIndexOfMaxGoal)
          _result = GoalEntity(_tmpId,_tmpUserId,_tmpMinGoal,_tmpMaxGoal)
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
