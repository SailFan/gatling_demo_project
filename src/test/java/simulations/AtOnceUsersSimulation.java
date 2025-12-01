package simulations;

import ApiEndpoints.APiEndpoints;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

// 一次性启动指定数量的用户
public class AtOnceUsersSimulation extends Simulation {

    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/");

    ScenarioBuilder scenarioBuilder = scenario("对比")
            .exec(session -> {
                System.out.println("请求名称: 对比K6");
                System.out.println("请求方法: GET");
                System.out.println("请求URL: api/data?load=10000000");
                return session;

            })
            .exec(APiEndpoints.load.check(bodyString().saveAs("responseBody")))
            .exec(session -> {
                System.out.println("响应内容: " + session.getString("responseBody"));
                return session;
            });
    {
        setUp(
                scenarioBuilder.injectOpen(atOnceUsers(10)) // 执行器
        ).protocols(httpProtocolBuilder);          // HTTP 协议配置
    }


}

//1. 检查 mean / p50
//   - <1.2：分布集中，平均值接近中位数
//   - 1.2~1.5：轻微偏右
//   - >1.5：右偏明显
//
//2. 检查 p95 / p50
//   - ≤2：长尾不明显
//   - 2~3：轻微长尾
//   - >3：明显长尾
//
//3. 检查 p99 / p50
//   - >5：极端长尾，需要重点分析
//
//4. 辅助：
//        - std dev / mean > 0.5 → 响应波动大
//   - max / min 高 → 可能偶尔出现异常


//std dev < 10ms → 系统响应很稳定
//
//std dev 10–50ms → 有轻微波动
//
//std dev > 50ms → 存在明显长尾或偶发慢请求