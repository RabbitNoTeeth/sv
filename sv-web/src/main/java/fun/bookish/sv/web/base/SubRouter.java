package fun.bookish.sv.web.base;

import io.vertx.ext.web.Router;

/**
 * 子路由接口, 用户自定义的路由只需要实现该接口, 并通过@Component注解放入spring容器中即可
 */
public interface SubRouter {

    void mount(Router router);

}
