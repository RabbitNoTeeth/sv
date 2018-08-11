package fun.bookish.sv.web.handler;

import fun.bookish.sv.web.utils.RoutingContextUtil;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

/**
 * 请求参数处理器
 */
public class RequestParamsHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext ctx) {
        HttpMethod method = ctx.request().method();
        if(method == HttpMethod.GET || method == HttpMethod.DELETE ||
                method == HttpMethod.PUT || method == HttpMethod.POST){
            MultiMap multiMap = ctx.request().params();
            JsonObject params = new JsonObject();
            for(Map.Entry<String,String> entry:multiMap.entries()){
                params.put(entry.getKey(),entry.getValue());
            }
            //将参数存入上下文,便于子路由获取
            RoutingContextUtil.setRequestParams(ctx, params);
        }
        ctx.next();
    }

}
