package com.example.springbatchsample.config

import com.example.springbatchsample.tasklet.SampleTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class BatchConfig {

    @Bean
    fun taskletStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        sampleTasklet: SampleTasklet
    ): Step {
        return StepBuilder("sampleTaskletStep", jobRepository)
            .tasklet(sampleTasklet, transactionManager)
            .build()
    }

    @Bean
    fun taskletJob(jobRepository: JobRepository, taskletStep: Step): Job {
        return JobBuilder("sampleTaskletJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(taskletStep)
            .build()
    }
    
}