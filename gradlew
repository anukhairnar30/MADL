#!/bin/sh
APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
if command -v gradle >/dev/null 2>&1; then exec gradle -p "$APP_HOME" "$@"; fi
echo "Gradle is not installed locally. Open this project in Android Studio and allow Gradle 8.7 to download."; exit 1
