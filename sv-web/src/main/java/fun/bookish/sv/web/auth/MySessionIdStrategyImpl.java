package fun.bookish.sv.web.auth;

import fun.bookish.vertx.auth.simple.configurable.SessionIdStrategy;
import io.vertx.ext.web.RoutingContext;

public class MySessionIdStrategyImpl implements SessionIdStrategy {

    @Override
    public String getSessionId(RoutingContext context) {
        String header = context.request().getHeader("JSESSIONID");
        return header != null ? header : context.request().getParam("JSESSIONID");
    }

    @Override
    public void writeSessionId(String sessionId, RoutingContext context) {
        context.response().putHeader("JSESSIONID",sessionId);
    }

}
