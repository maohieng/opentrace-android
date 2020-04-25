package kh.edu.niptict.opentrace

import android.content.Context
import android.content.SharedPreferences

object Preference {
    private const val PREF_ID = "Tracer_pref"
    private const val IS_ONBOARDED = "IS_ONBOARDED"
    private const val PHONE_NUMBER = "PHONE_NUMBER"
    private const val CHECK_POINT = "CHECK_POINT"
    private const val HANDSHAKE_PIN = "HANDSHAKE_PIN"

    private const val NEXT_FETCH_TIME = "NEXT_FETCH_TIME"
    private const val EXPIRY_TIME = "EXPIRY_TIME"
    private const val LAST_FETCH_TIME = "LAST_FETCH_TIME"

    private const val LAST_PURGE_TIME = "LAST_PURGE_TIME"

    private const val ANNOUNCEMENT = "ANNOUNCEMENT"

    private fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE);
    }

    fun putHandShakePin(context: Context, value: String) {
        getSharedPreference(context)
            .edit().putString(HANDSHAKE_PIN, value).apply()
    }

    fun getHandShakePin(context: Context): String {
        return getSharedPreference(context)
            .getString(HANDSHAKE_PIN, "AERTVC") ?: "AERTVC"
    }

    fun putIsOnBoarded(context: Context, value: Boolean) {
        getSharedPreference(context)
            .edit().putBoolean(IS_ONBOARDED, value).apply()
    }

    fun isOnBoarded(context: Context): Boolean {
        return getSharedPreference(context)
            .getBoolean(IS_ONBOARDED, false)
    }

    fun putPhoneNumber(context: Context, value: String) {
        getSharedPreference(context)
            .edit().putString(PHONE_NUMBER, value).apply()
    }

    fun getPhoneNumber(context: Context): String {
        return getSharedPreference(context)
            .getString(PHONE_NUMBER, "") ?: ""
    }

    fun putCheckpoint(context: Context, value: Int) {
        getSharedPreference(context)
            .edit().putInt(CHECK_POINT, value).apply()
    }

    fun getCheckpoint(context: Context): Int {
        return getSharedPreference(context)
            .getInt(CHECK_POINT, 0)
    }

    fun getLastFetchTimeInMillis(context: Context): Long {
        return getSharedPreference(context)
            .getLong(
                LAST_FETCH_TIME, 0
            )
    }

    fun putLastFetchTimeInMillis(context: Context, time: Long) {
        getSharedPreference(context)
            .edit().putLong(LAST_FETCH_TIME, time).apply()
    }

    fun putNextFetchTimeInMillis(context: Context, time: Long) {
        getSharedPreference(context)
            .edit().putLong(NEXT_FETCH_TIME, time).apply()
    }

    fun getNextFetchTimeInMillis(context: Context): Long {
        return getSharedPreference(context)
            .getLong(
                NEXT_FETCH_TIME, 0
            )
    }

    fun putExpiryTimeInMillis(context: Context, time: Long) {
        getSharedPreference(context)
            .edit().putLong(EXPIRY_TIME, time).apply()
    }

    fun getExpiryTimeInMillis(context: Context): Long {
        return getSharedPreference(context)
            .getLong(
                EXPIRY_TIME, 0
            )
    }

    fun putAnnouncement(context: Context, announcement: String) {
        getSharedPreference(context)
            .edit().putString(ANNOUNCEMENT, announcement).apply()
    }

    fun getAnnouncement(context: Context): String {
        return getSharedPreference(context)
            .getString(ANNOUNCEMENT, "") ?: ""
    }

    fun putLastPurgeTime(context: Context, lastPurgeTime: Long) {
        getSharedPreference(context)
            .edit().putLong(LAST_PURGE_TIME, lastPurgeTime).apply()
    }

    fun getLastPurgeTime(context: Context): Long {
        return getSharedPreference(context)
            .getLong(LAST_PURGE_TIME, 0)
    }

    fun registerListener(
        context: Context,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        getSharedPreference(context).registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterListener(
        context: Context,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        getSharedPreference(context)
            .unregisterOnSharedPreferenceChangeListener(listener)
    }
}
