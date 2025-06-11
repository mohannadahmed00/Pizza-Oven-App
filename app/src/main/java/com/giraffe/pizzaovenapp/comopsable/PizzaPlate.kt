package com.giraffe.pizzaovenapp.comopsable

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giraffe.pizzaovenapp.R
import com.giraffe.pizzaovenapp.model.PizzaSize
import com.giraffe.pizzaovenapp.model.PizzaTopping
import com.giraffe.pizzaovenapp.model.PizzaUiState
import com.giraffe.pizzaovenapp.ui.theme.PizzaOvenAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PizzaPlate(
    modifier: Modifier = Modifier,
    pizzaSize: PizzaSize = PizzaSize.MEDUIM,
    clickedTopping: PizzaTopping? = null,
    resetTopping: () -> Unit = {}
) {
    val pizzaUiStates = remember {
        mutableStateListOf(
            PizzaUiState(bread = R.drawable.bread_1),
            PizzaUiState(bread = R.drawable.bread_2),
            PizzaUiState(bread = R.drawable.bread_3),
            PizzaUiState(bread = R.drawable.bread_4),
            PizzaUiState(bread = R.drawable.bread_5),
        )
    }
    val pagerState = rememberPagerState { 5 }
    val pizzaAnimatedSize = remember { Animatable(pizzaSize.value) }
    LaunchedEffect(pizzaSize) {
        pizzaAnimatedSize.animateTo(pizzaSize.value)
    }
    LaunchedEffect(clickedTopping) {
        Log.d("messi", "PizzaPlate: clickedTopping = $clickedTopping")
        if (clickedTopping != null) {
            val currentPizza = pizzaUiStates[pagerState.currentPage]
            val newToppings = if (clickedTopping in currentPizza.toppings) {
                currentPizza.toppings - clickedTopping
            } else {
                currentPizza.toppings + clickedTopping
            }
            pizzaUiStates[pagerState.currentPage] = currentPizza.copy(toppings = newToppings)
            resetTopping()
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.padding(horizontal = 16.dp),
            painter = painterResource(R.drawable.plate), contentDescription = "plate")
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 5

        ) { page ->
            Pizza(state = pizzaUiStates[page], pizzaSize = pizzaSize.value)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PizzaOvenAppTheme {
        PizzaPlate()
    }
}