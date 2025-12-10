package feeders;

import io.gatling.javaapi.core.*;
import org.jspecify.annotations.NonNull;

import static io.gatling.javaapi.core.CoreDsl.*;


public class UserFeeder {


    // circular 读完末尾回到开头，重复使用数据，用户列表少于 VU 数，永远循环
    // queue 按照顺序读取数据，取完就报错，数据量正好匹配 VU 数，严格控制
    // random 随机取一行，模拟真实随机访问，避免热点
    // shuffle 随机打乱整个数据，按队列取，随机 + 顺序保证所有数据都会用一次

    public static FeederBuilder.Batchable<String>  userFeeder =
            csv("data/user.csv").circular();


    public static FeederBuilder. FileBased<Object> jsonFeeder =
            jsonFile("data/login.json").circular();


}
