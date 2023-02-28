package com.example.springbatchsample.chunk

import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component

@Component
@StepScope
class SampleReader : ItemReader<String> {
    private val logger = LoggerFactory.getLogger(SampleReader::class.java)

    private val input = listOf("Hello", "World", "hoge", "fuga", null, "The World")
    private var index = 0

    @BeforeStep
    fun beforeStep(stepExecution: StepExecution) {
        val jobContext = stepExecution.jobExecution.executionContext
        jobContext.put("jobKey", "jobValue")
        val stepContext = stepExecution.executionContext
        stepContext.put("stepKey", "stepValue")
    }

    override fun read(): String? {
        val message = input[index++]
        logger.info("Read: $message")
        return message
    }

}