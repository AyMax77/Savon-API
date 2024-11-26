package org.ldv.savonapi.model.dao

import org.ldv.savonapi.model.entity.Caracteristique
import org.springframework.data.jpa.repository.JpaRepository

interface CaractéristiquesDAO: JpaRepository<Caracteristique,Long> {
}