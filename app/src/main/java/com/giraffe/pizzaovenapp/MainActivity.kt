package com.giraffe.pizzaovenapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.giraffe.pizzaovenapp.screen.PizzaScreen
import com.giraffe.pizzaovenapp.ui.theme.PizzaOvenAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzaOvenAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    PizzaScreen()
                }
            }
        }
    }
}