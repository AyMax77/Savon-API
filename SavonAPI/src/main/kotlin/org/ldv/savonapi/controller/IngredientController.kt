package org.ldv.savonapi.controller

import org.ldv.savonapi.model.dao.IngredientsDAO
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping
class IngredientController(private val ingredientdao: IngredientsDAO){
}