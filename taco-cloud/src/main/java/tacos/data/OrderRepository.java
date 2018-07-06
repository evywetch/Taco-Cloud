package tacos.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tacos.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

	Order save(Order order);
	List<Order> findByZip(String zip);
}
