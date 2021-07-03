package me.pinfort.recorded.processor.model

import me.pinfort.recorded.processor.enumuration.VideoStatus

data class VideoFileMeta(
    val id: Long,
    val status: VideoStatus,
    val sha256: String,
    val path: String,
    val drops: Int,
    val splitFileCount: Int,
    )
