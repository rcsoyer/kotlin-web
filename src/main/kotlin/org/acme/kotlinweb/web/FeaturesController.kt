package org.acme.kotlinweb.web

import org.acme.kotlinweb.service.ImageFeatureService
import org.acme.kotlinweb.service.SourceDataService
import org.acme.kotlinweb.service.dto.FeatureCollection
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders.CONTENT_DISPOSITION
import org.springframework.http.MediaType.IMAGE_PNG
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("features")
class FeaturesController(
    private val sourceDataService: SourceDataService,
    private val imageService: ImageFeatureService
) {

    @GetMapping
    fun getFeatures(): List<FeatureCollection.Feature> = sourceDataService.listFeatures()

    @GetMapping("{featureId}/quicklook")
    fun downloadImage(@PathVariable featureId: UUID): ResponseEntity<Resource> =
        ResponseEntity
            .ok()
            .contentType(IMAGE_PNG)
            .header(CONTENT_DISPOSITION, "attachment; filename=image.png")
            .body(imageService.getImage(featureId))
}
