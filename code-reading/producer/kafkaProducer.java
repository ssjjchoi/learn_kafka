package org.apache.kafka.clients.producer;
// Kafka 공식 Producer API 패키지 위치 #위치기준

/**
 * [CODE READING]
 * 역할: ProducerRecord를 받아 Kafka 브로커로 전송하는 진입점
 * 
 * ┌───────────── 전체 흐름 위치 요약 ─────────────
 *
 * [User Code]
 *    |
 *    | new ProducerRecord(...)
 *    |
 *    v
 * KafkaProducer.send(record)   ← ★ 이 클래스의 책임 구간
 *    |
 *    | → 파티션 결정
 *    | → 직렬화
 *    | → RecordAccumulator
 *    | → Sender Thread
 *    v
 * [Kafka Broker]
 *
 * └──────────────────────────────────────────────
 *
 * - send()를 통해 메시지 전송 요청을 받는다
 * - 실제 내부에서는 직렬화, 파티셔닝, 배치, 네트워크 전송이 일어난다
 * - 이 파일은 "흐름 이해용"으로 내부 구현은 생략한다
 */
public class KafkaProducer<K, V> {
// K = key 타입, V = value 타입 #ProducerRecord와 타입 연결

    /**
     * KafkaProducer 생성자
     *
     * 실제 Kafka:
     * - 설정값(Properties) 검증
     * - Serializer 초기화
     * - RecordAccumulator 생성
     * - Sender thread 시작
     *
     * 여기서는 "Producer는 이런 준비를 한다"는 의미만 남김
     */
    public KafkaProducer() {
        // 실제 구현에서는 수많은 설정과 초기화가 이루어짐
        // 학습용이므로 비워둠
    }

    /**
     * [핵심 메서드]
     * ProducerRecord를 받아 전송을 시작하는 API
     *
     * @param record 사용자가 만든 메시지 컨테이너
     */
    public void send(ProducerRecord<K, V> record) {
        // 1. 사용자 코드 → Producer 진입점
        //    user → KafkaProducer.send(record)

        // 2. record 내용 확인 (topic, key, value, partition 등)
        String topic = record.topic();        // 보낼 토픽
        Integer partition = record.partition(); // 지정 파티션 or null
        K key = record.key();                  // key
        V value = record.value();              // value

        // 3. 실제 Kafka에서는 여기서부터 내부 파이프라인으로 이동
        //    send() → doSend() → RecordAccumulator.append()

        // 학습용이므로 실제 전송 로직은 생략
    }

    /**
     * Producer 종료
     *
     * 실제 Kafka:
     * - 버퍼에 남은 레코드 전송
     * - 네트워크 스레드 종료
     * - 리소스 정리
     */
    public void close() {
        // 자원 정리용 메서드
        // 학습용이므로 비워둠
    }
}
