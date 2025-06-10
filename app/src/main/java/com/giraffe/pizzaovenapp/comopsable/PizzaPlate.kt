package com.giraffe.pizzaovenapp.comopsable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giraffe.pizzaovenapp.R
import com.giraffe.pizzaovenapp.model.PizzaIngredient
import com.giraffe.pizzaovenapp.model.PizzaSize
import com.giraffe.pizzaovenapp.ui.theme.PizzaOvenAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PizzaPlate(
    modifier: Modifier = Modifier,
    pizzaSize: PizzaSize = PizzaSize.MEDUIM,
    ingredients: Set<PizzaIngredient> = setOf<PizzaIngredient>()
) {
    val breads = listOf(
        R.drawable.bread_1,
        R.drawable.bread_2,
        R.drawable.bread_3,
        R.drawable.bread_4,
        R.drawable.bread_5,
    )
    val pagerState = rememberPagerState { 5 }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(R.drawable.plate), contentDescription = "plate")
        HorizontalPager(
            modifier = Modifier,
            state = pagerState
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(pizzaSize.value),
                    painter = painterResource(breads[it]),
                    contentDescription = "bread"
                )
                ingredients.forEach { ingredient ->
                    Image(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(ingredient.images.random()),
                        contentDescription = "ingredient"
                    )
                }

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