package org.roof.im.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.roof.httpclinet.HttpClientUtils;
import org.roof.im.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.ResponseEntity;

/**
 * @author liuxin
 * @date 2018/3/10
 */
public class RestfulUserService implements UserService, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulUserService.class);

    private TokenUsernameStore tokenUsernameStore;
    private HttpClientUtils httpClientUtils;
    private String serverAddress; //服务器地址
    private String authApi; //authApi地址
    private String userInfoApi; //用户信息api地址

    private String authApiAddress;//用户验证接口
    private String userInfoApiAddress; //用户信息接口

    @Override
    public void afterPropertiesSet() throws Exception {
        authApiAddress = serverAddress + authApi;
        userInfoApiAddress = serverAddress + userInfoApi;
    }


    @Override
    public String auth(String token) throws UserAuthException {
        String url = authApiAddress + "?token=" + token;
        try {
            ResponseEntity<String> responseEntity = httpClientUtils.doGet(url);
            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                throw new UserAuthException("auth server error, server http return code is " + responseEntity.getStatusCode());
            }
            String json = responseEntity.getBody();
            JSONObject jsonObject = JSON.parseObject(json);
            return jsonObject.getString("user_name");
        } catch (Exception e) {
            throw new UserAuthException("auth server error, exception is " + e.getMessage(), e);
        }
    }

    @Override
    public String getLoginUser(String token) {
        return tokenUsernameStore.get(token);
    }

    @Override
    public boolean exist(String username) {
        String url = userInfoApiAddress + "/" + username;
        try {
            ResponseEntity<String> responseEntity = httpClientUtils.doGet(url);
            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                LOGGER.error("userInfo server error, server http return code is " + responseEntity.getStatusCode());
                return false;
            }
            String json = responseEntity.getBody();
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject userInfoJsonObject = jsonObject.getJSONObject("data");
            if (userInfoJsonObject == null) {
                return false;
            }
            String returnUsername = userInfoJsonObject.getString("username");
            if (StringUtils.isEmpty(returnUsername)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    public void setTokenUsernameStore(TokenUsernameStore tokenUsernameStore) {
        this.tokenUsernameStore = tokenUsernameStore;
    }

    public void setHttpClientUtils(HttpClientUtils httpClientUtils) {
        this.httpClientUtils = httpClientUtils;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void setAuthApi(String authApi) {
        this.authApi = authApi;
    }

    public void setUserInfoApi(String userInfoApi) {
        this.userInfoApi = userInfoApi;
    }
}
