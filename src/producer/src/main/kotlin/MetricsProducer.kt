import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*
import java.util.UUID

fun main() {
    val producer = MetricsProducer()
    val duration = producer.sendWithMeasurement()
    val rate = 100_000.0 / duration * 1000
    println("Время: ${duration}ms, msg/s: ${String.format("%.2f", rate)}")
}

class MetricsProducer() {

    fun sendWithMeasurement(): Long {
        val producer = KafkaProducer<String, String>(properties)
        val start = System.currentTimeMillis()
        repeat(100_000) {
            producer.send(ProducerRecord(topic, key, value))
        }

        producer.flush()
        producer.close()
        return System.currentTimeMillis() - start
    }

    private companion object {
        val bootstrapServers = "localhost:9092"
        val properties = object : Properties() {
            init {
                put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
                put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
                put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
                put(ProducerConfig.ACKS_CONFIG, "1")

            }
        }

        val topic = "kafka-metrics-go-service-value-add-v1"
        val key = UUID.randomUUID().toString()
        val value = UUID.randomUUID().toString()
    }
}