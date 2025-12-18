package org.apache.kafka.clients.producer;
// Kafka 공식 Producer API 패키지 위치임을 명확히 보여줌 #위치기준

/**
 * [CODE READING]
 * 역할: KafkaProducer.send()에 전달되는 메시지 컨테이너
 *
 * - 데이터만 보관 (로직 없음)
 * - partition / key / value를 담는다
 * - 실제 전송과 파티셔닝은 KafkaProducer 내부에서 수행된다
 */
public class ProducerRecord<K, V> {
// K = key 타입, V = value 타입 #제네릭으로 타입 유연성 확보

    private final String topic;         // 메시지를 보낼 대상 토픽 이름 #어디로 보낼지
    private final Integer partition;    // 지정 파티션 번호, 값 null이면 producer가 나중에 결정 #위임가능
    private final K key;                // 파티셔닝 및 순서 보장을 위한 key #여기서는 보관만
    private final V value;              // 실제 메시지 본문 #직렬화 전 원본 데이터
    private final Long timestamp;       // 메시지 시간 정보, null이면 producer가 현재시간 부여 #선택값

    public ProducerRecord(String topic, Integer partition, Long timestamp, K key, V value) {
        // ProducerRecord의 모든 정보를 한 번에 받는 기본 생성자 #완전체
        this.topic = topic;             // 토픽 확정
        this.partition = partition;     // 파티션 지정 or 미지정(null)
        this.timestamp = timestamp;     // 타임스탬프 지정 or 미지정(null)
        this.key = key;                 // key 저장 (아직 아무 처리 안 함)
        this.value = value;             // value 저장 (아직 아무 처리 안 함)
    }

    public ProducerRecord(String topic, K key, V value) {
        // 가장 많이 쓰는 간편 생성자 #자주 사용
        this(topic, null, null, key, value);
        // partition, timestamp는 Producer에게 맡김
    }

    public String topic() { return topic; }             // KafkaProducer가 토픽을 조회할 때 사용
    public Integer partition() { return partition; }    // partition 지정 여부를 Producer가 판단할 때 사용
    public K key() { return key; }                      // 파티셔너/직렬화 단계로 넘길 key 조회
    public V value() { return value; }                  // serializer로 넘길 value 조회
    public Long timestamp() { return timestamp; }       // 타임스탬프 존재 여부 확인용
}