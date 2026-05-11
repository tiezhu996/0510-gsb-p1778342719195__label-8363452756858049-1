#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
VERSION_FILE="$SCRIPT_DIR/.version"

if [ ! -f "$VERSION_FILE" ]; then
    echo "[ERROR] .version file not found"
    exit 1
fi

CURRENT_VERSION=$(cat "$VERSION_FILE" | tr -d '[:space:]')
echo "[INFO] Current version: $CURRENT_VERSION"

if [ -z "$1" ]; then
    echo ""
    echo "Usage: $0 <new-version>"
    echo "  Example: $0 1.0.1"
    echo ""
    echo "Current version: $CURRENT_VERSION"
    exit 1
fi

NEW_VERSION="$1"

if ! [[ "$NEW_VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
    echo "[ERROR] Invalid version format, use x.y.z format"
    exit 1
fi

echo ""
echo "[INFO] Bumping version: $CURRENT_VERSION -> $NEW_VERSION"
echo ""

echo "$NEW_VERSION" > "$VERSION_FILE"

echo "[INFO] .version file updated"
echo ""

bash "$SCRIPT_DIR/sync-version.sh"

echo ""
echo "[SUCCESS] Version bump completed!"
echo ""
echo "Next steps:"
echo "   1. Review changes: git diff"
echo "   2. Commit changes: git add . && git commit -m \"chore: bump version to $NEW_VERSION\""
echo "   3. Create tag: git tag v$NEW_VERSION"
echo "   4. Push changes: git push && git push --tags"
