package com.myboot.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * (sql 复杂自定义类查询)
 * 2015年7月1日 上午11:44:13
 * Created by majf
 *
 */
@Slf4j
@Repository
public class JdbcDaoImpl {

    private JdbcTemplate spaJdbcTemplate;

    @Autowired
    public JdbcDaoImpl(@Qualifier("jdbcTemplate") JdbcTemplate spaJdbcTemplate) {
        this.spaJdbcTemplate = spaJdbcTemplate;
    }

    /**
     * (根据Sql查询返回自定义类结果集合)
     * @param sql sql 查询语句
     * @param params 参数列表
     * @param clazz　查询结果实例类
     * @param <T> 实例类
     * @return 返回结果T列表
     */
    @SuppressWarnings("unchecked")
    public   <T> List<T> querySpaDataList(String sql, Object[] params, Class<T> clazz) {
        printSqlToConsole(sql, params);
        return spaJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<T>(clazz));
    }

    /**
     * 拼接sql语句
     * @param sql　查询语句
     * @param params　查询参数
     * @return 返回带参数内容的查询字符串
     */
    private String printSql(String sql, Object[] params) {
        StringBuilder strBuilder = new StringBuilder();
        if (null != params && params.length > 0) {
            int index = sql.indexOf("?");
            int i = 0;
            int start = 0;
            while (index != -1) {
                if (params[i] instanceof String) {
                    strBuilder.append(sql.substring(start, index));
                    strBuilder.append("'");
                    strBuilder.append(params[i]);
                    strBuilder.append("' ");
                } else {
                    strBuilder.append(sql.substring(start, index));
                    strBuilder.append(params[i]);
                    strBuilder.append(" ");
                }
                index++;
                i++;
                start = index;
                index = sql.indexOf("?", index);
            }
            if (start < sql.length()) {
                strBuilder.append(sql.substring(start));
            }
        } else {
            strBuilder.append(sql);
        }

        return strBuilder.toString();
    }

    /**
     * 打印查询语句
     * @param sql　查询语句
     * @param params 查询参数
     */
    private void printSqlToConsole(String sql, Object[] params) {
        log.info("executeSelectSql sql\n" + printSql(sql, params));
    }
}
