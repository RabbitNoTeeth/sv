package fun.bookish.sv.web.router;

import fun.bookish.sv.web.base.SubRouter;
import fun.bookish.sv.web.utils.JsonUtil;
import fun.bookish.sv.web.utils.RoutingContextUtil;
import fun.bookish.vertx.auth.simple.core.Subject;
import fun.bookish.vertx.auth.simple.util.SubjectUtil;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;

@Component
public class LoginRouter implements SubRouter {

    @Override
    public void mount(Router router) {
        router.get("/hello").handler(this::hello);
        router.post("/login").handler(this::login);
    }

    /**
     * 测试方法
     * @param context
     */
    private void hello(RoutingContext context) {
        context.response().write(JsonUtil.getJsonStr(true,"测试成功", "hello sv !"));
        context.next();
    }

    /**
     * 用户登录
     * @param context
     */
    private void login(RoutingContext context) {

        JsonObject params = RoutingContextUtil.getRequestParams(context);
        String username = params.getString("username");

        Subject subject = SubjectUtil.getSubject(context);
        subject.login(context,params, res -> {
            if(res.succeeded()){
                context.response().write(JsonUtil.getJsonStr(true,"登录成功", username));
            }else{
                context.response().write(JsonUtil.getJsonStr(false,"用户名或密码错误", null));
            }
            context.next();
        });

    }

}
