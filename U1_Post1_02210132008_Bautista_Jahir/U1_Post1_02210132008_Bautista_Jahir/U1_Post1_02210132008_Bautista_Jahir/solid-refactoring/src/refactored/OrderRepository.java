package refactored;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    List<Order> findAll();

    Order findById(String id);
}