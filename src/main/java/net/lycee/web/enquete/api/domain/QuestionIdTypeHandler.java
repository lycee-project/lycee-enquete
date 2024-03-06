package net.lycee.web.enquete.api.domain;

import net.lycee.web.enquete.api.entity.QuestionEntity;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(QuestionId.class)
public class QuestionIdTypeHandler extends BaseTypeHandler<QuestionId> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, QuestionId parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.value());
    }

    @Override
    public QuestionId getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) {
            return null;
        }
        return new QuestionId(value);
    }

    @Override
    public QuestionId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return new QuestionId(value);
    }

    @Override
    public QuestionId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return new QuestionId(value);
    }
}
