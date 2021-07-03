package me.pinfort.recorded.processor.step

import me.pinfort.recorded.processor.enumuration.VideoStatus
import me.pinfort.recorded.processor.model.SplitVideoFile
import me.pinfort.recorded.processor.model.VideoFileMeta
import me.pinfort.recorded.processor.repository.SplitVideoFilesRepository
import me.pinfort.recorded.processor.repository.SubUtilRepository
import me.pinfort.recorded.processor.repository.VideoFilesRepository
import org.slf4j.Logger
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class SubDetectStep(
    private val logger: Logger,
    private val videoFilesRepository: VideoFilesRepository,
    private val splitVideoFilesRepository: SplitVideoFilesRepository,
    private val subUtilRepository: SubUtilRepository
): Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val videoFileMeta: VideoFileMeta? = videoFilesRepository.findByStatus(VideoStatus.SPLIT)
        if (videoFileMeta == null) {
            logger.info("the video file ready to analyze sub not found")
            return RepeatStatus.FINISHED
        }
        val splitVideoFileList: List<SplitVideoFile> = splitVideoFilesRepository.findMainFilesByVideoFileId(videoFileMeta.id)
        splitVideoFileList.forEach { splitVideoFile -> splitVideoFilesRepository.update(splitVideoFile.copy(hasSubtitles = subUtilRepository.hasSub(splitVideoFile))) }
        return RepeatStatus.FINISHED
    }
}
