# 学习笔记

## JVM 3

1. 使用一个测试类分析GC情况：[GCLogAnalysis.java](./tools/GCLogAnalysis.java)

    - `java -XX:+PrintGCDetails GCLogAnalysis`
    - `java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

2. JVM线程堆栈：
    
    - 解决死锁，两个办法
        - 线程加超时时间
        - 把一个线程强制终止掉
    - 死锁问题分析工具：[FastThread: https://fastthread.io/](https://fastthread.io/)

3. 内存分析相关工具

    - 使用`Instrumentation.getObjectSize()`方法估算一个对象占用的内存空间
    - 使用工具JOL (Java Object Layout) 可以用来查看对象内存布局。
    - “对齐”：基本类型和包装类(int/Integer)、一维数组和多维数组、String
    - 和Perm/MetaSpace相关的OOM
    - 和线程相关的OOM
    - 内存Dump分析工具：Eclipse MAT、jhat

    多大的对象算大对象：
        - G1: 默认2048个region 1m, 512K

4. JVM 调优经验

    - 高分配速率 -> 增大Eden区
    - 过早提升 -> 增加年轻代、减少批处理的数量。目标：让临时数据在年轻代能存放的下
    - 根据GC log 分析分配速率

5. GC疑难情况问题分析
    - Arthas
    - 数据库连接池： 30~50比较合适、几百就太大了

6. 面试题

7. 压力了测试
    - `sb`工具（SuperBenchmarker）
    - jms

工具： windows terminal


## NIO 和 Netty

端口：区分要访问的是哪个进程

epoll
- 内核与用户空间共享一块内存
- 通过回调解决遍历问题
- fd没有限制，可以支撑10万连接

EDA - 事件驱动架构
SEDA - 分阶段的事件驱动架构