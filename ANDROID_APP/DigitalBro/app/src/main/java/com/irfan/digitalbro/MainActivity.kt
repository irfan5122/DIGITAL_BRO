package com.irfan.digitalbro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.irfan.digitalbro.ui.theme.DigitalBroTheme
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
class MainActivity : ComponentActivity() {
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalBroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Digital Bro",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    private fun sendTestData() {
        val request = Request.Builder()
            .url("http://192.168.1.36:8000/usage")
            .post(
                RequestBody.create(
                    "application/json".toMediaType(),
                    """{"message":"Hello from DigitalBro"}"""
                )
            )
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Request failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                println("Server response: ${response.code}")
                response.close()
            }
        })
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name! Welcome",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DigitalBroTheme {
        Greeting("Digital Bro")
    }
}