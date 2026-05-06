package com.example.appprototype

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    private var currentType = "Expense"
    private var selectedCategory = "Food"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        db = AppDatabase.getDatabase(this)

        val amountEditText = findViewById<EditText>(R.id.amountEditText)
        val dateEditText = findViewById<EditText>(R.id.dateEditText)
        val descriptionEditText = findViewById<EditText>(R.id.descriptionEditText)
        val saveButton = findViewById<Button>(R.id.saveTransactionButton)
        val toggleGroup = findViewById<MaterialButtonToggleGroup>(R.id.toggleGroup)
        val accountDropdown =
            findViewById<com.google.android.material.textfield.MaterialAutoCompleteTextView>(R.id.accountDropdown)
        val closeBtn = findViewById<ImageView>(R.id.btnClose)

        closeBtn.setOnClickListener { finish() }

        // ---------------- ACCOUNT DROPDOWN ----------------
        val accounts = arrayOf("Bank Account", "Cash", "Savings")
        accountDropdown.setAdapter(
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, accounts)
        )

        // ---------------- TYPE TOGGLE ----------------
        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                currentType = if (checkedId == R.id.btnIncome) "Income" else "Expense"
            }
        }

        // ---------------- DATE PICKER ----------------
        dateEditText.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            picker.addOnPositiveButtonClickListener {
                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                dateEditText.setText(sdf.format(Date(it)))
            }

            picker.show(supportFragmentManager, "DATE_PICKER")
        }

        // ---------------- AMOUNT FORMAT ----------------
        amountEditText.addTextChangedListener(object : TextWatcher {
            private var editing = false

            override fun afterTextChanged(s: Editable?) {
                if (editing) return

                editing = true
                val clean = s.toString().replace("[^\\d]".toRegex(), "")
                val value = clean.toDoubleOrNull() ?: 0.0

                val formatted = NumberFormat
                    .getCurrencyInstance(Locale("en", "ZA"))
                    .format(value / 100)

                amountEditText.setText(formatted)
                amountEditText.setSelection(amountEditText.text.length)

                editing = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // ---------------- CATEGORY CLICK SETUP ----------------
        setupCategories()

        // ---------------- SAVE TRANSACTION ----------------
        saveButton.setOnClickListener {

            val description = descriptionEditText.text.toString()
            val date = dateEditText.text.toString()
            val account = accountDropdown.text.toString().ifEmpty { "Bank Account" }

            val raw = amountEditText.text.toString()
                .replace("[^\\d]".toRegex(), "")
                .toDoubleOrNull() ?: 0.0

            val amount = raw / 100

            if (description.isBlank() || date.isBlank() || amount <= 0) {
                Toast.makeText(this, "Please complete all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val transaction = Transaction(
                title = description,
                amount = amount,
                date = date,
                category = selectedCategory,
                account = account,
                type = currentType
            )

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    db.transactionDao().insertTransaction(transaction)
                }
                finish()
            }
        }
    }

    // ---------------- CATEGORY SELECTION ----------------
    private fun setupCategories() {

        val food = findViewById<View>(R.id.category_food)
        val transport = findViewById<View>(R.id.category_transport)
        val bills = findViewById<View>(R.id.category_bills)
        val shopping = findViewById<View>(R.id.category_shopping)
        val entertainment = findViewById<View>(R.id.category_entertainment)
        val health = findViewById<View>(R.id.category_health)

        val all = listOf(food, transport, bills, shopping, entertainment, health)

        fun select(view: View, name: String) {

            selectedCategory = name

            all.forEach {
                it.setBackgroundResource(R.drawable.category_item_bg)
            }

            view.setBackgroundResource(R.drawable.category_selected_bg)
        }

        food.setOnClickListener { select(food, "Food") }
        transport.setOnClickListener { select(transport, "Transport") }
        bills.setOnClickListener { select(bills, "Bills") }
        shopping.setOnClickListener { select(shopping, "Shopping") }
        entertainment.setOnClickListener { select(entertainment, "Entertainment") }
        health.setOnClickListener { select(health, "Health") }

        food.performClick()
    }
}