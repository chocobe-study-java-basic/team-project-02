.PHONY: \
	dev-up \
	dev-down \
	dev-down-reset \
	db-logs

# Docker Compose 서비스 실행
dev-up:
	docker compose up -d

# Docker Compose 컨테이너와 네트워크 종료 및 삭제 (볼륨 유지)
dev-down:
	docker compose down

# Docker Compose 컨테이너, 네트워크, named volume 전체 삭제
dev-down-reset:
	docker compose down -v

# MySQL 컨테이너 로그 실시간 확인 (Ctrl+C로 종료)
db-logs:
	docker compose logs -f mysql
