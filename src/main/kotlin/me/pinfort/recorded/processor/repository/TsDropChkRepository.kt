package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.model.VideoFileMeta
import org.springframework.stereotype.Repository

/**
 * TSDropChk操作用レポジトリ
 */
@Repository
class TsDropChkRepository {
    fun check(videoFileMeta: VideoFileMeta): Int {
        return 0
    }
}
