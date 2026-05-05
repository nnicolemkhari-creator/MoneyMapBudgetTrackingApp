package com.example.moneymapbudgettrackingapp.`data`

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _expenseDao: Lazy<ExpenseDao> = lazy {
    ExpenseDao_Impl(this)
  }

  private val _userDao: Lazy<UserDao> = lazy {
    UserDao_Impl(this)
  }

  private val _categoryDao: Lazy<CategoryDao> = lazy {
    CategoryDao_Impl(this)
  }

  private val _goalDao: Lazy<GoalDao> = lazy {
    GoalDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "2b10c75ef009dec0b2b54489bf2d87d4", "b5b313a92e08893dee45b734a2e7a9cf") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fullname` TEXT NOT NULL, `email` TEXT NOT NULL, `password` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `CategoryName` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `amount` REAL NOT NULL, `description` TEXT NOT NULL, `date` TEXT NOT NULL, `categoryId` INTEGER NOT NULL, `photoPath` TEXT)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `goals` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `minGoal` REAL NOT NULL, `maxGoal` REAL NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2b10c75ef009dec0b2b54489bf2d87d4')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `users`")
        connection.execSQL("DROP TABLE IF EXISTS `categories`")
        connection.execSQL("DROP TABLE IF EXISTS `expenses`")
        connection.execSQL("DROP TABLE IF EXISTS `goals`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsUsers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsers.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("fullname", TableInfo.Column("fullname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("email", TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("password", TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUsers: TableInfo = TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers)
        val _existingUsers: TableInfo = read(connection, "users")
        if (!_infoUsers.equals(_existingUsers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |users(com.example.moneymapbudgettrackingapp.data.UserEntity).
              | Expected:
              |""".trimMargin() + _infoUsers + """
              |
              | Found:
              |""".trimMargin() + _existingUsers)
        }
        val _columnsCategories: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCategories.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("CategoryName", TableInfo.Column("CategoryName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCategories: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCategories: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCategories: TableInfo = TableInfo("categories", _columnsCategories, _foreignKeysCategories, _indicesCategories)
        val _existingCategories: TableInfo = read(connection, "categories")
        if (!_infoCategories.equals(_existingCategories)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |categories(com.example.moneymapbudgettrackingapp.data.CategoryEntity).
              | Expected:
              |""".trimMargin() + _infoCategories + """
              |
              | Found:
              |""".trimMargin() + _existingCategories)
        }
        val _columnsExpenses: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsExpenses.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("amount", TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("description", TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("date", TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("categoryId", TableInfo.Column("categoryId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("photoPath", TableInfo.Column("photoPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysExpenses: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesExpenses: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoExpenses: TableInfo = TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses, _indicesExpenses)
        val _existingExpenses: TableInfo = read(connection, "expenses")
        if (!_infoExpenses.equals(_existingExpenses)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |expenses(com.example.moneymapbudgettrackingapp.data.ExpenseEntity).
              | Expected:
              |""".trimMargin() + _infoExpenses + """
              |
              | Found:
              |""".trimMargin() + _existingExpenses)
        }
        val _columnsGoals: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsGoals.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGoals.put("userId", TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGoals.put("minGoal", TableInfo.Column("minGoal", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGoals.put("maxGoal", TableInfo.Column("maxGoal", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysGoals: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesGoals: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoGoals: TableInfo = TableInfo("goals", _columnsGoals, _foreignKeysGoals, _indicesGoals)
        val _existingGoals: TableInfo = read(connection, "goals")
        if (!_infoGoals.equals(_existingGoals)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |goals(com.example.moneymapbudgettrackingapp.data.GoalEntity).
              | Expected:
              |""".trimMargin() + _infoGoals + """
              |
              | Found:
              |""".trimMargin() + _existingGoals)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "users", "categories", "expenses", "goals")
  }

  public override fun clearAllTables() {
    super.performClear(false, "users", "categories", "expenses", "goals")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ExpenseDao::class, ExpenseDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(UserDao::class, UserDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CategoryDao::class, CategoryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(GoalDao::class, GoalDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun expenseDao(): ExpenseDao = _expenseDao.value

  public override fun userDao(): UserDao = _userDao.value

  public override fun categoryDao(): CategoryDao = _categoryDao.value

  public override fun goalDao(): GoalDao = _goalDao.value
}
