package org.ldv.savonapi.model.id

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class QuantitésId(
    var recetteId : Long,
    var ingredientsId : Long,
) : Serializable {
}