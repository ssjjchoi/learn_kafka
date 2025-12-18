# 02. Clients Structure


```text
## Producer 전체 흐름 요약
[User Code]
   |
   | new ProducerRecord(...)
   |
   v
KafkaProducer.send(record)
   |
   | (실제 구현에서는)
   | → 파티션 결정
   | → 직렬화
   | → RecordAccumulator
   | → Sender Thread
   v
[Kafka Broker]


## Consumer 전체 흐름 요약
[User Code]
   |
   | KafkaConsumer.subscribe(...)
   |
   v
KafkaConsumer.poll()
   |
   | (실제 구현에서는)
   | → Coordinator(Group 관리, rebalance)
   | → Fetch 요청 생성
   | → NetworkClient
   | → Broker fetch 응답 수신
   | → 역직렬화
   | → ConsumerRecords 생성
   v
[User Code]
```


---

## Producer / Consumer 대칭 구조로 바라보기

Producer와 Consumer는 사용 방식은 다르지만  
내부 구조를 흐름 기준으로 보면 다음과 같은 **대칭 관계**  

| Producer | Consumer |
|--------|----------|
| `ProducerRecord` | `ConsumerRecord` |
| `KafkaProducer.send()` | `KafkaConsumer.poll()` |
| RecordAccumulator | Fetch 요청 / 응답 처리 |
| Sender Thread | NetworkClient |
| push 방식 | pull 방식 |

---

## To be continue...
다음 문서에서는  
흐름을 **파일 단위로** 살펴 볼 예정  
`send()` 이후 내부 흐름을 코드 기준으로 단계별 추적

→ `03_producer_flow.md`  

