package me.pinfort.recorded.processor.model

import java.io.File

data class RecordedVideoFile (
    val id: Long,
    val path: String,
) {
    fun getFile(): File {
        return File(path)
    }
}
