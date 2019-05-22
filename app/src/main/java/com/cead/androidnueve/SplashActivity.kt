package com.cead.androidnueve

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils

class SplashActivity : AppCompatActivity() {



    var sharedPrefs: SharedPreferences ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs =  getSharedPreferences("Preferences", Context.MODE_PRIVATE)

        var intentLogin = Intent(this@SplashActivity, LoginActivity::class.java)
        var intentMain = Intent(this@SplashActivity, MainActivity::class.java)

        if(!TextUtils.isEmpty(getUserMailPrefs()) && !getUserMailPrefs()!!.equals("noexiste") &&
            !TextUtils.isEmpty(getUserPassPrefs()) && !getUserPassPrefs()!!.equals("noexiste")){
            startActivity(intentMain)
        }else{
            startActivity(intentLogin)
        }

        finish() //matar la instancia del activity
    }

    private fun getUserMailPrefs(): String?{
        return sharedPrefs!!.getString("email","noexiste")

    }

    private fun getUserPassPrefs(): String?{
        return sharedPrefs!!.getString("pass","noexiste")
    }

}
