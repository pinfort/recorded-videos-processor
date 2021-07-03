package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.model.SplitVideoFile
import org.springframework.stereotype.Repository

@Repository
class SubUtilRepository {
    fun hasSub(splitVideoFile: SplitVideoFile): Boolean {
        return false
    }
}
