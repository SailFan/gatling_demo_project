# gatling

## Core Executors




| 执行器                         | 描述                      | 典型用途            |
| --------------------------- | ----------------------- | --------------- |
| **atOnceUsers**             | 一次性启动指定数量的用户            | 瞬时压测、冒烟测试       |
| **rampUsers**               | 在指定时间内线性增加用户            | 平滑加载测试、观察系统承载能力 |
| **rampUsersPerSec**         | 在指定时间内线性增加请求速率（用户/秒）    | 对请求速率敏感的系统压力测试  |
| **constantUsersPerSec**     | 固定速率启动用户                | 目标 RPS 测试       |
| **constantConcurrentUsers** | 保持固定并发用户数               | 稳态负载测试          |
| **heavisideUsers**          | 使用 Heaviside 函数平滑分布用户启动 | 大规模并发模拟，避免瞬间冲击  |
| **nothingFor**              | 延迟执行（等待一段时间再启动）         | 模拟业务等待、阶段间冷却    |
| **separated**               | 将多个 scenario 的执行器分开     | 多 scenario 并发控制 |


## 复合执行器/扩展功能
| 执行器                     | 描述                                             |
| ----------------------- | ---------------------------------------------- |
| **rampConcurrentUsers** | 平滑 ramp 并发用户（高级别对 constantConcurrentUsers 的扩展） |
| **splitUsers**          | 将用户分成不同阶段启动，支持复杂负载曲线                           |
| **customInjectionStep** | Gatling DSL 可自定义用户注入模式（如根据函数或指标）               |
