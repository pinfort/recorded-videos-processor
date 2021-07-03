package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.enumuration.VideoStatus
import me.pinfort.recorded.processor.model.VideoFileMeta
import org.springframework.stereotype.Repository

/**
 * 親動画ファイルを管理するテーブル
 */
@Repository
class VideoFilesRepository {
    fun findByPath(path: String): VideoFileMeta? {
        return null
    }

    fun findByStatus(status: VideoStatus): VideoFileMeta? {
        return null
    }

    fun create(videoFileMeta: VideoFileMeta) {
//        TODO("insert DB record")
    }

    fun update(videoFileMeta: VideoFileMeta) {

    }
}
