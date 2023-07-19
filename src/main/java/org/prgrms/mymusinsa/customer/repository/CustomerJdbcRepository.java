package org.prgrms.mymusinsa.customer.repository;

import lombok.AllArgsConstructor;
import org.prgrms.mymusinsa.customer.domain.Customer;
import org.prgrms.mymusinsa.customer.domain.Email;
import org.prgrms.mymusinsa.product.domain.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CustomerJdbcRepository {

    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customers(customer_id, email, password, name, address, " +
        "interested_category)"
    + "VALUES(UUID_TO_BIN(:customerId), :email, :password, :name, :address, :interestedCategory)";
    private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM customers WHERE email = :email AND password = :password";

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Customer> customerRowMapper = (resultSet, rowNumber) -> {
        UUID customerId = toUUID(resultSet.getBytes("customer_id"));
        Email email = new Email(resultSet.getString("email"));
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        Category category = Category.valueOf(resultSet.getString("interested_category"));

        return new Customer(customerId, email, password, name, address, category);
    };

    public Customer insert(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL,
            toCreateMapSqlParams(customer)
        );
        return customer;
    }

    public Customer findByEmailAndPassword(Customer customer) {
        return jdbcTemplate.queryForObject(FIND_BY_EMAIL_AND_PASSWORD_SQL,
            toLoginMapSqlParams(customer),
            customerRowMapper);
    }

    private MapSqlParameterSource toCreateMapSqlParams(Customer customer) {
        return new MapSqlParameterSource().addValue("customerId", customer.getCustomerId().toString())
            .addValue("email", customer.getEmail().getEmailAddress())
            .addValue("password", customer.getPassword())
            .addValue("name", customer.getName())
            .addValue("address", customer.getAddress())
            .addValue("interestedCategory", customer.getInterestedCategory().toString());
    }

    private MapSqlParameterSource toLoginMapSqlParams(Customer customer) {
        return new MapSqlParameterSource().addValue("email", customer.getEmail().getEmailAddress())
            .addValue("password", customer.getPassword());
    }


    private UUID toUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

}
