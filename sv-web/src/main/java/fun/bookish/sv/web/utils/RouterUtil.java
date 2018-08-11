package fun.bookish.sv.web.utils;

import fun.bookish.sv.core.utils.SpringUtil;
import fun.bookish.sv.web.router.SubRouter;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RouterUtil {

    private RouterUtil(){}

    private static final Logger LOGGER = LoggerFactory.getLogger("sv-web");

    public static void registerSubRouter(Router router){
        List<SubRouter> subRouters = SpringUtil.getBeansByType(SubRouter.class);
        subRouters.forEach(subRouter -> {
            subRouter.mount(router);
            LOGGER.info("http子路由 " + subRouter.getClass().getSimpleName() + " 挂载成功");
        });
    }
}
