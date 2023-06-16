package id.plantaniteam.plantaniapp.session

import android.content.Context
import android.content.SharedPreferences
import id.plantaniteam.plantaniapp.data.model.User

class SessionManager(context: Context) {
    private val session: SharedPreferences = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE)

    fun setUserSession(user: User) {
        with(session.edit()) {
            putString(SESSION_UID, user._id)
            apply()
        }
    }

    fun getUserSession(): String {
        val id: String? = session.getString(SESSION_UID, "")
        return id.toString()
    }

    fun clearUserSession() {
        session.edit().clear().apply()
    }


    companion object {
        private const val SESSION = "session"
        private const val SESSION_UID = "session_uid"
    }
}