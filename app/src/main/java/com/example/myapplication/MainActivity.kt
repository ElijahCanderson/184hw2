package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp
import android.widget.Toast
import com.google.firebase.auth.OAuthProvider
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        setContent {
            MyApplicationTheme {
                Buttons()
            }
        }
    }

    private fun signInWithGithub() {
        val provider = OAuthProvider.newBuilder("github.com")
    
        provider.addCustomParameter("allow_signup", "false")
        auth.startActivityForSignInWithProvider(this, provider.build())
            .addOnSuccessListener { authResult ->
                val user = authResult.user
                val email = user?.email ?: "No email"
                val username = user?.displayName ?: email.substringBefore("@")
                val githubId = user?.uid ?: "No ID"

                val intent = Intent(this, Landing::class.java).apply {
                    putExtra("EMAIL", email)
                    putExtra("USERNAME", username)
                    putExtra("GITHUB_ID", githubId)
                }
                Toast.makeText(
                    this,
                    "Sign-in successful: ${authResult.user?.email}",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
               
                Toast.makeText(this, "Sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    @Composable
    fun Buttons() {
        Box(
            modifier = Modifier
                .fillMaxSize() 
                .padding(16.dp), 
            contentAlignment = Alignment.Center 
        ) {
            Button(onClick = { signInWithGithub() }) {
                Text("Github Sign In")
            }
        }
    }
}
