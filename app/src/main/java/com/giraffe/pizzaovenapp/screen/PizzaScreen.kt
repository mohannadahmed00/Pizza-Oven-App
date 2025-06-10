package com.giraffe.pizzaovenapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giraffe.pizzaovenapp.comopsable.AppBar
import com.giraffe.pizzaovenapp.comopsable.PizzaPlate
import com.giraffe.pizzaovenapp.model.PizzaIngredient
import com.giraffe.pizzaovenapp.model.PizzaSize
import com.giraffe.pizzaovenapp.ui.theme.PizzaOvenAppTheme
import com.giraffe.pizzaovenapp.ui.theme.brown
import com.giraffe.pizzaovenapp.ui.theme.green

@Composable
fun PizzaScreen() {
    var pizzaSize by remember { mutableStateOf(PizzaSize.MEDUIM) }
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
                    .padding(16.dp)
            )
            PizzaPlate(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                pizzaSize = pizzaSize
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = "$17", style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                PizzaSize.entries.forEach { size ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                color = if (pizzaSize == size) green.copy(.2f) else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable(
                                indication = null,
                                interactionSource = null
                            ) {
                                pizzaSize = size
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = size.name.first().toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "CUSTOMIZE YOUR PIZZA", style = MaterialTheme.typography.labelSmall
            )
            LazyRow(
                modifier = Modifier.padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(PizzaIngredient.entries) { index, item ->
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .clickable {},
                        painter = painterResource(item.images.first()),
                        contentDescription = "ingredient"
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
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