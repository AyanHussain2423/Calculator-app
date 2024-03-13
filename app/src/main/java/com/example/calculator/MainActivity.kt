package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var lstnumeric = false
    var stateerror = false
    var lastdot = false

    private lateinit var expression: Expression

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onbackclick(view: View) {
        binding.datatv.text = binding.datatv.text.toString().dropLast(1)
        try{
            val lastd = binding.datatv.text.last()
            if (lastd.isDigit()){
                equal()
            }
            else{
                binding.resulttv.text=""
            }
        }catch (e:Exception){

        }

    }
    fun onclearclick(view: View) {

        binding.datatv.text = ""
        lstnumeric = false
        stateerror = false
        lastdot = false
    }
    fun onequalclick(view: View) {
        binding.datatv.text = ""
        binding.resulttv.textSize = 32f
        lstnumeric = false
        stateerror = false
        lastdot = false
    }
    fun ondegitclick(view: View) {
        if (stateerror){
            binding.datatv.text = (view as Button).text
        }
        else{
            binding.datatv.append((view as Button).text)
        }
        lstnumeric = true
        equal()
    }
    fun onallclear(view: View) {
        binding.datatv.text = ""
        binding.resulttv.text = ""
        lstnumeric = false
        stateerror = false
        lastdot = false
    }
    fun onopratorclick(view: View) {
        if (lstnumeric&&!stateerror){
            binding.datatv.append((view as Button).text)
            lstnumeric = false
            lastdot = false
            equal()
        }
    }

    fun equal(){
        if (!stateerror&&lstnumeric){
            val txt = binding.datatv.text.toString()
            expression = ExpressionBuilder(txt).build()
            try{
                val result = expression.evaluate()
                binding.resulttv.visibility = View.VISIBLE
                binding.resulttv.text = "=" + result.toString()
            }
            catch (ex: ArithmeticException){
                Log.d("error gotchya", ex.toString())
                binding.resulttv.text = "error"
                stateerror = true
                lstnumeric = false

            }


        }

    }
}