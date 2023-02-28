package com.example.springbatchsample.config

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class BatchConfig {

    @Bean
    fun chunkStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        reader: ItemReader<String>,
        processor: ItemProcessor<String, String>,
        writer: ItemWriter<String>
    ): Step {
        return StepBuilder("SampleChunkStep", jobRepository)
            .chunk<String, String>(3, transactionManager)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build()
    }

    @Bean
    fun chunkJob(jobRepository: JobRepository, chunkStep: Step): Job {
        return JobBuilder("SampleChunkJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(chunkStep)
            .build()
    }

}