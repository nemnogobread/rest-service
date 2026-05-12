#!/usr/bin/env bash

set -euo pipefail

ROOT="${DEPLOY_PATH:?DEPLOY_PATH is required}"
cd "$ROOT"

if [ ! -f docker-compose.yml ]; then
  echo "ERROR: docker-compose.yml not found in $ROOT"
  exit 1
fi

if [ -n "${POSTGRES_PASSWORD:-}" ]; then
  touch .env
  if grep -q '^POSTGRES_PASSWORD=' .env 2>/dev/null; then
    grep -v '^POSTGRES_PASSWORD=' .env > .env.tmp && mv .env.tmp .env
  fi
  echo "POSTGRES_PASSWORD=${POSTGRES_PASSWORD}" >> .env
fi

if ! grep -q '^POSTGRES_PASSWORD=' .env 2>/dev/null; then
  echo "ERROR: в ${ROOT}/.env должна быть строка POSTGRES_PASSWORD=... (или передайте секрет из CI)"
  exit 1
fi

docker compose --profile build build
docker compose up -d --remove-orphans

docker compose ps
