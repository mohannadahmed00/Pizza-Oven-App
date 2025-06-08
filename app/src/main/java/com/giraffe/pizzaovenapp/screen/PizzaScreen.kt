package com.giraffe.pizzaovenapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giraffe.pizzaovenapp.R
import com.giraffe.pizzaovenapp.comopsable.AppBar
import com.giraffe.pizzaovenapp.ui.theme.PizzaOvenAppTheme
import com.giraffe.pizzaovenapp.ui.theme.brown
import com.giraffe.pizzaovenapp.ui.theme.red

@Composable
fun PizzaScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(red)
            )
            Text(text = "$17", style = MaterialTheme.typography.titleLarge)
            Row(
                modifier = Modifier.padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(text = "S", style = MaterialTheme.typography.titleMedium)
                Text(text = "M", style = MaterialTheme.typography.titleMedium)
                Text(text = "L", style = MaterialTheme.typography.titleMedium)
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = "CUSTOMIZE YOUR PIZZA", style = MaterialTheme.typography.labelSmall
            )
            LazyRow(
                modifier = Modifier.padding(vertical = 32.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(5) {
                    Image(painter = painterResource(R.drawable.basil_1), contentDescription = "")
                }
            }
            Button(
                modifier = Modifier.padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors().copy(containerColor = brown),
                onClick = {}) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")
                    Text(text = "Add to cart", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PizzaOvenAppTheme {
        PizzaScreen()
    }
}