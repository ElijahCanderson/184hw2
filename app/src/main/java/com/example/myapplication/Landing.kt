package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class Landing: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email = intent.getStringExtra("EMAIL") ?: "No email"
        val username = intent.getStringExtra("USERNAME") ?: "No username"
        val githubId = intent.getStringExtra("GITHUB_ID") ?: "No ID"

        setContent {
            MyApplicationTheme {
                LandingScreen(email = email, username = username, githubId = githubId)
            }
        }
    }

    @Composable
    fun LandingScreen(email: String, username: String, githubId: String) {
        var selectedTabIndex by remember { mutableStateOf(0) }
        val navController = rememberNavController()




        Column {
            TabRow(selectedTabIndex = selectedTabIndex) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = {
                        selectedTabIndex = 0
                        navController.navigate("home") 
                    },
                    text = { Text("Home") }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = {
                        selectedTabIndex = 1
                        navController.navigate("info")
                    },
                    text = { Text("Info") }
                )
                Tab(
                    selected = selectedTabIndex == 2,
                    onClick = {
                        selectedTabIndex = 2
                        navController.navigate("settings")
                    },
                    text = { Text("Settings") }
                )
            }
            NavHost(navController, startDestination = "home") {
                composable("home") { HomeScreen(email, username, githubId) }
                composable("info") {InfoScreen(email, username, githubId) }
                composable("settings") { SettingsScreen() }
            }
        }
    }

    @Composable
    fun HomeScreen(email: String, username: String, githubId: String) {
        Column {
            Text(text = "Home Screen")
            Text(text = "Email: $email")
            Text(text = "Username: $username")
            Text(text = "GitHub ID: $githubId")
        }
    }

    @Composable
    fun InfoScreen(email: String, username: String, githubId: String) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "The FITNESS", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp)) // Space between title and content

            Text(text = "The FitnessGram™ Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds. Line up at the start. The running speed starts slowly, but gets faster each minute after you hear this signal. [beep] A single lap should be completed each time you hear this sound. [ding] Remember to run in a straight line, and run as long as possible. The second time you fail to complete a lap before the sound, your test is over. The test will begin on the word start. On your mark, get ready, start.")
        }
    }

    @Composable
    fun SettingsScreen() {
        Column {
            Text(text = "SIKE I DO NOT HAVE THE SKILLS FOR SETTINGS")
        }
    }
}

