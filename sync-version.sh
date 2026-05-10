#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
ENV_FILE="$SCRIPT_DIR/.env"

if [ ! -f "$ENV_FILE" ]; then
  echo "❌ 错误: 未找到 .env 文件"
  exit 1
fi

APP_VERSION=$(grep "^APP_VERSION=" "$ENV_FILE" | cut -d'=' -f2)

if [ -z "$APP_VERSION" ]; then
  echo "❌ 错误: .env 文件中未找到 APP_VERSION"
  exit 1
fi

echo "📦 目标版本号: $APP_VERSION"
echo ""

cd "$SCRIPT_DIR/backend"
CURRENT_BACKEND_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout 2>/dev/null || echo "unknown")
echo "🔍 当前 backend 版本: $CURRENT_BACKEND_VERSION"

if [ "$CURRENT_BACKEND_VERSION" = "$APP_VERSION" ]; then
  echo "✅ backend 版本已是 $APP_VERSION，跳过更新"
else
  echo "🔄 更新 backend/pom.xml 到版本 $APP_VERSION..."
  mvn versions:set -DnewVersion="$APP_VERSION" -DgenerateBackupPoms=false > /dev/null 2>&1
  echo "✅ backend/pom.xml 已更新"
fi

echo ""
cd "$SCRIPT_DIR/frontend"
if [ -f "package.json" ]; then
  CURRENT_FRONTEND_VERSION=$(node -p "require('./package.json').version" 2>/dev/null || echo "unknown")
  echo "🔍 当前 frontend 版本: $CURRENT_FRONTEND_VERSION"

  if [ "$CURRENT_FRONTEND_VERSION" = "$APP_VERSION" ]; then
    echo "✅ frontend 版本已是 $APP_VERSION，跳过更新"
  else
    echo "🔄 更新 frontend/package.json 到版本 $APP_VERSION..."
    npm version "$APP_VERSION" --no-git-tag-version > /dev/null 2>&1
    echo "✅ frontend/package.json 已更新"
  fi
else
  echo "⚠️  未找到 frontend/package.json，跳过 frontend 版本更新"
fi

echo ""
echo "🎉 版本号同步完成！所有文件已处理"
exit 0
