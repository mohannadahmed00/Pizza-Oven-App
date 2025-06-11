package com.giraffe.pizzaovenapp.model

data class PizzaUiState(
    val bread: Int,
    val toppings: List<PizzaTopping> = emptyList()
)