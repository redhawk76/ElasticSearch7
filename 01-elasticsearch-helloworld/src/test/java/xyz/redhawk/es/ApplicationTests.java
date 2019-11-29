package xyz.redhawk.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ApplicationTests {

    RestHighLevelClient client;

    @BeforeEach
    public void getRestHighLevelClient() {
        // 连接 ES
        client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("127.0.0.1", 9200, "http")
                // 此处可连接多个 ES 服务器
        ));
    }

    /**
     *  新增
     * @author: HuEnhui
     * @date: 2019/11/29 16:15
     * @param: []
     * @return: void
     */
    @Test
    public void testSave() throws IOException {
        IndexRequest request = new IndexRequest("posts"); //索引
        request.id("1"); //请求的文档ID
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2019-06-10\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON); //文档源以字符串形式提供

        //执行
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.getId());
        client.close();
    }

    /**
     *  获取
     * @author: HuEnhui
     * @date: 2019/11/29 16:17
     * @param: []
     * @return: void
     */
    @Test
    public void testGet() throws IOException {
        GetRequest getRequest = new GetRequest("posts", "1");
        GetResponse getResponse = client.get(getRequest,RequestOptions.DEFAULT);
        if (getResponse.isExists()) {
            String sourceAsString = getResponse.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    /**
     *  删除
     * @author: HuEnhui
     * @date: 2019/11/29 16:21
     * @param: []
     * @return: void
     */
    @Test
    public void testDel() throws IOException {
        DeleteRequest request = new DeleteRequest("posts", String.valueOf("1"));
        DeleteResponse deleteResponse = client.delete(request,RequestOptions.DEFAULT);
        if (deleteResponse.status() == RestStatus.OK) {
            System.out.println("删除成功");
        }
    }
    @Test
    void contextLoads() {

    }

}
