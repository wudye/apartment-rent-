
swagger:  
    For Swagger UI:  
    http://localhost:8080/swagger-ui/index.html (may not be available with only Knife4j)
    <br>
    For Knife4j UI (default):
    http://localhost:8080/doc.html
<br>
For log:<br>
    logging:
    level:
    root: INFO
    org.springframework.web: DEBUG
    org.springframework.security: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    file:
    path: /logs/web-admin.log
<br>
Hibernate @CreationTimestamp and @UpdateTimestamp
<br>
redis config
<br>
personalized ResponseEntity (Result.clasas)
<br>
global exception handler
<br>
@Builder is a Lombok annotation that generates a builder pattern for your class, making it easier to create objects with a fluent AP
<br>
ThreadLocal
<br>
 property injection
@ConfigurationProperties maps config values to fields.
@EnableConfigurationProperties makes the config class a Spring bean so it can be injected elsewhere.
@ConditionalOnProperty makes the configuration conditional on a property being set.
can be used to load properties from application.properties or application.yml files.
can be replaced with @Value for individual properties.
<br>
redis docker compose file
with password root
services:
redis:
    image: redis:7.2
    container_name: redis
    command: ["redis-server", "--requirepass", "root"]
    ports:
    - "6379:6379"
    restart: unless-stopped
    volumes:
      - redis-data:/data
    
    volumes:
    redis-data:
with password
    version: '3.8'
    services:
    redis:
    image: redis:7.2
    container_name: redis
    environment:
    - REDIS_PASSWORD=your_password
    command: ["redis-server", "--requirepass", "$${REDIS_PASSWORD}"]
    ports:
      - "6379:6379"
      restart: unless-stopped
      volumes:
      - redis-data:/data
    
    volumes:
    redis-data:


    send sms 
    in China:
         <dependency>
                <groupId>com.aliyun</groupId>
                <artifactId>dysmsapi20170525</artifactId>
                <version>${aliyun.sms.version}</version>
            </dependency>
    replace:
        <!-- https://mvnrepository.com/artifact/com.twilio.sdk/twilio -->
        <dependency>
            <groupId>com.twilio.sdk</groupId>
            <artifactId>twilio</artifactId>
            <version>10.9.2</version>
        </dependency>

    validate the image 


Java ThreadLocal 
    use set, get, clear functions
    working with new Thread().start
    https://medium.com/@alxkm/effective-use-of-threadlocal-in-java-applications-f4eb6a648d4a

    can also work with async
    @Component
    public class ContextCopyingTaskDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            String userContext = RequestContext.getUser();
            return () -> {
                try {
                    RequestContext.setUser(userContext);
                    runnable.run();
                } finally {
                    RequestContext.clear();
                }
            };
        }
    }
    @Configuration
    @EnableAsync
    public class AsyncConfig {
        @Bean
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(2);
            executor.setMaxPoolSize(5);
            executor.setQueueCapacity(10);
            executor.setThreadNamePrefix("Async-");
            executor.setTaskDecorator(new ContextCopyingTaskDecorator());
            executor.initialize();
            return executor;
        }
    }
    https://medium.com/@mndpsngh21/mastering-context-propagation-in-spring-boot-from-threadlocal-to-distributed-tracing-1bdf3f53f6dd


Serialization and Deserialization in Java
    https://javalaunchpad.com/serialization-and-deserialization-in-java/

scheduling
@EnableScheduling


        @Scheduled(cron = "0 0 0 * * *")
        The first 0: Seconds — at 0 seconds.
    The second 0: Minutes — at 0 minutes.
    The third 0: Hours — at 0 hours (midnight).
    The first *: Day of month — every day of the month.
    The second *: Month — every month.
    The ?: Day of week — no specific value (used when day-of-month is specified).
    https://www.baeldung.com/spring-scheduled-tasks

A generic class (like Result<T>) is a class that can operate on objects of various types while providing compile-time type safety. The <T> is a type parameter, allowing you to specify the type when you use the class.
    package com.mwu.common.constant.result;


    import lombok.Data;

    @Data
    public class Result<T> {

        private Integer code;
        private String message;

        private T data;

        public Result() {
        }

        private static <T> Result<T> build(T data) {
            Result<T> result = new Result<>();
            if (data != null)
                result.setData(data);
            return result;
        }

        public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
            Result<T> result = build(body);
            result.setCode(resultCodeEnum.getCode());
            result.setMessage(resultCodeEnum.getMessage());
            return result;
        }

        public static <T> Result<T> build(Integer code, String message) {
            Result<T> result = build(null);
            result.setCode(code);
            result.setMessage(message);
            return result;
        }


        public static <T> Result<T> ok(T data) {
            return build(data, ResultCodeEnum.SUCCESS);
        }

        public static <T> Result<T> ok() {
            return Result.ok(null);
        }

        public static <T> Result<T> fail() {
            return build(null, ResultCodeEnum.FAIL);
        }
    }
