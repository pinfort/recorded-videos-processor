package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.model.SplitVideoFile
import org.springframework.stereotype.Repository

/**
 * 子動画ファイルを管理するテーブル
 */
@Repository
class SplitVideoFilesRepository {
    fun create(splitVideoFile: SplitVideoFile) {

    }

    fun findByVideoFileId(id: Long): List<SplitVideoFile> {
        return emptyList()
    }

    fun findMainFilesByVideoFileId(id: Long): List<SplitVideoFile> {
        return emptyList()
    }

    fun update(splitVideoFile: SplitVideoFile) {

    }
}