package me.pinfort.recorded.processor.step

import me.pinfort.recorded.processor.enumuration.VideoStatus
import me.pinfort.recorded.processor.model.SplitVideoFile
import me.pinfort.recorded.processor.model.VideoFileMeta
import me.pinfort.recorded.processor.repository.AmatsukazeRepository
import me.pinfort.recorded.processor.repository.SplitVideoFilesRepository
import me.pinfort.recorded.processor.repository.VideoFilesRepository
import org.slf4j.Logger
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class EncodeStep(
    private val logger: Logger,
    private val videoFilesRepository: VideoFilesRepository,
    private val splitVideoFilesRepository: SplitVideoFilesRepository,
    private val amatsukazeRepository: AmatsukazeRepository
): Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val videoFileMeta: VideoFileMeta? = videoFilesRepository.findByStatus(status = VideoStatus.SUB_PROCESSED)
        if (videoFileMeta == null) {
            logger.info("the video file ready to encode not found")
            return RepeatStatus.FINISHED
        }
        val splitVideoFileList: List<SplitVideoFile> = splitVideoFilesRepository.findMainFilesByVideoFileId(videoFileMeta.id)
        val encodedSplitVideoFileList: List<SplitVideoFile> = splitVideoFileList.map { splitVideoFile -> amatsukazeRepository.encode(splitVideoFile) }
        encodedSplitVideoFileList.map { splitVideoFile -> splitVideoFilesRepository.update(splitVideoFile) }

        videoFilesRepository.update(videoFileMeta.copy(status = VideoStatus.ENCODED))
        return RepeatStatus.FINISHED
    }
}