package org.ldv.savonapi.model.id

import jakarta.persistence.Embeddable


@Embeddable
class ResultatId(
    var caracteristiqueId: Long,
    var recetteId: Long
) {
}