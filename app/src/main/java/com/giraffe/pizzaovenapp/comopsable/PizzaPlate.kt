package com.giraffe.pizzaovenapp.comopsable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giraffe.pizzaovenapp.R
import com.giraffe.pizzaovenapp.model.PizzaSize
import com.giraffe.pizzaovenapp.model.PizzaTopping
import com.giraffe.pizzaovenapp.ui.theme.PizzaOvenAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PizzaPlate(
    modifier: Modifier = Modifier,
    pizzaSize: PizzaSize = PizzaSize.MEDUIM,
    toppings: Set<PizzaTopping> = setOf<PizzaTopping>()
) {
    val breads by remember {
        mutableStateOf(
            listOf(
                R.drawable.bread_1,
                R.drawable.bread_2,
                R.drawable.bread_3,
                R.drawable.bread_4,
                R.drawable.bread_5,
            )
        )
    }
    val pagerState = rememberPagerState { 5 }
    val toppingAnimatedSize by animateDpAsState(targetValue = if (PizzaTopping.BASIL in toppings) 50.dp else 300.dp)
    val pizzaAnimatedSize = remember { Animatable(pizzaSize.value) }
    val toppingAnimatedOpacity = remember { Animatable(0f) }
    LaunchedEffect(pizzaSize) {
        pizzaAnimatedSize.animateTo(pizzaSize.value)
    }
    LaunchedEffect(PizzaTopping.BASIL in toppings) {
        if (PizzaTopping.BASIL in toppings) {
            toppingAnimatedOpacity.animateTo(1f)
        } else {
            toppingAnimatedOpacity.snapTo(0f)
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(R.drawable.plate), contentDescription = "plate")
        HorizontalPager(
            modifier = Modifier,
            state = pagerState
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(pizzaAnimatedSize.value),
                    painter = painterResource(breads[page]),
                    contentDescription = "bread"
                )
                Topping(
                    pizzaSize = pizzaAnimatedSize.value,
                    topping = PizzaTopping.BASIL,
                    toppingAnimatedSize = toppingAnimatedSize,
                    toppingAnimatedOpacity = toppingAnimatedOpacity.value
                )
                /*if (PizzaTopping.BASIL in pizzas[page].topping) {
                    Spread(
                        pizzaSize = animatedPizzaSize.value,
                        topping = PizzaTopping.BASIL,
                        toppingAnimatedSize = basilAnimatedSize,
                    )
                }
                if (PizzaTopping.BROCCOLI in pizzas[page].topping) {
                    Spread(
                        pizzaSize = animatedPizzaSize.value,
                        topping = PizzaTopping.BROCCOLI,
                        toppingAnimatedSize = broccoliAnimatedSize,
                    )
                }
                if (PizzaTopping.MUSHROOM in pizzas[page].topping) {
                    Spread(
                        pizzaSize = animatedPizzaSize.value,
                        topping = PizzaTopping.MUSHROOM,
                        toppingAnimatedSize = mushroomAnimatedSize,
                    )
                }
                if (PizzaTopping.ONION in pizzas[page].topping) {
                    Spread(
                        pizzaSize = animatedPizzaSize.value,
                        topping = PizzaTopping.ONION,
                        toppingAnimatedSize = onionAnimatedSize,
                    )
                }
                if (PizzaTopping.SAUSAGE in pizzas[page].topping) {
                    Spread(
                        pizzaSize = animatedPizzaSize.value,
                        topping = PizzaTopping.SAUSAGE,
                        toppingAnimatedSize = sausageAnimatedSize,
                    )
                }*/
            }
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