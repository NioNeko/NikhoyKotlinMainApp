package com.dicoding.nikhoyapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var extbtn: Button
    private lateinit var abut: ImageView
    private lateinit var about: TextView
    private lateinit var tlp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        extbtn = findViewById(R.id.exitbtn)
        extbtn.setOnClickListener(this)


        tlp = findViewById(R.id.tlp)
        tlp.setOnClickListener(this)

        abut = findViewById(R.id.abut)
        about = findViewById(R.id.about)

        abut.setOnClickListener(this)
        about.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.exitbtn -> finish()
            R.id.abut -> showAboutPopup()
            R.id.about -> showAboutPopup()
            R.id.tlp -> TlpMe()
        }
    }

    private fun showAboutPopup() {
        AlertDialog.Builder(this)
            .setTitle("About")
            .setMessage("Latar belakang saya, bla bla bla bla bla bla bla bla bla bla bla bla bla")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun TlpMe() {
        val phoneNumber = "081291452276"
        val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(dialPhoneIntent)

    }

    fun calculatorapp(view: View) {
        val intent = Intent(this, Calculator::class.java)
        Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()

        startActivity(intent)
    }
}