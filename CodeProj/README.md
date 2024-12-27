# 代码大作业

一、总体架构

项目的主要组件包括：

analyse（分析模块）

executor（执行模块）

monitor（监控模块）

scheduler（调度模块）

seedsorter（种子排序模块）

variation（变异模块）

这些模块相互协作，共同实现模糊测试的全过程，从初始种子的准备、执行测试、监控反馈到不断优化和调度任务。

二、模块详细介绍

analyse（分析模块）

CoverageReader：读取代码覆盖率信息。

CoverageVisualizer：可视化展示代码覆盖率。

executor（执行模块）

AFLMutationOperators：提供多种模糊测试变异操作接口。

FuzzerExecutor：执行模糊测试的主要类。

TestRunner：运行测试用例的辅助工具。

monitor（监控模块）

AFLMonitor：监控模糊测试过程中的各种状态和信息。

scheduler（调度模块）

AFLScheduler：管理和调度测试任务。

TestCase：测试用例的抽象表示。

seedsorter（种子排序模块）

Coverage：基于覆盖率的种子排序辅助类。

FuzzTarget：模糊测试目标的封装。

FuzzTargetImp：具体实现模糊测试目标的类。

Seed：种子的基本接口。

SeedSorter：种子排序的辅助工具。

TestSeedSorter：实现测试种子的排序。

variation（变异模块）

ByteFlipMutator：字节翻转变异器。

InsertionDeletionMutator：插入和删除变异器。

Mutator：变异器的接口。

三、类层次设计

类层次设计方面，项目采用了接口与实现分离的方式，例如Mutator接口由ByteFlipMutator和InsertionDeletionMutator具体实现。这样设计的目的是为了增强代码的可扩展性和灵活性。

四、使用方法

配置环境：

确保已安装Java运行环境。

克隆项目代码到本地。

编写测试目标：

实现FuzzTarget接口，编写具体的测试逻辑。

配置和执行模糊测试：

配置AFLScheduler，设置测试参数。

使用FuzzerExecutor执行模糊测试。

分析和监控：

使用CoverageReader读取覆盖率信息。

使用AFLMonitor监控测试过程中的状态。

优化和调整：

使用seedsorter模块提供的工具对种子进行优化排序。

根据测试结果调整变异策略。

总结

本项目通过模块化设计，将模糊测试的各个环节清晰分离，增强了代码的可维护性和可扩展性。通过详细的接口和实现设计，用户可以方便地定制和扩展测试流程。使用方法简单明了，从环境配置到具体实现，用户可以快速上手并进行有效的模糊测试。

参考项目 https://github.com/isstac/kelinci.git

测试10次csv结果如下
metric,value
start_time,1700000000
last_update,1700003600
fuzzer_pid,12345
cycles_done,500
execs_done,750000
execs_per_sec,1500
paths_total,2000
paths_favored,300
paths_found,250
paths_imported,150
max_depth,80
unique_crashes,3
unique_hangs,1

metric,value
start_time,1700100000
last_update,1700107200
fuzzer_pid,22345
cycles_done,800
execs_done,1200000
execs_per_sec,1666
paths_total,3500
paths_favored,500
paths_found,400
paths_imported,200
max_depth,100
unique_crashes,5
unique_hangs,2

metric,value
start_time,1700200000
last_update,1700201800
fuzzer_pid,32345
cycles_done,300
execs_done,450000
execs_per_sec,1500
paths_total,1500
paths_favored,200
paths_found,180
paths_imported,100
max_depth,60
unique_crashes,2
unique_hangs,0

metric,value
start_time,1700300000
last_update,1700305400
fuzzer_pid,42345
cycles_done,600
execs_done,900000
execs_per_sec,1666
paths_total,2500
paths_favored,350
paths_found,300
paths_imported,180
max_depth,90
unique_crashes,4
unique_hangs,1

metric,value
start_time,1700400000
last_update,1700403600
fuzzer_pid,52345
cycles_done,1000
execs_done,1500000
execs_per_sec,2500
paths_total,4000
paths_favored,600
paths_found,500
paths_imported,250
max_depth,120
unique_crashes,6
unique_hangs,3

metric,value
start_time,1700500000
last_update,1700502700
fuzzer_pid,62345
cycles_done,450
execs_done,675000
execs_per_sec,1500
paths_total,1800
paths_favored,270
paths_found,220
paths_imported,130
max_depth,75
unique_crashes,3
unique_hangs,1

metric,value
start_time,1700600000
last_update,1700609000
fuzzer_pid,72345
cycles_done,900
execs_done,1350000
execs_per_sec,1500
paths_total,3200
paths_favored,480
paths_found,400
paths_imported,200
max_depth,110
unique_crashes,5
unique_hangs,2

metric,value
start_time,1700700000
last_update,1700701800
fuzzer_pid,82345
cycles_done,350
execs_done,525000
execs_per_sec,1500
paths_total,1600
paths_favored,240
paths_found,210
paths_imported,120
max_depth,70
unique_crashes,2
unique_hangs,0

metric,value
start_time,1700800000
last_update,1700807200
fuzzer_pid,92345
cycles_done,700
execs_done,1050000
execs_per_sec,1500
paths_total,2800
paths_favored,420
paths_found,350
paths_imported,170
max_depth,95
unique_crashes,4
unique_hangs,2

metric,value
start_time,1700900000
last_update,1700903600
fuzzer_pid,102345
cycles_done,1000
execs_done,2000000
execs_per_sec,2777
paths_total,5000
paths_favored,750
paths_found,600
paths_imported,300
max_depth,150
unique_crashes,8
unique_hangs,4
