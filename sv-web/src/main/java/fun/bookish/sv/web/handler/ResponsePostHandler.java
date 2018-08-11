package fun.bookish.sv.web.handler;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * 响应后置处理器
 */
public class ResponsePostHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext ctx) {
        ctx.response().end();
    }

}
