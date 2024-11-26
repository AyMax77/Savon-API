package org.ldv.savonapi.model.dao

import org.ldv.savonapi.model.entity.Ingredient
import org.springframework.data.jpa.repository.JpaRepository

interface IngredientsDAO : JpaRepository<Ingredient,Long> {}