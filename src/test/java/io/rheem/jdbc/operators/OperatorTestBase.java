package io.rheem.jdbc.operators;

import org.junit.BeforeClass;
import io.rheem.core.api.Configuration;
import io.rheem.core.api.Job;
import io.rheem.core.optimizer.DefaultOptimizationContext;
import io.rheem.core.optimizer.OptimizationContext;
import io.rheem.core.plan.rheemplan.ExecutionOperator;
import io.rheem.core.plan.rheemplan.Operator;
import io.rheem.core.platform.ChannelInstance;
import io.rheem.core.platform.CrossPlatformExecutor;
import io.rheem.core.profiling.FullInstrumentationStrategy;
import io.rheem.java.execution.JavaExecutor;
import io.rheem.java.operators.JavaExecutionOperator;
import io.rheem.java.platform.JavaPlatform;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test base for {@link JdbcExecutionOperator}s and other {@link ExecutionOperator}s in this module.
 */
public class OperatorTestBase {

    protected static Configuration configuration;

    @BeforeClass
    public static void init() {
        configuration = new Configuration();
    }

    protected static JavaExecutor createJavaExecutor() {
        final Job job = createJob();
        return new JavaExecutor(JavaPlatform.getInstance(), job);
    }

    private static Job createJob() {
        final Job job = mock(Job.class);
        when(job.getConfiguration()).thenReturn(configuration);
        when(job.getCrossPlatformExecutor()).thenReturn(new CrossPlatformExecutor(job, new FullInstrumentationStrategy()));
        return job;
    }

    protected static OptimizationContext.OperatorContext createOperatorContext(Operator operator) {
        OptimizationContext optimizationContext = new DefaultOptimizationContext(createJob());
        return optimizationContext.addOneTimeOperator(operator);
    }

    protected static void evaluate(JavaExecutionOperator operator,
                                   ChannelInstance[] inputs,
                                   ChannelInstance[] outputs) {
        operator.evaluate(inputs, outputs, createJavaExecutor(), createOperatorContext(operator));
    }

}
