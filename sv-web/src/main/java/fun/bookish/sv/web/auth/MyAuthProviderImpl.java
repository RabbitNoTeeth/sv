package fun.bookish.sv.web.auth;

import fun.bookish.sv.web.config.HttpProperties;
import fun.bookish.vertx.auth.simple.core.SimpleAuthUser;
import fun.bookish.vertx.auth.simple.provider.SimpleAuthProvider;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import org.springframework.stereotype.Component;

/**
 * 权限认证实现类, 主要用户登录时检查用户是否存在以及从数据库等存储介质中获取用户权限信息
 */
@Component
public class MyAuthProviderImpl implements SimpleAuthProvider {

    /**
     * 校验用户
     * @param authInfo 用户登录信息: 用户名,密码等
     * @param resultHandler
     */
    @Override
    public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
        String username = authInfo.getString("username");
        String password = authInfo.getString("password");

        /*if(用户认证通过){
            1. 从存储介质中获取用户权限信息
            ...
            2. 创建SimpleAuthUser, 存储权限信息等
            SimpleAuthUser user = new SimpleAuthUser();
            user.setPrincipal(authInfo);
            user.addPermissions(...);
            resultHandler.handle(Future.succeededFuture(user));
        }else{
            resultHandler.handle(Future.failedFuture(new IllegalArgumentException("用户名或密码错误")));
        }*/

    }

}
