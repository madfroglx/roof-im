package org.roof.im.chain.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.Module.Sts;
import com.qcloud.QcloudApiModuleCenter;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.roof.im.request.GetTmpSecretRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.TreeMap;

/**
 * @author hzliuxin1
 * @since 2018/3/7 0007
 */
public class GetTmpSecretHandlerNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetTmpSecretHandlerNode.class);
    private static final String GET_FEDERATION_TOKEN = "GetFederationToken";
    /**
     * 获取密钥失败
     */
    private static final String GET_TMP_SECRET_ERROR = "getTmpSecretError";
    /**
     * 获取密钥成功
     */
    private static final String GET_TMP_SECRET_SUCCESS = "getTmpSecretSuccess";
    private String secretId;
    private String secretKey;
    private int durationSeconds;
    private String region;
    private String bucket;
    private String pathPrefix;
    private String appId;

    public NodeResult doNode(GetTmpSecretRequest request, ValueStack valueStack) {
        TreeMap<String, Object> config = new TreeMap<>();
        config.put("SecretId", secretId);
        config.put("SecretKey", secretKey);

        /* 请求方法类型 POST、GET */
        config.put("RequestMethod", "GET");

        /* 区域参数，可选: gz: 广州; sh: 上海; hk: 香港; ca: 北美; 等。 */
        config.put("DefaultRegion", "sh");

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Sts(),
                config);

        TreeMap<String, Object> params = new TreeMap<>();
        /* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
        /* DescribeInstances 接口的部分可选参数如下 */
        params.put("name", "im");
        String policy = "{\"statement\": [{\"action\": [\"name/cos:GetObject\",\"name/cos:PutObject\"]," +
                "\"effect\": \"allow\"," +
                "\"resource\":[\"qcs::cos:" + region + ":uid/" + appId + ":prefix//" + appId + "/im/*\"]}]" +
                ",\"version\": \"2.0\"}";
        params.put("policy", policy);
        params.put("durationSeconds", durationSeconds);

        /* 在这里指定所要用的签名算法，不指定默认为 HmacSHA1*/
        //params.put("SignatureMethod", "HmacSHA256");

        /* generateUrl 方法生成请求串, 可用于调试使用 */
        String result = null;
        try {
            NodeResult nodeResult = new NodeResult(GET_TMP_SECRET_SUCCESS);
            /* call 方法正式向指定的接口名发送请求，并把请求参数 params 传入，返回即是接口的请求结果。 */
            result = module.call(GET_FEDERATION_TOKEN, params);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getInteger("code") != 0) {
                LOGGER.error(result);
                return new NodeResult(GET_TMP_SECRET_ERROR);
            }
            JSONObject data = jsonObject.getJSONObject("data");
            data.put("region", region);
            data.put("bucket", bucket + "-" + appId);
            data.put("path", pathPrefix + "/" + DateFormatUtils.format(new Date(), "yyyyMM") + "/");
            nodeResult.setData(data);
            return nodeResult;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new NodeResult(GET_TMP_SECRET_ERROR);
        }
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setPathPrefix(String pathPrefix) {
        this.pathPrefix = pathPrefix;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
