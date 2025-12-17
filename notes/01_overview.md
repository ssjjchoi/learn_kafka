# 01. Kafka Overview

## Kafka란 무엇인가
Apache Kafka는 대용량 이벤트 스트림을  
안정적 저장,전달하기 위한 분산 스트리밍 플랫폼  

로그 수집, 데이터 파이프라인, 실시간 스트리밍 처리에서  
중앙 메시지 허브 역할로 자주 사용  

---

## Kafka 왜 써요?
Kafka는 단순한 메시지 큐를 넘어서  
다음과 같은 문제를 해결하기 위해 설계  

- 높은 처리량이 필요한 데이터 흐름  
- Producer와 Consumer 간의 느슨한 결합  
- 데이터 유실 없는 저장과 재처리  
- 실시간 처리와 배치 처리를 함께 만족해야 하는 상황  

---

## 기본 구성 요소 (Actor)
주요 컴포넌트들로 구성  

- **Producer**: 이벤트를 Kafka로 전송하는 클라이언트  
- **Broker**: 이벤트를 저장하고 전달하는 Kafka 서버  
- **Consumer**: 이벤트를 읽어 처리하는 클라이언트  
- **Consumer Group**: 여러 Consumer가 하나의 스트림을 병렬로 소비하기 위한 단위  

---

## Kafka 핵심 개념 (data & mechanism)
- **Topic**: 이벤트가 기록되는 논리적 스트림 단위  
- **Partition**: Topic을 나눈 물리적 단위 (병렬 처리의 기준)  
- **Offset**: Partition 내에서 이벤트의 위치를 나타내는 값  
- **Record**: Kafka에 저장되는 하나의 이벤트 데이터  


---

## 리포 공부 방향
목표 is   
- **공식 Kafka 소스 코드 기반 내부 동작 이해**  

Producer `send()` 흐름과  
Consumer `poll()` 메커니즘을
코드 레벨에서 추적 집중  

---

## To be continue...
다음 문서에서는  
Kafka `clients` 모듈 구조부터 볼 예정  

→ `02_clients_structure.md`