package uz.megasoft.newapp

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext context: Context) {

    private var mySharedPref: SharedPreferences =
        context.getSharedPreferences("newApp", Context.MODE_PRIVATE)

    var uid: String
        get() = mySharedPref.getString("uid", "")!!
        set(value) {
            mySharedPref.edit().putString("uid", value).apply()
        }

    var name: String
        get() = mySharedPref.getString("name", "AS")!!
        set(value) {
            mySharedPref.edit().putString("name", value).apply()
        }

}