项目结构:
本项目已经按照包名 分为分析组件 执行组件 监视器组件 调度组件 种子排序组件和变异算子组件
分别位于analyse executor monitor scheduler seedsorter variation包下
执行对应的test程序即可对对应组件进行测试
设计
Mutator为变异算子父接口定义了mutate方法对种子进行变异
实现了按位差进行变异等方法
CoverageReader使用了JDOM2对测试生成的XML进行解析转换并由CoverageVisualizer使用JfreeChart进行可视化
`AFLMutationOperators`的类，它包含三个静态方法，用于对输入的字节数组进行变异操作。这些方法常用于模糊测试（Fuzz Testing），目的是通过引入随机变化来触发软件中的潜在错误。下面是每个方法的中文解释：
1. **简单位翻转 (`flipOneByteBit`)**:
    - 此方法从给定的输入数据中随机选择一个字节，并在这个字节内随机选择一位进行翻转（即如果该位为0则变为1，为1则变为0）。首先，它检查输入是否为空或长度为零，如果是，则直接返回输入。接着，它克隆输入数组以避免修改原数组，并随机选取一个字节索引和一个位索引进行位翻转操作。

2. **块交换 (`swapBlocks`)**:
    - 这个方法随机选择两个不重叠的“块”（连续的字节序列）并交换它们的位置。首先确保输入数据至少有两个字节长，然后克隆输入数组。接着，它随机决定块的大小（至少为1），以及两个起始位置，确保这两个块不重叠。最后，使用`System.arraycopy`方法交换这两块数据。

3. **插入删除 (`insertOrDeleteByte`)**:
    - 此方法随机执行一个字节的插入或删除操作。如果随机决定执行删除（并且输入长度大于1），则随机选择一个位置并移除该位置的字节，将数组缩短。若决定插入，则在随机位置添加一个新的字节，其值随机选定。这个方法同样先克隆输入数组，然后根据随机决策执行相应的数组操作。
      `AFLScheduler`)，主要用于指导模糊测试（fuzzing），以发现软件中的潜在漏洞。以下是各部分功能的详细解释：
AFLSchulder
    - `PriorityQueue<TestCase>`：一个优先队列，用于存储待执行的测试用例。队列按照测试用例的`score`降序排列。
    - `Random rand`：随机数生成器，用于在变异操作中引入随机性。
    - `BitSet totalCoverage`：记录所有测试用例累计达到的代码边沿覆盖率
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
