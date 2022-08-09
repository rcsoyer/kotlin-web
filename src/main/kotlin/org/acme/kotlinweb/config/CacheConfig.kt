package org.acme.kotlinweb.config

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CacheConfig {

    companion object {
        const val SOURCE_DATA_CACHE = "sourceDataFileCache"
        const val IMAGE_FEATURE_CACHE = "imageFeatureCache"
    }
}
