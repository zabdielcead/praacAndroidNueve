package com.cead.androidnueve

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    // preferencias compartidas
    // session storage y local storage
    // splash screen lanza una pagina antes de que empiece la aplicacion

    var editTextEmail: EditText? = null
    var editTextPass: EditText? = null
    var switchSave: Switch? = null
    var btnLogin: Button? = null


    var sharedPrefs : SharedPreferences ?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        bindUI()

        sharedPrefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE) //lectura

        setCredentialsIfExist()

        btnLogin!!.setOnClickListener{
            if(login(editTextPass!!.text.toString(), editTextEmail!!.text.toString()) ){
                saveOnPreferences(editTextEmail!!.text.toString(), editTextPass!!.text.toString())
                goToMain()

            }

        }
    }

    private fun setCredentialsIfExist() {
        var emails: String? = getUserMailPrefs()
        var pass: String? = getUserPassPrefs()

        if(!TextUtils.isEmpty(emails) && !TextUtils.isEmpty(pass)){
            editTextEmail!!.setText(emails)
            editTextPass!!.setText(pass)
        }
    }

    private fun getUserMailPrefs(): String?{
        return sharedPrefs!!.getString("email","noexiste")

    }

    private fun getUserPassPrefs(): String?{
        return sharedPrefs!!.getString("pass","noexiste")
    }

    private fun bindUI(){
        editTextEmail = findViewById(R.id.edTextEmail)
        editTextPass  = findViewById(R.id.edTextPassword)
        switchSave  = findViewById(R.id.switchRemember)
        btnLogin = findViewById(R.id.btnLogin)



    }

    private fun saveOnPreferences(email: String, password:String){
        if(switchSave!!.isChecked){
             var editor: SharedPreferences.Editor = sharedPrefs!!.edit()//escritura
             editor.putString("email", email)
             editor.putString("pass", password)
             //editor.commit()  //devuelve un boolean  el codigo se para hasta que se guarden todos los registro sincorono
             editor.apply() // asincrono sigue aunque no se hayan guardado

        }
    }

    private fun login(email: String, password: String):Boolean{
        if ( !isValidMail(email)  ){
            Toast.makeText(this," Email is not valid, again", Toast.LENGTH_LONG).show()
            return false
        } else if(!isValidPassword(password)){
            Toast.makeText(this," Password is not valid, again", Toast.LENGTH_LONG).show()
            return false
        }else{
            return true
        }
    }

    private fun isValidMail(email: String): Boolean{

        return !TextUtils.isEmpty(email) && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(pass: String): Boolean{
        return pass.length >= 4
    }

    private fun goToMain(){
        var intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK  // no qeremos que pase activity anterior con la flecha de atras, se cierra la aplicacion
        startActivity(intent)
    }

}
