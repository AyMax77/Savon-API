package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity
class Caracteristique(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var caracteristiqueId: Long,
    var nom: String,
    @OneToMany (mappedBy = "caracteristique")
    var mentions: MutableList<Mention> = mutableListOf()

) {
}