package fun.bookish.sv.web.verticle;

import fun.bookish.sv.core.utils.SpringUtil;
import fun.bookish.sv.web.auth.MyPermissionStrategyImpl;
import fun.bookish.sv.web.auth.MySessionIdStrategyImpl;
import fun.bookish.sv.web.auth.MyAuthProviderImpl;
import fun.bookish.sv.web.config.HttpProperties;
import fun.bookish.sv.web.handler.CrosHandler;
import fun.bookish.sv.web.handler.RequestParamsHandler;
import fun.bookish.sv.web.handler.ResponsePostHandler;
import fun.bookish.sv.web.handler.ResponsePreHandler;
import fun.bookish.sv.web.router.SubRouter;
import fun.bookish.vertx.auth.simple.core.SimpleAuthOptions;
import fun.bookish.vertx.auth.simple.handler.SimpleAuthHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * http服务部署单元
 */
public class HttpVerticle extends AbstractVerticle {

    private final HttpProperties httpProperties = SpringUtil.getBeanByClass(HttpProperties.class);
    private final Logger LOGGER = LoggerFactory.getLogger("sv-web");

    @Override
    public void start() throws Exception {

        Router router = Router.router(this.vertx);

        configRouter(router);        //配置路由

        this.vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(httpProperties.getPort(), res -> {
                    if(res.succeeded()){
                        LOGGER.info("http 服务启动成功, port = " +  httpProperties.getPort());
                    }else{
                        LOGGER.error("http 服务启动失败", res.cause());
                        throw new IllegalStateException(res.cause());
                    }
                });

    }

    private void configRouter(Router router) {

        // 静态资源处理器
        StaticHandler staticHandler = StaticHandler.create().setIndexPage(httpProperties.getIndexPage()).setWebRoot(httpProperties.getWebRoot());

        // 权限认证处理器
        MyAuthProviderImpl authProvider = SpringUtil.getBeanByClass(MyAuthProviderImpl.class);
        SimpleAuthOptions authOptions = new SimpleAuthOptions();
        List<String> annos = new ArrayList<>();
        if(httpProperties.isAuth()){
            annos.addAll(httpProperties.getAnnoPermissions());
        }else{
            annos.add("*");
        }
        authOptions.setAnnoPermissions(annos);
        authOptions.setPermissionStrategy(new MyPermissionStrategyImpl());
        authOptions.setSessionIdStrategy(new MySessionIdStrategyImpl());
        SimpleAuthHandler authHandler = SimpleAuthHandler.create(this.vertx, authProvider, authOptions);

        // 挂载所有前置处理器
        router.route()
                .handler(new CrosHandler())                     // 跨域处理器
                .handler(CookieHandler.create())                // cookie处理器
                .handler(authHandler)                           // 权限认证处理器
                .handler(BodyHandler.create())                  // 请求体处理器
                .handler(new RequestParamsHandler())            // 请求参数处理器
                .handler(new ResponsePreHandler())              // 响应前置处理器(设置字符编码等)
                .handler(staticHandler);                        // 静态资源处理器


        // 挂载子路由
        List<SubRouter> subRouters = SpringUtil.getBeansByType(SubRouter.class);
        subRouters.forEach(subRouter -> {
            subRouter.mount(router);
            LOGGER.info("http 子路由 " + subRouter.getClass().getSimpleName() + " 挂载成功");
        });

        // 此处可挂载各种日志处理器
        // ....

        // 挂载后置处理器
        router.route()
                .handler(new ResponsePostHandler());            // 响应后置处理器, 对响应做最后的处理

    }
}
