package org.joker.oscp.user.entity.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joker.oscp.user.entity.UserSexEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link UserSexEnum} 类型映射器
 * @author JOKER
 */
public class UserSexEnumTypeHandler extends BaseTypeHandler<UserSexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UserSexEnum userSexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, userSexEnum.value());
    }

    @Override
    public UserSexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }
        String userSexCode = resultSet.getString(s);
        return UserSexEnum.valueOf(userSexCode);
    }

    @Override
    public UserSexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }
        String userSexCode = resultSet.getString(i);
        return UserSexEnum.valueOf(userSexCode);
    }

    @Override
    public UserSexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.wasNull()) {
            return null;
        }
        String userSexCode = callableStatement.getString(i);
        return UserSexEnum.valueOf(userSexCode);
    }
}
