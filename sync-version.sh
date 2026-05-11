#!/bin/bash
set -e

ENV_FILE=".env"

if [ ! -f "$ENV_FILE" ]; then
  echo "Error: $ENV_FILE not found"
  exit 1
fi

source "$ENV_FILE"

if [ -z "$APP_VERSION" ]; then
  echo "Error: APP_VERSION not set in $ENV_FILE"
  exit 1
fi

echo "Syncing version: $APP_VERSION"

sed -i.bak "s|<version>[0-9]\+\.[0-9]\+\.[0-9]\+</version>|<version>${APP_VERSION}</version>|" backend/pom.xml
rm -f backend/pom.xml.bak
echo "  backend/pom.xml -> ${APP_VERSION}"

sed -i.bak "s|\"version\": \"[0-9]\+\.[0-9]\+\.[0-9]\+\"|\"version\": \"${APP_VERSION}\"|" frontend/package.json
rm -f frontend/package.json.bak
echo "  frontend/package.json -> ${APP_VERSION}"

echo "Version sync complete: $APP_VERSION"
