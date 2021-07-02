package me.pinfort.recorded.processor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RecordedVideosProcessorApplication

fun main(args: Array<String>) {
	runApplication<RecordedVideosProcessorApplication>(*args)
}
