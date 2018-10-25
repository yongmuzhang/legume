# soybean(毛豆)


soybeans是一款快速搭建以spring boot为基础框架的java restful风格的后端服务脚手架。
## Include(包含)
> Data Migration(数据迁移)

> Swagger(在线API文档框架)

> Exception(统一风格的异常处理)

> Validator(参数校验器)

> EventPublisher(事件发布器)

> RateLimiter(API限流器)

> OAuth(鉴权)

> 命令调用(命令调用)

## 详细介绍
### Data Migration(数据迁移)
```
帮助更好的管理数据库版本。当容器启动时自动装载运行准备好的数据库脚本，如果version已经存在查分更新数据库脚本。
使用：
    1、在class目录创建数据库脚本目录(如db/migration)，把准备好的数据库脚本文件(V1__Base_version.sql)拷贝到此目录。
    2、配置application.properties:spring.flyway.locations=classpath:db/migration
    3、启动容器即可。如果生产环境需要关闭配置application.properties:spring.flyway.enabled=false
```
### Swagger(在线API文档框架)
```
帮助更好的管理API文档。做到输出modle修改，文档随之自动更新。
使用：
    1、开启swagger在application.properties:soybean.swagger.enabled=true(关闭false)
    2、具体怎么配置见http://springfox.github.io/springfox。
```
### Exception(统一风格的异常处理)
```
帮助更好的管理API文档。做到输出modle修改，文档随之自动更新。
使用：
    1、开启exception在application.properties:soybean.exception.enabled=true(关闭false)，
       如果没有开启，使用spring boot提供的异常处理风格或者自定义实现
    soybean异常api返回格式：
        {
          "message": "提现金额不能大于账户金额",
          "errorCode": "0",
          "exception": "java.lang.RuntimeException",
          "status": 400,
          "error": "业务错误"
        }
    2、提供了基础的异常类，自定义异常：
        @ResponseStatus(value = HttpStatus.UNAUTHORIZED) //http返回码
        @ErrorCode(value = ApiErrorCode.NOT_EXIST) //ErrorCode返回码
        public class AuthorizationException extends BaseException {//继承异常基类
        
          public AuthorizationException(String message) {
            super(message);
          }
        }
```
### Validator(参数校验器)
```
提供注解的请求参数检验。
使用：
    1、默认提供了钱的检验注解和枚举类型的校验注解，其他逐步完善，欢迎PR
    2、开启检验注解 @Validated
    参数model：
    public class ParamForm {
        @NotEmpty(message = "密码重置类型不能为空")
        @Enumeration(targetClassType = ResetPasswordType.class, message = "密码重置类型不对")
        private String type;
        @NotNull(message = "退出金额不能为空")
        @Money(message = "退出金额格式错误")
        private Double amount;
    }
    
```
### EventPublisher(事件发布中心)
```
为事件的发布订阅提供支持，默认实现是spring提供的事件订阅发布组件。
使用：
    @Autowired
    private EventPublisher eventPublisher;
    异步或者同步调用:
    eventPublisher.publish(xxxxxEvent);
```

> 代码规范遵守[阿里云Java代码规约](https://github.com/alibaba/p3c)