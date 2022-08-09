package org.acme.kotlinweb.service.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class FeatureImageCollection(val features: List<FeatureImage>) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class FeatureImage(
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        val properties: Properties? = null
    ) {
        val id: UUID? = properties?.id
        val quicklook: String? = properties?.quicklook

        @JsonIgnoreProperties(ignoreUnknown = true)
        data class Properties(
            val id: UUID?,
            val quicklook: String?
        )
    }
}
