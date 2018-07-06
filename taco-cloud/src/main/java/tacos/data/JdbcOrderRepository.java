package tacos.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.Order;
import tacos.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {
	
	/*
	 * SimpleJdbcInsert is an object that wraps JdbcTemplate to it easier to insert data into
a table.
	 */
	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;  //Jackson’s ObjectMapper
	
	
	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		/*
		 * assigned to the orderInserter instance variable, is configured to work with 
		 * the Taco_Order table and to assume that the id property will be provided/generated 
		 * by the database.
		 */
	this.orderInserter = new SimpleJdbcInsert(jdbc)
	.withTableName("Taco_Order")
	.usingGeneratedKeyColumns("id");
	
	this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
	.withTableName("Taco_Order_Tacos");
	this.objectMapper = new ObjectMapper();
	}

	
	@Override
	public Order save(Order order) {
		
		order.setPlacedAt(new Date());
		long orderId = saveOrderDetails(order);
		order.setId(orderId);
		List<Taco> tacos = order.getTacos();
		for (Taco taco : tacos) {
		saveTacoToOrder(taco, orderId);
		}
		return order;
	}
	
	// save order and returns order ID
	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values =
		objectMapper.convertValue(order, Map.class);
		values.put("placedAt", order.getPlacedAt());
		long orderId =
		orderInserter
		.executeAndReturnKey(values)
		.longValue();
		return orderId;
		}
	
	// save order id and taco id in Taco_Order_Tacos table
		private void saveTacoToOrder(Taco taco, long orderId) {
			
		Map<String, Object> values = new HashMap<>();
		values.put("tacoOrder", orderId);
		values.put("taco", taco.getId());
		orderTacoInserter.execute(values);
		}


		@Override
		public <S extends Order> Iterable<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Optional<Order> findById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public boolean existsById(Long id) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public Iterable<Order> findAll() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Iterable<Order> findAllById(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public void deleteById(Long id) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void delete(Order entity) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void deleteAll(Iterable<? extends Order> entities) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}


		@Override
		public List<Order> findByZip(String zip) {
			// TODO Auto-generated method stub
			return null;
		}

}
