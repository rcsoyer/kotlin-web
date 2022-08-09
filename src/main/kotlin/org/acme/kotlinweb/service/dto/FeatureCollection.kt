package org.acme.kotlinweb.service.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class FeatureCollection(val features: List<Feature>) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Feature(
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        val properties: Properties? = null
    ) {

        val id: UUID? = properties?.id
        val timestamp: Long? = properties?.timestamp
        val beginViewingDate: Long? = properties?.acquisition?.beginViewingDate
        val endViewingDate: Long? = properties?.acquisition?.endViewingDate
        val missionName: String? = properties?.acquisition?.missionName

        @JsonIgnoreProperties(ignoreUnknown = true)
        data class Properties(
            val id: UUID?,
            val timestamp: Long?,
            @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
            val acquisition: Acquisition? = null,
        ) {

            @JsonIgnoreProperties(ignoreUnknown = true)
            data class Acquisition(
                val beginViewingDate: Long?,
                val endViewingDate: Long?,
                val missionName: String?
            )
        }
    }
}
