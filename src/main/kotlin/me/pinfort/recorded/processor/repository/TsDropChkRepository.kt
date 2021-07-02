package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.model.VideoFileMeta
import org.springframework.stereotype.Repository

@Repository
class TsDropChkRepository {
    fun check(videoFileMeta: VideoFileMeta): Int {
        return 0
    }
}
