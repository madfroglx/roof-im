package org.roof.im.test;

import com.qcloud.Module.Sts;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.auth.COSSigner;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.TreeMap;

/**
 * @author hzliuxin1
 * @since 2018/3/7 0007
 */
public class OCSTest {
    @Test
    public void testKey() {
        /* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        config.put("SecretId", "AKIDSpCPQFhNfmmcJ0Q0FKE7pNjuuiKlN73o");
        config.put("SecretKey", "kKjOsEPOCoFUlwviLm27IJ9nFWcaNyOc");

        /* 请求方法类型 POST、GET */
        config.put("RequestMethod", "GET");

        /* 区域参数，可选: gz: 广州; sh: 上海; hk: 香港; ca: 北美; 等。 */
        config.put("DefaultRegion", "sh");

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Sts(),
                config);

        TreeMap<String, Object> params = new TreeMap<String, Object>();
        /* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
        /* DescribeInstances 接口的部分可选参数如下 */
        params.put("name", "test");
//        String policy = "{\"statement\": [{\"action\": [\"name/cos:GetObject\",\"name/cos:PutObject\"],\"effect\": \"allow\",\"resource\":[\"qcs::cos:ap-beijing:uid/1255710173:prefix//1255710173/im/*\"]}],\"version\": \"2.0\"}";
        String policy = "{\"statement\": [{\"action\": [\"name/cos:GetObject\",\"name/cos:PutObject\"],\"effect\": \"allow\",\"resource\":[\"qcs::cos:ap-shanghai:uid/1255710173:prefix//1255710173/im/*\"]}],\"version\": \"2.0\"}";
        params.put("policy", policy);

        /* 在这里指定所要用的签名算法，不指定默认为 HmacSHA1*/
        //params.put("SignatureMethod", "HmacSHA256");

        /* generateUrl 方法生成请求串, 可用于调试使用 */
        System.out.println(module.generateUrl("GetFederationToken", params));
        String result = null;
        try {
            /* call 方法正式向指定的接口名发送请求，并把请求参数 params 传入，返回即是接口的请求结果。 */
            result = module.call("GetFederationToken", params);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }
    }

    @Test
    public void testUpload() {
        // 用户基本信息
        String appid = "1255710173";
        String secret_id = "AKID3A9jOoqatjrh6k7UjyKfA5N6q4olSaB6";
        String secret_key = "Qdr0efEb3NicSkED2UsKaQ8ANaPorDWr";
        String sessionToken = "81ca8cc5fb84bd42ac36c40515917b308ad333d830001";

        // 设置秘钥
        COSCredentials cred = new BasicCOSCredentials(appid, secret_id, secret_key);
        // 设置区域, 这里设置为华北
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        // 生成 cos 客户端对象
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 创建 bucket
        // bucket 数量上限 200 个, bucket 是重操作, 一般不建议创建如此多的 bucket
        // 重复创建同名 bucket 会报错
        String bucketName = "im";
        // 上传 object, 建议 20M 以下的文件使用该接口
        File localFile = new File("E:\\excel\\test.txt");
        String key = "im/zlt/test4";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSecurityToken(sessionToken);
        putObjectRequest.setMetadata(objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println(putObjectResult);

        // 关闭客户端 (关闭后台线程)
        cosClient.shutdown();
    }

    @Test
    public void createAuthorization() throws UnsupportedEncodingException {
        String appid = "1255710173";
        String secret_id = "AKIDSpCPQFhNfmmcJ0Q0FKE7pNjuuiKlN73o";
        String secret_key = "kKjOsEPOCoFUlwviLm27IJ9nFWcaNyOc";
//        String sessionToken = "562af6ffcbe981dd21bd7434176dcbb2f883839030001";

        // 设置秘钥
        COSCredentials cred = new BasicCOSCredentials(appid, secret_id, secret_key);
        COSSigner cosSigner = new COSSigner();
        System.out.println(cosSigner.buildAuthorizationStr(HttpMethodName.POST, URLEncoder.encode("/zlt/test121", "UTF-8"), cred, new Date(1520478668000L)));

    }
}
