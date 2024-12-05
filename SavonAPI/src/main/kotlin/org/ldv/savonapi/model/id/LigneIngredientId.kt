package org.ldv.savonapi.model.id

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class LigneIngredientId(
    var recetteId : Long,
    var ingredientsId : Long,
) : Serializable {
}