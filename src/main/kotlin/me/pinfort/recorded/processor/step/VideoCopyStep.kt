package me.pinfort.recorded.processor.step

import me.pinfort.recorded.processor.config.RecServerConfigurationProperties
import me.pinfort.recorded.processor.enumuration.VideoStatus
import me.pinfort.recorded.processor.model.VideoFileMeta
import me.pinfort.recorded.processor.model.RecordedVideoFile
import me.pinfort.recorded.processor.repository.VideoFilesRepository
import me.pinfort.recorded.processor.repository.RecServerFilesRepository
import me.pinfort.recorded.processor.repository.VideoStoreRepository
import org.apache.commons.codec.digest.DigestUtils
import org.slf4j.Logger
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import java.io.FileInputStream

class VideoCopyStep(
    private val recServerFilesRepository: RecServerFilesRepository,
    private val videoFilesRepository: VideoFilesRepository,
    private val videoStoreRepository: VideoStoreRepository,
    private val recServerConfigurationProperties: RecServerConfigurationProperties,
    private val logger: Logger
): Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val files = recServerFilesRepository.getFiles(recServerConfigurationProperties.baseDir)
        files.forEach(this::executeFile)

        return RepeatStatus.FINISHED
    }

    private fun executeFile(recordedVideoFile: RecordedVideoFile) {
        if (videoFilesRepository.findByPath(recordedVideoFile.path) != null) {
            logger.debug("file ${recordedVideoFile.path} is already exist")
            return
        }

        val videoFileMeta = VideoFileMeta(
            id = 1, // dummy. DB auto incremented
            VideoStatus.CREATED,
            path = recordedVideoFile.path,
            sha256 = DigestUtils.sha256Hex(FileInputStream(recordedVideoFile.getFile())),
            drops = 0,
            splitFileCount = 0,
            hasSubtitles = false
        )

        videoStoreRepository.write(recordedVideoFile.path, FileInputStream(recordedVideoFile.getFile()))
        videoFilesRepository.create(videoFileMeta)
    }
}