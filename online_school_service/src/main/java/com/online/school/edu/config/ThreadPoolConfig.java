package com.online.school.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {


    @Bean(name = "upLoadExecutor")
    public Executor upLoadExecutor() {
        return taskExecutor(5, 10, 5000, "upLoad-Thread", 60);

    }

    private ThreadPoolTaskExecutor taskExecutor (int corePoolSize, int maxPoolSize, int queueCapacity, String threadName, int keepAliveSeconds) {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(queueCapacity);

        executor.setKeepAliveSeconds(keepAliveSeconds);

        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(threadName + " ==> ");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();

        return executor;
    }
}
