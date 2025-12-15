package ApiEndpoints;


import io.gatling.javaapi.core.Session;
import io.gatling.javaapi.http.HttpRequestActionBuilder;
import org.jspecify.annotations.NonNull;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class APiEndpoints {


   //获取spu列表
   public static final HttpRequestActionBuilder spu = http("商品列表")

           .get("/api/skulist")
           .header("Authorization", session -> "Bearer " + session.getString("token"))
           .check(status().is(200));



    public static final HttpRequestActionBuilder sku = http("新增购物车")
            .post("/api/addToCart")
            .body(StringBody((Session session) -> {
                // 从 Session 中读取值（确保 Simulation 里事先 set 了这两个值）
                int productId = session.getInt("productId");
                String username = session.getString("username");

                // 返回 JSON 字符串
                return String.format(
                        "{\"productId\":%d,\"username\":\"%s\",\"quantity\":1}",
                        productId,
                        username);
            }))
            .asJson()
            .check(status().is(200));

    public static final  HttpRequestActionBuilder load = http("对比K6")
            .get("api/data?load=80000000").check(status().is(200));


    public static final HttpRequestActionBuilder cpuStress = http("CpuLoad")
            .post("/api/data")
            .body(StringBody(session -> {
                return String.format("{\"date\":\"%s\",\"orderNo\":\"%s\",\"load\":80000000}", session.getString("date"), session.getString("orderNo"));

            }))
            .asJson()
            .check(status().is(200))
            .check(bodyString().saveAs("responseBody"))
            .check(jsonPath("$.success").is("true"));
                    // JSON 响应校验


}
//当 p95 RT ≥ p50 RT × 4～5
//        ⇒ 系统已进入不可逆拥塞区
//雪崩点通常发生在极限处理能力的 2～4 倍注入速率