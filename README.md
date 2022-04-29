# Java-Challenge

### Java version
- JDK 11

### Tests
```
gradle cleanTest test
```

### Benchmark
```
gradle clean jmh
```

### Results:
```
Benchmark                         Mode  Cnt      Score   Error  Units
ProgramBenchmark.fourNumberSumV1    ss        2554,600          us/op
ProgramBenchmark.fourNumberSumV2    ss       35195,400          us/op
```