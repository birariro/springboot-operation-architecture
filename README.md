[![version](https://img.shields.io/badge/springboot-2.7.16--SNAPSHOT-00bfb3?style=flat&logo=springboot)]()
[![version](https://img.shields.io/badge/Java-11-00bfb3?style=flat&logo=sdk)]()

## Description
주문 프로세스에서 발생할수있는 예외 상황을 
로그로 추적이 가능하도록 한다.

### 케이스
- 없는 유저 정보로 주문을 생성하는 경우
- 존재하지 않는 상품을 주문
- 상품의 재고 보다 더 많은 양의 주문


## Environment

| port  | name         |
|-------|--------------|
| 11801 | auth server  |
| 11802 | order server |
| 11810 | db           |
| 11820 | prometheus   |
| 3000 | grafana      |
| 3100 | loki         |

```
auth server <- promtail -> loki <- grafana
order server <- promtail 
```

## Usage
### the beginning of service for docker compose

```shell
docker compose -f docker-compose.db.yml up --build -d
docker compose -f docker-compose.auth.yml up --build -d
docker compose -f docker-compose.order.yml up --build -d
docker compose -f docker-compose.operation.yml up --build -d
```


### grafana setting
prometheus http url : http://host.docker.internal:11820 </br>
loki http url : http://host.docker.internal:3100 </br>
grafana dashboard : https://grafana.com/grafana/dashboards/17175-spring-boot-observability/


### success process
order -> user check -> product check -> order check -> success

```
curl -d '{"memberId": 1, "productId" : 3, "count": 2}' \
-H "Content-Type: application/json" \
-X POST http://localhost:11802/order
``` 

### fail process

not found api path
```
curl -X GET http://localhost:11802/delete
```

not exist product order
```
curl -d '{"memberId": 1, "productId" : 65535, "count": 2}' \
-H "Content-Type: application/json" \
-X POST http://localhost:11802/order
```

overflow product count order
```
curl -d '{"memberId": 1, "productId" : 1, "count": 99999999}' \
-H "Content-Type: application/json" \
-X POST http://localhost:11802/order
```


## document

[loki + promtail 설치 가이드](https://grafana.com/docs/loki/latest/setup/install/docker/)

### promtail
Promtail 는 로그파일이 존재하는 서버에 설치되어 로그를 실시간 조회를 하여 테일링 하고 
Loki 에게 전달하는 역활을 수행한다.

[promtail 문서](https://grafana.com/docs/loki/latest/send-data/promtail/)

### loki
Promtail 로 부터 로그를 수신 하여 
로그 데이터를 효율적으로 보관 하도록 최적화된 데이터 저장소이다. </br>

[loki 문서](https://grafana.com/docs/loki/v2.8.x/fundamentals/overview/)


