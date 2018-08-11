# sv
SpringBoot与vertx集成应用骨架(已集成web模块)

# 为什么要做这样一个应用?
相信很多人像我一样, 曾经抱着尝鲜的态度去接触vertx, 从此无法自拔. vertx很棒, 虽然vertx web与spring mvc完全是两种不同的http服务编程方式, 但是我觉得vertx与
spring boot是不冲突的, 我认为只把spring boot当作容器来用,体验是相当棒的,除了最基础的容器功能,spring boot提供的对yaml文件的解析以及bootJar打包,都是非
常实用的, 在我的博客后端应用中, 没有用到spring boot, 是单纯切干净的vertx, 但是在加载配置文件上需要自己写一套实现方式, 虽然也实现了对yaml的解析支持,但是
在具体配置上依旧不如spring boot功能强大. </br>
曾经我也是一个纯粹主义者, 认为既然上了vertx,那就不上spring的任何东西, 但是随着工作和业余时间里做的项目体验来讲, 将vertx嫁接到springboot中, 开发体验确实
是相当不错的, 同时也只是用了springboot基础的容器功能,并没有引入其他starter, 也没有增加太大的复杂性, 所以才搞了这样一个简单的小东西, 权当做以后做新web项目时
的初始结构, 开箱即用了.
