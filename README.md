# Kafka Notification Service

Kafka를 활용한 SNS 알림센터 서비스

<!-- prettier-ignore-start -->
![SpringBoot](https://shields.io/badge/springboot-black?logo=springboot&style=for-the-badge%22)
![Docker](https://shields.io/badge/docker-black?logo=docker&style=for-the-badge%22)
![Kafka](https://shields.io/badge/kafka-black?logo=apache-kafka&style=for-the-badge%22)
![Redis](https://shields.io/badge/redis-black?logo=redis&style=for-the-badge%22)
![MongoDB](https://shields.io/badge/mongodb-black?logo=mongodb&style=for-the-badge%22)
<!-- prettier-ignore-end -->

### System Requirements

- [java] 21
- [springboot] 3.5.8
- [docker] 29.1.1
- [kafka] 7.3.10
- [zookeeper] 7.3.10
- [redis]
- [mongodb] 6.0.16

### Kafka 명령

#### 기본 명령

토픽 리스트 보기

```
kafka-topics --list --bootstrap-server localhost:19092
```

토픽 생성

```
kafka-topics --create --topic test --bootstrap-server localhost:19092 --partitions 1 --replication-factor 1
```

이벤트 발행

```
kafka-console-producer --topic test --bootstrap-server localhost:19092
```

이벤트 수신

```
kafka-console-consumer --topic test --bootstrap-server localhost:19092 --from-beginning
```



#### comment, like, follow 토픽 관련

comment 토픽 생성

```
kafka-topics --create --bootstrap-server localhost:19092 --replication-factor 1 --partitions 1 --topic comment
```

like 토픽 생성

```
kafka-topics --create --bootstrap-server localhost:19092 --replication-factor 1 --partitions 1 --topic like
```

follow 토픽 생성

```
kafka-topics --create --bootstrap-server localhost:19092 --replication-factor 1 --partitions 1 --topic follow
```

댓글 이벤트 발행

```
echo '{"type":"ADD","postId":1,"userId":2,"commentId":1,"createdAt":"2025-11-29T18:35:24.01Z"}' | kafka-console-producer --broker-list localhost:19092 --topic comment
```

좋아요 이벤트 발행

```
echo '{"type":"ADD","postId":1,"userId":2,"createdAt":"2025-11-29T18:35:24.01Z"}' | kafka-console-producer --broker-list localhost:19092 --topic like
```

팔로우 이벤트 발행

```
echo '{"type":"FOLLOW","userId":1,"targetUserId":2,"createdAt":"2025-11-29T18:35:24.01Z"}' | kafka-console-producer --broker-list localhost:19092 --topic follow
```

