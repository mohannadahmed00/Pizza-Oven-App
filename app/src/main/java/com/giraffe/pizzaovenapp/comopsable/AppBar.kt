package com.giraffe.pizzaovenapp.comopsable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.giraffe.pizzaovenapp.ui.theme.PizzaOvenAppTheme

@Composable
fun AppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Arrow Back")
        Text(text = "Pizza", style = MaterialTheme.typography.titleMedium)
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
    }
}

@Preview
@Composable
private fun Preview() {
    PizzaOvenAppTheme { AppBar() }
}