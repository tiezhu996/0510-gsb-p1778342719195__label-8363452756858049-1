#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
VERSION_FILE="$SCRIPT_DIR/.version"
ENV_FILE="$SCRIPT_DIR/.env"
POM_FILE="$SCRIPT_DIR/backend/pom.xml"
PACKAGE_FILE="$SCRIPT_DIR/frontend/package.json"

if [ ! -f "$VERSION_FILE" ]; then
    echo "[ERROR] .version file not found"
    exit 1
fi

VERSION=$(cat "$VERSION_FILE" | tr -d '[:space:]')

if [ -z "$VERSION" ]; then
    echo "[ERROR] Version is empty in .version file"
    exit 1
fi

echo "[INFO] Syncing version: $VERSION"

echo "[1/3] Updating .env file..."
if [ -f "$ENV_FILE" ]; then
    if grep -q "^VERSION=" "$ENV_FILE"; then
        sed -i.bak "s/^VERSION=.*/VERSION=$VERSION/" "$ENV_FILE"
        rm -f "${ENV_FILE}.bak"
    else
        echo "VERSION=$VERSION" >> "$ENV_FILE"
    fi
else
    echo "VERSION=$VERSION" > "$ENV_FILE"
fi
echo "      [OK] .env updated (for image tags and runtime version)"

echo "[2/3] Updating backend/pom.xml (project version only)..."
if [ -f "$POM_FILE" ]; then
    awk -v new_version="$VERSION" '
        /<artifactId>yanbian-backend<\/artifactId>/ {
            print
            getline
            sub(/<version>[0-9.]*<\/version>/, "<version>" new_version "</version>")
            print
            next
        }
        { print }
    ' "$POM_FILE" > "${POM_FILE}.tmp"
    mv "${POM_FILE}.tmp" "$POM_FILE"
    echo "      [OK] pom.xml project version updated"
else
    echo "      [SKIP] pom.xml not found, skipped"
fi

echo "[3/3] Updating frontend/package.json..."
if [ -f "$PACKAGE_FILE" ]; then
    if command -v jq >/dev/null 2>&1; then
        jq --arg v "$VERSION" '.version = $v' "$PACKAGE_FILE" > "${PACKAGE_FILE}.tmp"
        mv "${PACKAGE_FILE}.tmp" "$PACKAGE_FILE"
        echo "      [OK] package.json updated (using jq)"
    else
        sed -i.bak 's/"version": *"[0-9.]*"/"version": "'"$VERSION"'"/' "$PACKAGE_FILE"
        rm -f "${PACKAGE_FILE}.bak"
        echo "      [OK] package.json updated (using sed)"
    fi
else
    echo "      [SKIP] package.json not found, skipped"
fi

echo ""
echo "[SUCCESS] Version $VERSION synced successfully!"
echo ""
echo "Next steps:"
echo "   - Run: docker compose build"
echo "   - Or: bash scripts/build-and-push.sh (for production)"
