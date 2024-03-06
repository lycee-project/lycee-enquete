package net.lycee.web.enquete.api.domain;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(QuestionType.class)
public class QuestionTypeTypeHandler extends BaseTypeHandler<QuestionType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, QuestionType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public QuestionType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) {
            return null;
        }
        return QuestionType.create(value);
    }

    @Override
    public QuestionType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return QuestionType.create(value);
    }

    @Override
    public QuestionType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return QuestionType.create(value);
    }
}
