package org.ldv.savonapi.model.dao

import org.ldv.savonapi.model.entity.Resultat
import org.springframework.data.jpa.repository.JpaRepository

interface ResultatDAO : JpaRepository<Resultat, Long> {}