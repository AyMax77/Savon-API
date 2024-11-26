package org.ldv.savonapi.controller

import org.ldv.savonapi.model.dao.MentionDAO
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping
class MentionController(private val mentiondao : MentionDAO) {
}