package me.pinfort.recorded.processor.step

import me.pinfort.recorded.processor.enumuration.VideoStatus
import me.pinfort.recorded.processor.model.SplitVideoFile
import me.pinfort.recorded.processor.model.VideoFileMeta
import me.pinfort.recorded.processor.repository.SplitVideoFilesRepository
import me.pinfort.recorded.processor.repository.TsSplitRepository
import me.pinfort.recorded.processor.repository.VideoFilesRepository
import org.slf4j.Logger
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class TsSplitStep(
    private val videoFilesRepository: VideoFilesRepository,
    private val tsSplitRepository: TsSplitRepository,
    private val splitVideoFilesRepository: SplitVideoFilesRepository,
    private val logger: Logger
): Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val videoFileMeta: VideoFileMeta? = videoFilesRepository.findByStatus(VideoStatus.DROP_CHECKED)
        if (videoFileMeta == null) {
            logger.info("the video file ready to tssplit not found")
            return RepeatStatus.FINISHED
        }
        val splitFileList: List<SplitVideoFile> = tsSplitRepository.split(videoFileMeta)
        if (splitFileList.isEmpty()) {
            logger.error("splitting fail id:${videoFileMeta.id} path:${videoFileMeta.path}")
        }
        if (splitFileList.size > 2) {
            logger.warn("unexpected split file count")
        }
        if (splitFileList.none { it.fileSize > 5 * 1024 * 1024 }) {
            logger.error("file over 5MB size not found in split id:${videoFileMeta.id} path:${videoFileMeta.path}")
            return RepeatStatus.FINISHED
        }

        splitFileList.forEach { splitVideoFile -> splitVideoFilesRepository.create(splitVideoFile) }
        videoFilesRepository.update(videoFileMeta.copy(status = VideoStatus.SPLIT))
        return RepeatStatus.FINISHED
    }
}