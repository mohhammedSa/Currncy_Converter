package com.Currenverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var fromList: AutoCompleteTextView
    private lateinit var toList: AutoCompleteTextView
    private lateinit var amountTV: TextInputEditText
    private lateinit var resultTV: TextInputEditText

    private val mad = "Moroccan Dirhams"
    private val rial = "Saudi Rial"
    private val dinar = "Algerian Dinar"
    private val pound = "Egyptian Pound"

    private val values = mapOf(
        mad to 1.00,
        rial to 0.34,
        dinar to 12.82,
        pound to 1.78
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialise()
        setAdapter()

        convert()
        fromList.setOnItemClickListener { _, _, _, _ ->
            convert()
        }
        toList.setOnItemClickListener { _, _, _, _ ->
            convert()
        }
        amountTV.doOnTextChanged { _, _, _, _ ->
            convert()
        }
    }

    private fun initialise() {
        fromList = findViewById(R.id.from_list_auto)
        toList = findViewById(R.id.to_list_auto)
        amountTV = findViewById(R.id.amount_field)
        resultTV = findViewById(R.id.result_field)
    }

    private fun setAdapter() {
        val currencyList = listOf(mad, rial, dinar, pound)
        val myAdapter = ArrayAdapter(this, R.layout.list, currencyList)
        fromList.setAdapter(myAdapter)
        toList.setAdapter(myAdapter)
    }

    private fun convert() {
        if(amountTV.text.toString().isNotEmpty()) {
            val amountNum = amountTV.text.toString().toDouble()
            val from = values[fromList.text.toString()]
            val to = values[toList.text.toString()]

            val resultNum = amountNum.times(to!!).div(from!!)
            resultTV.setText(resultNum.toString())
        }
        else amountTV.error = "Field Required"
    }
}