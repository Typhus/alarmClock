package ru.taptap.journeyalarmclock.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken
import ru.taptap.journeyalarmclock.JACApplication
import java.lang.ClassCastException
import kotlin.reflect.KProperty
import com.google.gson.GsonBuilder


object Prefs {

    var name by AndroidSharedPreference("")
    var size: Int? by AndroidSharedPreference(0)
    var rage: Boolean? by AndroidSharedPreference(true)
    var rage2: Boolean? by AndroidSharedPreference(true)


    class AndroidSharedPreference<T>(private val default: T) {
        private val APP_PREFERENCES = "mysettings"

        private val settings: SharedPreferences by lazy {
            JACApplication.instance.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        }

        operator fun getValue(prefs: Prefs, property: KProperty<*>): T? {
            return when (default) {
                is String -> settings.getString(property.name, default)
                is Int -> settings.getInt(property.name, default)
                is Long -> settings.getLong(property.name, default)
                is Float -> settings.getFloat(property.name, default)
                is Boolean -> settings.getBoolean(property.name, default)
                else -> throw ClassCastException("SharedPreferences not work with that type")
            } as T
        }

        operator fun setValue(prefs: Prefs, property: KProperty<*>, value: T?) {
            when (value) {
                is String -> settings.edit().putString(property.name, value)
                is Int -> settings.edit().putInt(property.name, value)
                is Long -> settings.edit().putLong(property.name, value)
                is Float -> settings.edit().putFloat(property.name, value)
                is Boolean -> settings.edit().putBoolean(property.name, value)
                else -> throw ClassCastException("SharedPreferences not work with type of $value")
            }.apply()
        }
    }
}
