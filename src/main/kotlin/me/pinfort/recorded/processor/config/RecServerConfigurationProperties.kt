package me.pinfort.recorded.processor.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "recsrv")
data class RecServerConfigurationProperties(
    val baseDir: String
)
