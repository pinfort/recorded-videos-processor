package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.model.VideoFileMeta
import org.springframework.stereotype.Repository

/**
 * 処理済みの動画ファイルを管理するテーブル
 */
@Repository
class ProcessedFilesRepository {
    fun findByPath(path: String): VideoFileMeta? {
        return null
    }

    fun create(videoFileMeta: VideoFileMeta) {
//        TODO("insert DB record")
    }

    fun update(videoFileMeta: VideoFileMeta) {

    }
}
