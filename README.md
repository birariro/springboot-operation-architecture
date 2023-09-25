[![version](https://img.shields.io/badge/springboot-2.7.16--SNAPSHOT-00bfb3?style=flat&logo=springboot)]()



```
auth server <- promtail -> loki <- grafana
order server <- promtail 
```

## port

| port  | name         |
|-------|--------------|
| 11801 | auth server  |
| 11802 | order server |
| 11810 | db           |
| 11820 | prometheus   |
| 3000 | grafana      |
| 3100 | loki         |



### the beginning of service for docker compose

```shell
docker compose -f docker-compose.db.yml up --build -d
docker compose -f docker-compose.auth.yml up --build -d
docker compose -f docker-compose.order.yml up --build -d
docker compose -f docker-compose.operation.yml up --build -d
```


### grafana 설정
prometheus http url : http://host.docker.internal:11820 </br>
loki http url : http://host.docker.internal:3100 </br>
grafana dashboard : https://grafana.com/grafana/dashboards/17175-spring-boot-observability/

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


## process
1. order -> user check -> product check -> order check -> success