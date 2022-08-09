package org.acme.kotlinweb.service

import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.Base64
import java.util.UUID

@Service
class ImageFeatureService(private val sourceDataService: SourceDataService) {
    fun getImage(featureId: UUID): Resource =
        sourceDataService
            .listImagesById()
            .getOrElse(featureId) { throw ResponseStatusException(NOT_FOUND, "Image not found") }
            .let {
                val image = Base64.getDecoder().decode(it)
                ByteArrayResource(image)
            }
}
