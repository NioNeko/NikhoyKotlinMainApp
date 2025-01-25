package com.dicoding.nikhoyapp

import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class Calculator : AppCompatActivity(), View.OnClickListener {
    private lateinit var resultF : TextView
    private lateinit var workingF : TextView

    private lateinit var back : Button

//    numbers

//    private lateinit var num1 : Button
//    private lateinit var num2 : Button
//    private lateinit var num3 : Button
//    private lateinit var num4 : Button
//    private lateinit var num5 : Button
//    private lateinit var num6 : Button
//    private lateinit var num7 : Button
//    private lateinit var num8 : Button
//    private lateinit var num9 : Button
//    private lateinit var num0 : Button

    public lateinit var calculationNum1 : Text
    public lateinit var calculationNum2 : Text

    private val numberButtons = listOf(
        R.id.caclbtn0, R.id.caclbtn1, R.id.caclbtn2, R.id.caclbtn3,
        R.id.caclbtn4, R.id.caclbtn5, R.id.caclbtn6, R.id.caclbtn7,
        R.id.caclbtn8, R.id.caclbtn9
    )

    private val  operatorButtons = listOf(
        R.id.plus, R.id.minus, R.id.times, R.id.divide
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        back = findViewById(R.id.backhome)
        back.setOnClickListener(this)
        resultF = findViewById(R.id.resultf)
        workingF = findViewById(R.id.workingf)

//        num1 = findViewById(R.id.caclbtn1)
//        num2 = findViewById(R.id.caclbtn2)
//        num3 = findViewById(R.id.caclbtn3)
//        num4 = findViewById(R.id.caclbtn4)
//        num5 = findViewById(R.id.caclbtn5)
//        num6 = findViewById(R.id.caclbtn6)
//        num7 = findViewById(R.id.caclbtn7)
//        num8 = findViewById(R.id.caclbtn8)
//        num9 = findViewById(R.id.caclbtn9)
//        num0 = findViewById(R.id.caclbtn0)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object{
        var mp: MediaPlayer? = null
    }
    override fun onClick(v: View?){
        when (v?.id) {
            R.id.backhome-> {
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, "Back to menu!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }
    }
    override fun onResume() {
        super.onResume()

        resultF = findViewById(R.id.resultf)
        workingF = findViewById(R.id.workingf)

        var textlength = workingF.getText().length
        numberButtons.forEachIndexed { index, buttonId ->
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                try {
                    val mp = MediaPlayer.create(this, getSoundForNumber(index))
                    mp?.start()
                    mp?.setOnCompletionListener { mp.release() }
                    if (!(index == 0 && workingF.text.toString() == "0")){
                        if (index != 0 && workingF.text.toString() == "0") {
                            workingF.setText(index.toString())
                        } else {
                            workingF.append(index.toString())
                        }
                    }

                }
                catch (e: Exception){
                    Toast.makeText(this, "Error playing sound ${e.message}", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }

        operatorButtons.forEachIndexed{ index, buttonId ->
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                val mp = MediaPlayer.create(this, R.raw.click)
                mp?.start()
                mp?.setOnCompletionListener { mp.release() }
                var operators = listOf('+','-','*','/')
//
//                var nomor = workingF.text.toString().split("+","-","*","/")
//                var result = nomor[1] + nomor[2]
                if (workingF.text.toString().isNotEmpty()) {
                    if (workingF.text.toString().last() !in operators) {
                        workingF.append(operators[index].toString())
                    } else {
                        workingF.text = workingF.text.dropLast(1)
                        workingF.append(operators[index].toString())
                    }

                }

            }
        }
    }


//    play sound
    private fun getSoundForNumber(index: Int): Int {
        return when (index) {
            0 -> R.raw.mi
            1 -> R.raw.dolow
            2 -> R.raw.re
            3 -> R.raw.mi
            4 -> R.raw.fa
            5 -> R.raw.sol
            6 -> R.raw.la
            7 -> R.raw.si
            8 -> R.raw.dohigh
            9 -> R.raw.dolow
            else -> R.raw.dohigh
        }
    }

//hapus value workingF
    fun clcClear(view: View) {
        workingF.setText("")
        val mp = MediaPlayer.create(this, R.raw.click)
        mp?.start()
        mp?.setOnCompletionListener { mp.release() }
    }
//    hitung
fun clcEquals(view: View) {
    val expression = workingF.text.toString()
    if (expression.isNotEmpty()) {

        val operator = expression.find { it in listOf('+', '-', '*', '/') }

        if (operator != null) {
            val parts = expression.split(operator)

            if (parts.size == 2) {
                val operand1 = parts[0].toDoubleOrNull()
                val operand2 = parts[1].toDoubleOrNull()
//                var nomor = workingF.text.toString().split("+","-","*","/")

                if (operand1 != null && operand2 != null) {
                    val result = when (operator) {
                        '+' -> operand1 + operand2
                        '-' -> operand1 - operand2
                        '*' -> operand1 * operand2
                        '/' -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
                        else -> 0.0
                    }
//                var result = nomor[1] + nomor[2]

                    workingF.setText(result.toString())
                }
            }
        }
    }
}


}