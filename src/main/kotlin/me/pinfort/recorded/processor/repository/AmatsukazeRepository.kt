package me.pinfort.recorded.processor.repository

import me.pinfort.recorded.processor.model.SplitVideoFile
import org.springframework.stereotype.Repository

@Repository
class AmatsukazeRepository {
    fun encode(splitVideoFile: SplitVideoFile): SplitVideoFile {
        return SplitVideoFile(
            1L,
            1L,
            "",
            "",
            0,
            0,
            main = false,
            hasSubtitles = false
        )
    }
}