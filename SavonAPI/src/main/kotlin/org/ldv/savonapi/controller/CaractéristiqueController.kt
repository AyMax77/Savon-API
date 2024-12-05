package org.ldv.savonapi.controller

import org.ldv.savonapi.model.dao.CaracteristiquesDAO
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api-savon/v1/Caractéristiques")
class CaractéristiqueController(private val caractéristiqueDAO: CaracteristiquesDAO) {
}