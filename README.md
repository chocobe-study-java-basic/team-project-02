# NBE12-14-2-JAVACHIP

## Docker Compose 실행

프로젝트 루트에서 Makefile 명령으로 로컬 개발 환경을 관리합니다.

### 전체 서비스 실행

```bash
make dev-up
```

### 컨테이너 종료 및 삭제

named volume의 데이터는 유지합니다.

```bash
make dev-down
```

### 컨테이너와 DB 데이터 전체 초기화

named volume을 포함한 로컬 DB 데이터가 모두 삭제됩니다.

```bash
make dev-down-reset
```

### MySQL 로그 확인

실시간 로그 확인을 종료하려면 `Ctrl+C`를 누릅니다.

```bash
make db-logs
```
