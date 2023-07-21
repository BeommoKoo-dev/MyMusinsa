package org.prgrms.mymusinsa.order.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.prgrms.mymusinsa.order.domain.Order;
import org.prgrms.mymusinsa.order.domain.OrderItem;
import org.prgrms.mymusinsa.order.domain.OrderStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Repository
public class JdbcOrderRepository implements OrderRepository{

    private static final String INSERT_ORDER_SQL = "INSERT INTO orders(order_id, customer_id, address, postcode, " +
        "order_status, created_at, updated_at)" +
        "VALUES(UUID_TO_BIN(:orderId), UUID_TO_BIN(:customerId), :address, :postcode, :orderStatus, :createdAt, :updatedAt)";
    private static final String INSERT_ORDER_ITEMS_SQL = "INSERT INTO order_items(order_id, product_id, quantity)" +
        "VALUES(UUID_TO_BIN(:orderId), UUID_TO_BIN(:productId), :quantity)";
    private static final String INCREMENT_SALES_COUNT_SQL = "UPDATE products SET sales_count = sales_count + (:quantity) " +
        "WHERE product_id = UUID_TO_BIN(:productId)";
    private static final String UPDATE_ORDER_SQL = "UPDATE orders " +
        "SET address = :address, postcode = :postcode, order_status = :orderStatus, updated_at = :updatedAt " +
        "WHERE order_id = UUID_TO_BIN(:orderId)";
    private static final String DELETE_ORDER_BY_ID_SQL = "DELETE FROM orders WHERE order_id = UUID_TO_BIN(:orderId)";
    private static final String DELETE_ORDER_ITEMS_BY_ID_SQL = "DELETE FROM order_items WHERE order_id = UUID_TO_BIN(:orderId)";
    private static final String FIND_ALL_SQL = "SELECT * FROM orders INNER JOIN order_items ON orders.order_id = order_items.order_id";
    private static final String FIND_ITEMS_BY_ID_SQL = "SELECT * FROM order_items WHERE order_id = UUID_TO_BIN(:orderId)";
    private static final String FIND_ORDER_BY_ID_SQL = "SELECT * FROM orders WHERE order_id = UUID_TO_BIN(:orderId)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderRowMapper = (resultSet, rowNumber) -> {
        UUID orderId = toUUID(resultSet.getBytes("order_id"));
        UUID customerId = toUUID(resultSet.getBytes("customer_id"));
        OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        String address = resultSet.getString("address");
        String postcode = resultSet.getString("postcode");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        return new Order(orderId, customerId, null, orderStatus, address, postcode, createdAt, updatedAt);
    };

    private final RowMapper<OrderItem> orderItemRowMapper = (resultSet, rowNumber) -> {
        UUID productId = toUUID(resultSet.getBytes("product_id"));
        int quantity = resultSet.getInt("quantity");

        return new OrderItem(productId, quantity);
    };

    @Override
    public Order insert(Order order) {
        jdbcTemplate.update(INSERT_ORDER_SQL, toMapSqlParams(order));
        order.getOrderItems().forEach(orderItem -> {
                jdbcTemplate.update(INSERT_ORDER_ITEMS_SQL, toMapSqlParmas(order.getOrderId(), orderItem));
                jdbcTemplate.update(INCREMENT_SALES_COUNT_SQL, toMapSqlParmas(order.getOrderId(), orderItem));
            }
        );
        return order;
    }

    @Override
    public void update(Order order) {
        UUID orderId = order.getOrderId();
        jdbcTemplate.update(DELETE_ORDER_ITEMS_BY_ID_SQL, Collections.singletonMap("orderId", orderId.toString()));
        jdbcTemplate.update(UPDATE_ORDER_SQL, toMapSqlParams(orderId, order));
        order.getOrderItems().forEach(orderItem ->
            jdbcTemplate.update(INSERT_ORDER_ITEMS_SQL, toMapSqlParmas(orderId, orderItem)));
    }

    @Override
    public void deleteById(UUID orderId) {
        jdbcTemplate.update(DELETE_ORDER_BY_ID_SQL, Collections.singletonMap("orderId", orderId.toString()));
        jdbcTemplate.update(DELETE_ORDER_ITEMS_BY_ID_SQL, Collections.singletonMap("orderId", orderId.toString()));
    }

    @Override
    public List<Order> findAll() {
        List<Order> allOrders= jdbcTemplate.query(FIND_ALL_SQL, orderRowMapper);
        allOrders.forEach(order -> {
            List<OrderItem> orderItems = jdbcTemplate.query(FIND_ITEMS_BY_ID_SQL,
                Collections.singletonMap("orderId", order.getOrderId().toString()),
                orderItemRowMapper);
            order.setOrderItems(orderItems);
        });
        return allOrders;
    }

    @Override
    public Optional<Order> findOrderById(UUID orderId) {
        try {
            Order order = jdbcTemplate.queryForObject(FIND_ORDER_BY_ID_SQL,
                Collections.singletonMap("orderId", orderId.toString()),
                orderRowMapper);
            List<OrderItem> orderItems = jdbcTemplate.query(FIND_ITEMS_BY_ID_SQL,
                Collections.singletonMap("orderId", orderId.toString()),
                orderItemRowMapper);
            order.setOrderItems(orderItems);
            return Optional.of(order);
        } catch (EmptyResultDataAccessException e) {
            log.warn(e.toString());
            return Optional.empty();
        }
    }

    private MapSqlParameterSource toMapSqlParams(Order order) {
        return new MapSqlParameterSource().addValue("orderId", order.getOrderId().toString())
            .addValue("customerId", order.getCustomerId().toString())
            .addValue("address", order.getAddress())
            .addValue("postcode", order.getPostcode())
            .addValue("orderStatus", order.getOrderStatus().toString())
            .addValue("createdAt", order.getCreatedAt())
            .addValue("updatedAt", order.getUpdatedAt());
    }

    private MapSqlParameterSource toMapSqlParams(UUID orderId, Order order) {
        return new MapSqlParameterSource("orderId", orderId.toString())
            .addValue("address", order.getAddress())
            .addValue("postcode", order.getPostcode())
            .addValue("orderStatus", order.getOrderStatus().toString())
            .addValue("updatedAt", order.getUpdatedAt());
    }

    private MapSqlParameterSource toMapSqlParmas(UUID orderId, OrderItem orderItem) {
        return new MapSqlParameterSource().addValue("orderId", orderId.toString())
            .addValue("productId", orderItem.productId().toString())
            .addValue("quantity", orderItem.quantity());
    }

    private UUID toUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

}
