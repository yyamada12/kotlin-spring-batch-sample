package com.example.springbatchsample.chunk

import org.slf4j.LoggerFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component


@Component
@StepScope
class SampleWriter : ItemWriter<String> {

    private val logger = LoggerFactory.getLogger(SampleWriter::class.java)
    override fun write(chunk: Chunk<out String>) {
        logger.info("Writer: ${chunk.items}")
        logger.info("=======================")
    }

}