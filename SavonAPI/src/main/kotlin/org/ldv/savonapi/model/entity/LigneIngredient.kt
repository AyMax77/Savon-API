package org.ldv.savonapi.model.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import org.ldv.savonapi.model.dao.LigneIngredientDAO
import org.ldv.savonapi.model.id.LigneIngredientId

@Entity
class LigneIngredient(
    @EmbeddedId
    var ligneIngredientId : LigneIngredientId,

    @MapsId("ingredientsId")
    @ManyToOne
    @JoinColumn(name = "ingredients_id")
    var ingredient : Ingredient?,
    var quantite : Float,

    var pourcentage : Float,

    @MapsId("recetteId")
    @ManyToOne
    @JoinColumn(name = "recette_id")
    var recette : Recette?=null
) {
}