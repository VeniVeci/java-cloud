package com.zukxu.common.datasource.support;

import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.zukxu.common.datasource.config.properties.DataSourceProperties;
import com.zukxu.common.datasource.constants.DataSourceConstants;
import org.jasypt.encryption.StringEncryptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 从数据源中获取数据库配置信息
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 16:30
 */
public class JdbcDynamicDataSourceProvider extends AbstractJdbcDataSourceProvider {

    private final DataSourceProperties properties;

    private final StringEncryptor stringEncryptor;

    public JdbcDynamicDataSourceProvider(DataSourceProperties properties, StringEncryptor stringEncryptor) {
        super(properties.getDriverClassName(), properties.getUrl(), properties.getUsername(), properties.getPassword());
        this.properties = properties;
        this.stringEncryptor = stringEncryptor;
    }

    /**
     * 执行语句获得数据源信息
     *
     * @param statement sql语句
     * @return 数据源参数
     * @throws SQLException
     */
    @Override
    protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery(properties.getQueryDsSql());

        Map<String, DataSourceProperty> map = new HashMap<>(8);
        while (rs.next()) {
            String name = rs.getString(DataSourceConstants.DS_NAME);
            String username = rs.getString(DataSourceConstants.DS_JDBC_USER_NAME);
            String password = rs.getString(DataSourceConstants.DS_JDBC_PWD);
            String url = rs.getString(DataSourceConstants.DS_JDBC_URL);
            DataSourceProperty property = new DataSourceProperty();
            property.setUsername(username);
            property.setLazy(true);
            property.setPassword(stringEncryptor.decrypt(password));
            property.setUrl(url);
            map.put(name, property);
        }

        // 添加默认主数据源
        DataSourceProperty property = new DataSourceProperty();
        property.setUsername(properties.getUsername());
        property.setPassword(properties.getPassword());
        property.setUrl(properties.getUrl());
        property.setLazy(true);
        map.put(DataSourceConstants.DS_MASTER, property);
        return map;
    }
}
