import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

public class HystrixPropertyTest {

    @HystrixCommand(fallbackMethod = "str_fallbackMethod",
        groupKey = "strGroupCommand",
        commandKey = "strCommand",
        threadPoolKey = "strThreadPool",
        commandProperties = {
                /**
                 * Execution
                 */
                //设置隔离策略,THREAD表示线程池 SEMAPHORE:信号池隔离
                @HystrixProperty(name = "execution.isolation.strategy",value = "THREAD"),
                //当HystrixCommand命令的隔离策略使用信号量时，该属性用来配置信号量的大小。当最大并发请求达到该设置值时，后续的请求将被拒绝。
                @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "10"),
                //该属性用来配置HystrixCommand执行的超时时间，单位为毫秒。
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "10"),
                //该属性用来配置HystrixCommand.run()的执行是否启用超时时间。默认为true。
                @HystrixProperty(name = "execution.timeout.enabled",value = "true"),
                //该属性用来配置当HystrixCommand.run()执行超时的时候是否要它中断。
                @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout",value = "true"),
                //该属性用来配置当HystrixCommand.run()执行取消时是否要它中断。
                @HystrixProperty(name = "execution.isolation.thread.interruptOnCancel",value = "true"),

                /**
                 * Fallback
                 */
                //该属性用来设置从调用线程中允许HystrixCommand.getFallback()方法执行的最大并发请求数。当达到最大并发请求时，后续的请求将会被拒绝并抛出异常。
                @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests",value = "10"),
                //该属性用来设置服务降级策略是否启用，默认是true。如果设置为false，当请求失败或者拒绝发生时，将不会调用HystrixCommand.getFallback()来执行服务降级逻辑。
                @HystrixProperty(name = "fallback.enabled",value = "true"),

                /**
                 * Circuit Breaker
                 */
                //确定当服务请求命令失败时，是否使用断路器来跟踪其健康指标和熔断请求。默认为true。
                @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                //用来设置在滚动时间窗中，断路器熔断的最小请求数。例如，默认该值为20的时候，如果滚动时间窗（默认10秒）内仅收到19个请求，即使这19个请求都失败了，断路器也不会打开。
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "20"),
                //用来设置当断路器打开之后的休眠时间窗。休眠时间窗结束之后，会将断路器设置为“半开”状态，尝试熔断的请求命令，如果依然时候就将断路器继续设置为“打开”状态，如果成功，就设置为“关闭”状态。
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
                //该属性用来设置断路器打开的错误百分比条件。默认值为50，表示在滚动时间窗中，在请求值超过requestVolumeThreshold阈值的前提下，如果错误请求数百分比超过50，就把断路器设置为“打开”状态，否则就设置为“关闭”状态。
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                //该属性默认为false。如果该属性设置为true，断路器将强制进入“打开”状态，它会拒绝所有请求。该属性优于forceClosed属性。
                @HystrixProperty(name = "circuitBreaker.forceOpen",value = "false"),
                //该属性默认为false。如果该属性设置为true，断路器强制进入“关闭”状态，它会接收所有请求。如果forceOpen属性为true，该属性不生效。
                @HystrixProperty(name = "circuitBreaker.forceClosed",value = "true"),

                /**
                 * Metrics
                 */
                //该属性用来设置滚动时间窗的长度，单位为毫秒。该时间用于断路器判断健康度时需要收集信息的持续时间。断路器在收集指标信息时会根据设置的时间窗长度拆分成多个桶来累计各度量值，每个桶记录了一段时间的采集指标。例如，当为默认值10000毫秒时，断路器默认将其分成10个桶，每个桶记录1000毫秒内的指标信息。
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000"),
                //用来设置滚动时间窗统计指标信息时划分“桶”的数量。默认值为10。
                @HystrixProperty(name = "metrics.rollingStats.numBuckets",value = "10"),
                //用来设置对命令执行延迟是否使用百分位数来跟踪和计算。默认为true，如果设置为false，那么所有的概要统计都将返回-1。
                @HystrixProperty(name = "metrics.rollingPercentile.enabled",value = "false"),
                //用来设置百分位统计的滚动窗口的持续时间，单位为毫秒。
                @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds",value = "60000"),
                //用来设置百分位统计滚动窗口中使用桶的数量。
                @HystrixProperty(name = "metrics.rollingPercentile.numBuckets",value = "60000"),
                //用来设置每个“桶”中保留的最大执行数。
                @HystrixProperty(name = "metrics.rollingPercentile.bucketSize",value = "100"),
                //用来设置采集影响断路器状态的健康快照的间隔等待时间。
                @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds",value = "500"),

                /**
                 * Request Context
                 */
                //用来配置是否开启请求缓存。
                @HystrixProperty(name = "requestCache.enabled",value = "true"),
                //用来设置HystrixCommand的执行和事件是否打印到日志的HystrixRequestLog中。
                @HystrixProperty(name = "requestLog.enabled",value = "true"),
            },
            threadPoolProperties = {
                //该参数用来设置执行命令线程池的核心线程数，该值也就是命令执行的最大并发量
                @HystrixProperty(name = "coreSize",value = "10"),
                //该参数用来设置线程池的最大队列大小。当设置为-1时，线程池将使用SynchronousQueue实现的队列，
                //否则将使用LinkedBlockingQueue实现的队列
                @HystrixProperty(name = "maximumSize",value="-1"),
                //该参数用来为队列设置拒绝阙值。通过该参数，即使队列没有达到最大值也能拒绝请求
                //该参数主要是对LinkedBlockingQueue队列的补充，
                //因为LinkedBlockingQueue队列不能动态修改它的对象大小，而通过该属性就可以调整拒绝请求的队列大小
                @HystrixProperty(name = "queueSizeRejectionThreshold",value="5"),
            }
    )
    public String getAll(){
        return "";
    }
}
