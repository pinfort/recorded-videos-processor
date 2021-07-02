package me.pinfort.recorded.processor.repository

import org.springframework.stereotype.Repository
import java.io.InputStream

/**
 * ストレージサーバー操作レポジトリ
 */
@Repository
class VideoStoreRepository {
    fun write(path: String, stream: InputStream) {
    }
}
