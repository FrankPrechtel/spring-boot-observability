```
cd container
# edit prometheus.yml beforehand
docker run -d --name=prometheus -p 9090:9090 -v $PWD/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
https://grafana.com/grafana/dashboards/11378-justai-system-monitor/

docker run -d --name=grafana -p 3000:3000 grafana/grafana


# docker run -d --name=loki -p 3100:3100 grafana/loki

# https://grafana.com/docs/loki/latest/installation/docker/
wget https://raw.githubusercontent.com/grafana/loki/v2.6.1/cmd/loki/loki-local-config.yaml -O loki-config.yaml
docker run --name loki -v $PWD:/mnt/config -p 3100:3100 grafana/loki:2.6.1 --config.file=/mnt/config/loki-config.yaml
wget https://raw.githubusercontent.com/grafana/loki/v2.6.1/clients/cmd/promtail/promtail-docker-config.yaml -O promtail-config.yaml
docker run -v $PWD:/mnt/config -v /var/log:/var/log --link loki grafana/promtail:2.6.1 --config.file=/mnt/config/promtail-config.yaml
```
