package com.soeasyeasy.db.core;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 字段加解密
 *
 * @author hc
 * @date 2025/03/14
 */
@MappedTypes(String.class)
public class EncryptTypeHandler extends BaseTypeHandler<String> {

    //private final Encryptor encryptor = new AESEncryptor();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    String parameter, JdbcType jdbcType) {
        //ps.setString(i, encryptor.encrypt(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) {
        //return encryptor.decrypt(rs.getString(columnName));
        return null;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}