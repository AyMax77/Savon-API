package org.ldv.savonapi.DTO

class LigneIngredientDTO(
    var IngredientId : Long,
    var recetteId : Long?,
    var quantite : Float,
    var pourcentage : Float
) {
}