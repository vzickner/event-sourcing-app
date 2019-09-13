package com.example.orderapp;

import com.example.orderapp.core.condiment.CondimentId;
import com.example.orderapp.core.order.Order;
import com.example.orderapp.core.order.OrderId;
import com.example.orderapp.core.order.OrderRepository;
import kafka.KafkaTest;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(topics = OrderAppApplicationTests.TOPIC)
@ActiveProfiles("test")
public class OrderAppApplicationTests {

	static final String TOPIC = "orders";

	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;

	@Autowired
	private OrderRepository orderRepository;

	private Consumer<String, String> consumer;

	@Before
	public void setUp() throws Exception {
		Map<String, Object> props = KafkaTestUtils.consumerProps("test-broker", "false", embeddedKafkaBroker);
		consumer = new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new StringDeserializer()).createConsumer();
		consumer.subscribe(Collections.singleton(TOPIC));
		consumer.poll(0);
	}

	@After
	public void tearDown() throws Exception {
		consumer.close();
	}

	@Test
	public void testKafkaTopic() {
		// Arrange
		OrderId orderId = new OrderId();
		List<CondimentId> condiments = Collections.singletonList(new CondimentId());

		// Act
		new Order.Builder()
				.setOrderId(orderId)
				.setName("Paul")
				.setCondiments(condiments)
				.build();

		// Assert
		ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(consumer, TOPIC);
		assertThat(record).isNotNull();
		assertThat(record.key()).isEqualTo(orderId.getValue());

		Order order = this.orderRepository.findById(orderId);
		assertThat(order).isNotNull();
		assertThat(order.getName()).isEqualTo("Paul");
		assertThat(order.getCondiments()).isEqualTo(condiments);
	}

}
