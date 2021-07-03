package me.pinfort.recorded.processor.model

data class SplitVideoFile(
    val id: Long,
    val videoFileId: Long,
    val originPath: String,
    val encodedPath: String,
    val length: Int,
    val fileSize: Long,
    val main: Boolean,
    val hasSubtitles: Boolean,
)
