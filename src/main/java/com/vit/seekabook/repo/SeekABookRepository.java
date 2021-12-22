package com.vit.seekabook.repo;

import com.vit.seekabook.domain.BookAd;
import com.vit.seekabook.domain.User;
import com.vit.seekabook.domain.UserType;
import com.vit.seekabook.dto.BookAdPostDto;
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

    public void saveBookAd(BookAdPostDto bookAdPostDto) {
        Map<String, String> params = new HashMap<>();
        params.put("seller_email", bookAdPostDto.getSellerEmail());
        params.put("book_title", bookAdPostDto.getBookTitle());
        params.put("price", bookAdPostDto.getPrice());
        params.put("book_description", bookAdPostDto.getBookDescription());
        params.put("book_review", bookAdPostDto.getBookReview());
        namedParameterJdbcTemplate.update("INSERT INTO book_ads (seller_email, " +
                "book_title, price, book_description, book_review) \n" +
                "VALUES (:seller_email, :book_title, :price, :book_description, :book_review);", params);
    }

    public List<BookAd> getAllBooks() {
        return namedParameterJdbcTemplate.query("SELECT b.ad_id,\n" +
                "    b.seller_email,\n" +
                "    b.book_title,\n" +
                "    b.price,\n" +
                "    b.book_description,\n" +
                "    b.book_review,\n" +
                "    b.approved_by_admin,\n" +
                "    u.email,\n" +
                "    u.user_type,\n" +
                "    u.first_name,\n" +
                "    u.last_name,\n" +
                "    u.address,\n" +
                "    u.city,\n" +
                "    u.country,\n" +
                "    u.post_code\n" +
                "FROM book_ads b, user_detail u\n" +
                "WHERE b.seller_email = u.email;\n", new RowMapper<>() {
            @Override
            public BookAd mapRow(ResultSet rs, int rowNum) throws SQLException {
                BookAd b = new BookAd();
                b.setAdID(rs.getLong("ad_id"));
                b.setBookTitle(rs.getString("book_title"));
                b.setPrice(rs.getString("price"));
                b.setBookDescription(rs.getString("book_description"));
                b.setBookReview(rs.getString("book_review"));
                String approvedByAdmin = rs.getString("approved_by_admin");
                b.setApprovedByAdmin("Yes".equalsIgnoreCase(approvedByAdmin) ? true : false);
                User u = new User();
                u.setEmail(rs.getString("email"));
                u.setUserType(UserType.valueOf(rs.getString("user_type")).name());
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setAddress(rs.getString("address"));
                u.setCity(rs.getString("city"));
                u.setCountry(rs.getString("country"));
                u.setPostalCode(rs.getString("post_code"));
                b.setSeller(u);
                return b;
            }
        });
    }
}
