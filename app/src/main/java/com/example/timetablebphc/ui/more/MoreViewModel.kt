package com.example.timetablebphc.ui.more

import android.app.Application
import androidx.lifecycle.*
import com.example.timetablebphc.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoreViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    fun signOut() = viewModelScope.launch(Dispatchers.IO){
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.g_api_key.toString())
            .requestEmail()
            .build()

        googleSignInClient = context.let { GoogleSignIn.getClient(it, gso) }!!
        // Firebase sign out
        auth.signOut()
        googleSignInClient.signOut()
        // Google sign out
    }
}