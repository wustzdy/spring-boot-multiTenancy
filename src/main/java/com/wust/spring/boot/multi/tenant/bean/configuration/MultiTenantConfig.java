package com.wust.spring.boot.multi.tenant.bean.configuration;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.wust.spring.boot.multi.tenant.bean.api.ExecutionContextProvider;
import com.wust.spring.boot.multi.tenant.bean.context.ApiContext;
import com.wust.spring.boot.multi.tenant.bean.context.CallerContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan("com.wust.spring.boot.multi.tenant.bean.mapper")
public class MultiTenantConfig {
    @Autowired
    private ApiContext apiContext;

    @Bean
    OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    @Bean
    public PaginationInterceptor paginationInterceptor(ExecutionContextProvider executionContextProvider) {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));

        // 创建SQL解析器集合
        List<ISqlParser> sqlParserList = new ArrayList<>();

        // 创建租户SQL解析器
        TenantSqlParser tenantSqlParser = new TenantSqlParser();

        // 设置租户处理器
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            // 设置当前租户ID，实际情况你可以从cookie、或者缓存中拿都行
            @Override
            public Expression getTenantId(boolean select) {
                // 从当前系统上下文中取出当前请求的服务商ID，通过解析器注入到SQL中。
                CallerContext callerContext = executionContextProvider.current().get(CallerContext.CONTEXT_KEY);
                if (null == callerContext) {
                    throw new RuntimeException("Get CurrentProviderId error.");
                }
                return new LongValue(callerContext.getTenantId());
            }

            @Override
            public String getTenantIdColumn() {
                // 对应数据库中租户ID的列名
                return "tenant_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 是否需要需要过滤某一张表
               /* List<String> tableNameList = Arrays.asList("sys_user");
                if (tableNameList.contains(tableName)) {
                    return true;
                }*/
                return false;
            }
        });

        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(metaObject -> {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            if (ms.getId().startsWith("com.wust.spring.boot.multi.tenant.bean.mapper.Tenant")) {
                return true;
            }
            return false;
        });

        return paginationInterceptor;
    }
}
