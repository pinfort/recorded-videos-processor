package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.model.RecordedVideoFile
import org.springframework.stereotype.Repository

/**
 * 録画サーバー操作レポジトリ
 */
@Repository
class RecServerFilesRepository {
    fun getFiles(directory: String): List<RecordedVideoFile> {
        return emptyList()
    }
}
