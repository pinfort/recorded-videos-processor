package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.model.SplitVideoFile
import me.pinfort.recorded.processor.model.VideoFileMeta
import org.springframework.stereotype.Repository

/**
 * TSSplitter操作用レポジトリ
 */
@Repository
class TsSplitRepository {
    fun split(videoFileMeta: VideoFileMeta): List<SplitVideoFile> {
        return emptyList()
    }
}
