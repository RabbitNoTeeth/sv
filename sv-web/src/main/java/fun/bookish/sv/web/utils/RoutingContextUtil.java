package fun.bookish.sv.web.utils;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RoutingContextUtil {

    private RoutingContextUtil(){}

    private static final String REQUEST_PARAMS_KEY = "REQUEST_PARAMS";

    /**
     * 将请求参数放入context上下文
     * @param context
     * @param params
     */
    public static void setRequestParams(RoutingContext context, JsonObject params){
        context.put(REQUEST_PARAMS_KEY, params);
    }

    /**
     * 从context上下文获取请求参数
     * @param context
     * @return
     */
    public static JsonObject getRequestParams(RoutingContext context){
        return context.get(REQUEST_PARAMS_KEY);
    }

    /**
     * 向response写入数据,并跳转下一个handler
     * @param context
     * @param json
     */
    public static void writeAndNext(RoutingContext context, String json){
        context.response().write(json);
        context.next();
    }

}
