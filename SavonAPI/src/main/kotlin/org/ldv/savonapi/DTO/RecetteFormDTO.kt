package org.ldv.savonapi.DTO



class RecetteFormDTO(
    var id : Long? =null,
    var titre : String,
    var description : String,
    var surgraissage : Float,
    var avecSoude : Boolean,
    var concentrationAlcalin: Float,
    var ligneIngredient: MutableList<LigneIngredientDTO> = mutableListOf()
) {
}