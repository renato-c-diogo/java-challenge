package com.rcd.test;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Fork(value = 1)
@Threads(value = 1)
@Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 3000)
@Measurement(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 3000)
public class ProgramBenchmark {

    public static void main(String[] args) throws RunnerException {
        new Runner(
                new OptionsBuilder()
                        .include(ProgramBenchmark.class.getSimpleName())
                        .build()
        )
                .run();
    }

    // Test Case 6
    int[] array = new int[] {-10, -3, -5, 2, 15, -7, 28, -6, 12, 8, 11, 5};
    int targetSum = 20;

    @Benchmark
    public void fourNumberSumV1() {
        Program.fourNumberSumV1(array, targetSum);
    }

    @Benchmark
    public void fourNumberSumV2() {
        Program.fourNumberSumV2(array, targetSum);
    }

}
