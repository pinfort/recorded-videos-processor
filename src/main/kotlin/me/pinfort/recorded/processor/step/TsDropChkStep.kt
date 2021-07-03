package me.pinfort.recorded.processor.step

import me.pinfort.recorded.processor.enumuration.VideoStatus
import me.pinfort.recorded.processor.model.VideoFileMeta
import me.pinfort.recorded.processor.repository.VideoFilesRepository
import me.pinfort.recorded.processor.repository.TsDropChkRepository
import org.slf4j.Logger
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class TsDropChkStep(
    private val tsDropChkRepository: TsDropChkRepository,
    private val videoFilesRepository: VideoFilesRepository,
    private val logger: Logger
): Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val videoFileMeta: VideoFileMeta? = videoFilesRepository.findByStatus(VideoStatus.CREATED)
        if (videoFileMeta == null) {
            logger.info("the video file ready to drop check not found.")
            return RepeatStatus.FINISHED
        }
        val drops: Int = tsDropChkRepository.check(videoFileMeta = videoFileMeta)
        val newVideoFileMeta = videoFileMeta.copy(drops = drops, status = VideoStatus.DROP_CHECKED)
        videoFilesRepository.update(newVideoFileMeta)

        return RepeatStatus.FINISHED
    }
}
