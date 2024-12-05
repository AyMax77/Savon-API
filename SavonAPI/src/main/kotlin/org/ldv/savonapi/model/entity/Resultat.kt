package org.ldv.savonapi.model.entity

import jakarta.persistence.*
import org.ldv.savonapi.model.id.ResultatId

@Entity
class Resultat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var resultatId : ResultatId,
    var score : Float,

    @MapsId("RecetteId")
    @ManyToOne
    @JoinColumn(name = "recette_id")
    var recette : Recette?=null,

    @ManyToOne
    @JoinColumn(name = "mention_id")
    var mention : Mention? =null,

    @MapsId("CaracteristiqueId")
    @ManyToOne
    @JoinColumn(name = "caracteristique_id")
    var caracteristique : Caracteristique?
) {
}