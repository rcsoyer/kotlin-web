package org.acme.kotlinweb.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.acme.kotlinweb.config.CacheConfig.Companion.IMAGE_FEATURE_CACHE
import org.acme.kotlinweb.config.CacheConfig.Companion.SOURCE_DATA_CACHE
import org.acme.kotlinweb.service.dto.FeatureCollection
import org.acme.kotlinweb.service.dto.FeatureImageCollection
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class SourceDataService(
    @Qualifier("kotlinJacksonMapper") private val objectMapper: ObjectMapper,
    @Value("\${source.data.file}") private val sourceDataFile: ClassPathResource
) {

    @Cacheable(SOURCE_DATA_CACHE)
    fun listFeatures(): List<FeatureCollection.Feature> =
        sourceDataFile
            .inputStream
            .use {
                objectMapper.readValue<List<FeatureCollection>>(it)
            }.flatMap {
                it.features
            }.ifEmpty {
                throw ResponseStatusException(INTERNAL_SERVER_ERROR, "No features found")
            }

    @Cacheable(IMAGE_FEATURE_CACHE)
    fun listImages(): Map<UUID?, String?> =
        sourceDataFile
            .inputStream
            .use {
                objectMapper.readValue<List<FeatureImageCollection>>(it)
            }.flatMap {
                it.features
            }.ifEmpty {
                throw ResponseStatusException(INTERNAL_SERVER_ERROR, "No images found")
            }.associate {
                it.id to it.quicklook
            }
}
