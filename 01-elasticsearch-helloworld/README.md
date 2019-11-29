
# 入门程序
## 引入依赖
```
        <!--elasticSearch 7.4.2 -->
        <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>elasticsearch</artifactId>
            <version>7.4.2</version>
        </dependency>
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>7.4.2</version>
        </dependency>
```
此处注意：要显式声明ES版本,因为SpringBoot声明了6.8.4版本
```puml
    <properties>
        <!--显式声明ES版本-->
        <elasticsearch.version>7.4.2</elasticsearch.version>
    </properties>
```
## 使用RestHighLevelClient连接ES服务器
```puml
RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                                    new HttpHost("127.0.0.1", 9200, "http")
                                    // ,new HttpHost("0.0.0.0",9200,"http")
                                    // 此处可连接多个 ES 服务器
                            ));
```