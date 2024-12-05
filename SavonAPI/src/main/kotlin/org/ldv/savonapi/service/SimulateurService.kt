package org.ldv.savonapi.service

import org.ldv.savonapi.model.dao.*
import org.ldv.savonapi.model.entity.LigneIngredient
import org.springframework.stereotype.Service


@Service
class SimulateurService(
    val caracteristiquesDAO: CaracteristiquesDAO,
    val recetteDAO: RecetteDAO,
    val ingredientDAO: IngredientsDAO,
    val ligneIngredientDAO: LigneIngredientDAO,
    val mentionDAO : MentionDAO,
    val resultatDAO: ResultatDAO
) {
}