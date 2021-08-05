package com.androiddev.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var typeHere :TextView
    var lastNumeric: Boolean = false
    var stateError: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        typeHere = findViewById(R.id.typeHere)
    }
    fun digit(view: View){
        if(stateError){
            typeHere.text = (view as Button).text
            stateError = false
        }
        else {
            typeHere.append((view as Button).text)
        }
        lastNumeric = true
    }

    fun decimalpt(view: View){
        if(lastNumeric && !stateError && !lastDot){
            typeHere.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun operator(view: View){
        if(lastNumeric && !stateError){
            typeHere.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun clr(view: View){
        this.typeHere.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    fun equal(view: View){
        if(lastNumeric && !stateError){
            val txt = typeHere.text.toString()
            val expression = ExpressionBuilder(txt).build()
            try{
                val result = expression.evaluate()
                typeHere.text = result.toString()
                lastDot = true
            }catch (ex: ArithmeticException){
                typeHere.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }
}