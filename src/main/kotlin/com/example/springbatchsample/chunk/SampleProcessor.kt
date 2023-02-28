package com.example.springbatchsample.chunk

import org.slf4j.LoggerFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
@StepScope
class SampleProcessor : ItemProcessor<String, String> {
    private val logger = LoggerFactory.getLogger(SampleProcessor::class.java)

    override fun process(item: String): String? {
        val processed = "**$item**"
        logger.info("Processor: $processed")
        return processed
    }

}