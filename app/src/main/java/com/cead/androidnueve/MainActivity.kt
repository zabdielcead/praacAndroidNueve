package com.cead.androidnueve

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // preferencias compartidas
    // session storage y local storage
    // splash screen lanza una pagina antes de que empiece la aplicacion


    var prefs : SharedPreferences ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_logout -> {
                logOut()
                return true
            }R.id.menu_forget_logout -> {
                removeSharedPreferences()
                logOut()
                return true
            } else -> {
                return super.onOptionsItemSelected(item)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun logOut(){
        var intent  = Intent(this@MainActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun removeSharedPreferences(){
       prefs!!.edit().clear().apply()
    }


}
