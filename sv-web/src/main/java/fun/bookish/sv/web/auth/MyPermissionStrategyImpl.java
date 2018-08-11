package fun.bookish.sv.web.auth;

import fun.bookish.vertx.auth.simple.configurable.impl.DefaultPermissionStrategyImpl;
import io.vertx.core.http.HttpServerRequest;

/**
 * 权限认证规则
 */
public class MyPermissionStrategyImpl extends DefaultPermissionStrategyImpl {

    /**
     * 权限字符串生成规则
     * @param request
     * @return
     */
    public String generatePermission(HttpServerRequest request) {
        return super.generatePermission(request);
    }

    /**
     * 权限字符串校验规则
     * @param request   请求权限字符串,根据 generatePermission() 方法生成
     * @param cached    用户拥有的权限字符串
     * @return
     */
    public boolean checkPermission(String request, String cached) {
        return super.checkPermission(request, cached);
    }
}
