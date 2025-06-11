package com.giraffe.pizzaovenapp.comopsable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.giraffe.pizzaovenapp.model.PizzaUiState

@Composable
fun Pizza(
    modifier: Modifier = Modifier,
    state: PizzaUiState,
    pizzaSize: Float
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(pizzaSize),
            painter = painterResource(state.bread),
            contentDescription = "bread"
        )
        state.toppings.forEach { topping ->
            Topping(
                pizzaSize = pizzaSize,
                topping = topping,
            )
        }
    }
}