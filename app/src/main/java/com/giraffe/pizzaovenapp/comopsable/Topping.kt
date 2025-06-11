package com.giraffe.pizzaovenapp.comopsable

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.giraffe.pizzaovenapp.model.PizzaTopping
import kotlin.random.Random

@Composable
fun Topping(
    pizzaSize: Float,
    topping: PizzaTopping = PizzaTopping.BASIL,
) {
    val density = LocalDensity.current
    val toppingAnimatedSize = remember { Animatable(with(density) { 300.dp.toPx() }) }
    val toppingAnimatedOpacity = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        toppingAnimatedOpacity.animateTo(1f, animationSpec = tween(20))
        toppingAnimatedSize.animateTo(with(density) { 50.dp.toPx() })
    }
    val randomOffsets = remember {
        List(topping.images.size) {
            Offset(Random.nextFloat(), Random.nextFloat())
        }
    }

    var boxSize by remember { mutableStateOf(IntSize.Zero) }
    Box(
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize(pizzaSize)
            .onGloballyPositioned { coordinates ->
                boxSize = coordinates.size
            }
    ) {
        topping.images.forEachIndexed { index, image ->
            val x = randomOffsets[index].x * (boxSize.width - toppingAnimatedSize.value)
            val y = randomOffsets[index].y * (boxSize.height - toppingAnimatedSize.value)
            Image(
                painter = painterResource(image),
                contentDescription = "topping",
                modifier = Modifier
                    .size(with(density) { toppingAnimatedSize.value.toDp() })
                    .absoluteOffset { IntOffset(x.toInt(), y.toInt()) }
                    .alpha(toppingAnimatedOpacity.value)
            )
        }
    }
}