package org.apache.kafka.clients.consumer;
// Kafka 공식 Consumer API 패키지 위치 #위치기준

import java.util.List;
import java.util.Collections;

/**
 * [CODE READING]
 * 역할: KafkaConsumer.poll()의 결과 컨테이너
 *
 * - 여러 ConsumerRecord의 묶음
 * - topic / partition 단위로 레코드를 담는다
 * - 이 클래스 자체는 로직이 거의 없음 (순수 결과 객체)
 */
public class ConsumerRecords<K, V> {

    /**
     * 실제 Kafka:
     * - Map<TopicPartition, List<ConsumerRecord<K, V>>> 구조
     *
     * 여기서는 구조 이해용으로 단순화
     */
    private final List<ConsumerRecord<K, V>> records;

    public ConsumerRecords() {
        // 학습용: 빈 결과
        this.records = Collections.emptyList();
    }

    /**
     * poll() 이후 user code가 가장 많이 호출하는 메서드
     *
     * @return ConsumerRecord 리스트
     */
    public Iterable<ConsumerRecord<K, V>> records() {
        return records;
    }

    /**
     * 수신한 레코드 개수
     */
    public int count() {
        return records.size();
    }
}