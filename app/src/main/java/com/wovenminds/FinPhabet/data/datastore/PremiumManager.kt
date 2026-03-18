package com.wovenminds.FinPhabet.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PremiumManager (private val context: Context)
{
    private val Context.dataStore by preferencesDataStore("premium_store")

    companion object
    {
        val PREMIUM_KEY = booleanPreferencesKey("premium_unlocked")
    }

    suspend fun setPremiumUnlocked() {
        context.dataStore.edit { prefs ->
            prefs[PREMIUM_KEY] = true
        }
    }

        val isPremiumUnlocked: Flow<Boolean> = context.dataStore.data.map{ prefs ->
            prefs[PREMIUM_KEY] ?: false
        }
    suspend fun isPremiumUnlocked(): Boolean
    {
        val prefs = context.dataStore.data.first()
        return prefs[PREMIUM_KEY]?:false
    }
}