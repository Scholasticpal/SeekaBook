package com.vit.seekabook.repo;

import com.vit.seekabook.domain.User;
import com.vit.seekabook.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SeekABookRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getUsers(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        return namedParameterJdbcTemplate.query("SELECT email,\n" +
                "    user_type,\n" +
                "    first_name,\n" +
                "    last_name,\n" +
                "    address,\n" +
                "    city,\n" +
                "    country,\n" +
                "    post_code\n" +
                "FROM user_detail\n" +
                "WHERE email = :email", params, new RowMapper<>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User u = new User();
                u.setEmail(rs.getString("email"));
                u.setUserType(UserType.valueOf(rs.getString("user_type")).name());
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setAddress(rs.getString("address"));
                u.setCity(rs.getString("city"));
                u.setCountry(rs.getString("country"));
                u.setPostalCode(rs.getString("post_code"));
                return u;
            }
        });
    }
}
