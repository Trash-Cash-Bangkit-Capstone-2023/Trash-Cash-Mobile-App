package com.example.trashcash.helper

import com.google.firebase.auth.FirebaseAuth

object FirebaseUtils {
    fun getIdToken(callback: (String?) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.getIdToken(true)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val idToken = task.result?.token
                    callback(idToken)
                } else {
                    callback(null)
                }
            }
    }

    fun logout() {
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
    }
}