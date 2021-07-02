package me.pinfort.recorded.processor.step

import me.pinfort.recorded.processor.enumuration.VideoStatus
import me.pinfort.recorded.processor.model.VideoFileMeta
import me.pinfort.recorded.processor.repository.ProcessedFilesRepository
import me.pinfort.recorded.processor.repository.TsDropChkRepository
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class TsDropChkStep(
    private val tsDropChkRepository: TsDropChkRepository,
    private val processedFilesRepository: ProcessedFilesRepository
): Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val videoFileMeta = VideoFileMeta(
            id = 1L,
            status = VideoStatus.FOUND,
            sha256 = "hoge",
            path = "/hjoge",
            drops = 0,
            splitFileCount = 0,
            hasSubtitles = false
        )
        val drops: Int = tsDropChkRepository.check(videoFileMeta = videoFileMeta)
        val newVideoFileMeta = videoFileMeta.copy(drops = drops, status = VideoStatus.DROP_CHECKED)
        processedFilesRepository.update(newVideoFileMeta)

        return RepeatStatus.FINISHED
    }
}
