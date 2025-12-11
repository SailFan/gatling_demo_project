package ApiEndpoints;


import io.gatling.javaapi.core.Session;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
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
            .get("api/data?load=10000000").check(status().is(200));


    public static final HttpRequestActionBuilder cpuStress = http("CpuLoad")
            .post("api/data")

            .body(StringBody("{\n" +
                    "  \"load\": ${load},\n" +
                    "  \"dataCount\": 150,\n" +
                    "  \"includeTimestamp\": true\n" +
                    "}"))
            .asJson()
            .check(status().is(200))
            .check(jsonPath("$.success").is(String.valueOf(true)));               // JSON 响应校验


}