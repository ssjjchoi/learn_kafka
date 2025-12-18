package org.apache.kafka.clients.consumer;

import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.metrics.KafkaMetric;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * [CODE READING]
 * Kafka Consumer 인터페이스 학습용 요약판
 *
 * - KafkaConsumer의 주요 기능만 추출
 * - 실제 구현은 KafkaConsumer.java 참고
 * - poll(), commit, subscribe, seek 등 핵심 API 중심
 */
public interface KafkaConsumerInterface<K, V> extends Closeable {

    // ===================== Subscription =====================
    Set<TopicPartition> assignment();                     // 현재 할당된 파티션
    Set<String> subscription();                            // 현재 구독 토픽
    void subscribe(Collection<String> topics);            // 토픽 구독
    void subscribe(Collection<String> topics, ConsumerRebalanceListener callback); // 토픽+리밸런스 콜백
    void assign(Collection<TopicPartition> partitions);   // 특정 파티션 직접 할당
    void unsubscribe();                                   // 구독 해제
    void subscribe(Pattern pattern);                      // 패턴 구독
    void subscribe(Pattern pattern, ConsumerRebalanceListener callback);

    // ===================== Poll & Records =====================
    ConsumerRecords<K, V> poll(Duration timeout);         // 메시지 가져오기

    // ===================== Commit =====================
    void commitSync();                                    // 동기 커밋
    void commitSync(Duration timeout);                   
    void commitSync(Map<TopicPartition, OffsetAndMetadata> offsets); 
    void commitSync(Map<TopicPartition, OffsetAndMetadata> offsets, Duration timeout);
    void commitAsync();                                   // 비동기 커밋
    void commitAsync(OffsetCommitCallback callback);
    void commitAsync(Map<TopicPartition, OffsetAndMetadata> offsets, OffsetCommitCallback callback);

    // ===================== Metrics =====================
    void registerMetricForSubscription(KafkaMetric metric);
    void unregisterMetricFromSubscription(KafkaMetric metric);
    Map<MetricName, ? extends Metric> metrics();

    // ===================== Seek / Position =====================
    void seek(TopicPartition partition, long offset);
    void seek(TopicPartition partition, OffsetAndMetadata offsetAndMetadata);
    void seekToBeginning(Collection<TopicPartition> partitions);
    void seekToEnd(Collection<TopicPartition> partitions);
    long position(TopicPartition partition);
    long position(TopicPartition partition, Duration timeout);
    Map<TopicPartition, OffsetAndMetadata> committed(Set<TopicPartition> partitions);
    Map<TopicPartition, OffsetAndMetadata> committed(Set<TopicPartition> partitions, Duration timeout);

    // ===================== Topic / Partition Info =====================
    List<PartitionInfo> partitionsFor(String topic);
    List<PartitionInfo> partitionsFor(String topic, Duration timeout);
    Map<String, List<PartitionInfo>> listTopics();
    Map<String, List<PartitionInfo>> listTopics(Duration timeout);

    // ===================== Pause / Resume =====================
    Set<TopicPartition> paused();
    void pause(Collection<TopicPartition> partitions);
    void resume(Collection<TopicPartition> partitions);

    // ===================== Offsets =====================
    Map<TopicPartition, OffsetAndTimestamp> offsetsForTimes(Map<TopicPartition, Long> timestampsToSearch);
    Map<TopicPartition, OffsetAndTimestamp> offsetsForTimes(Map<TopicPartition, Long> timestampsToSearch, Duration timeout);
    Map<TopicPartition, Long> beginningOffsets(Collection<TopicPartition> partitions);
    Map<TopicPartition, Long> beginningOffsets(Collection<TopicPartition> partitions, Duration timeout);
    Map<TopicPartition, Long> endOffsets(Collection<TopicPartition> partitions);
    Map<TopicPartition, Long> endOffsets(Collection<TopicPartition> partitions, Duration timeout);
    OptionalLong currentLag(TopicPartition topicPartition);

    // ===================== Group & Rebalance =====================
    ConsumerGroupMetadata groupMetadata();
    void enforceRebalance();
    void enforceRebalance(String reason);

    // ===================== Close / Wakeup =====================
    void close();
    @Deprecated
    void close(Duration timeout);
    void close(CloseOptions option);                   // CloseOptions로 다양한 종료 옵션 지원
    void wakeup();                                      // poll() 등 블록 해제
}
