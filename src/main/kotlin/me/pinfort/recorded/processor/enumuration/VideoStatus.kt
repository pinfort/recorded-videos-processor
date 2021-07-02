package me.pinfort.recorded.processor.enumuration

enum class VideoStatus {
    FOUND, // ファイルが初めて発見された
    CREATED, // DBレコードが作成された
    DROP_CHECKED, // Dropのcheckが済んだ
    SPLIT, // tsSplitterが済んだ
    SUB_ANALYSED, // 字幕チェックが済んだ
    SUB_PROCESSED, // 字幕処理が済んだ
    ENCODED, // エンコがすんだ
    FINISHED, // 後処理も終わった
}
