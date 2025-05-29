#!/bin/sh

echo "Waiting for InfluxDB to be healthy..."

# Wait until /health endpoint returns 200
until curl -s -o /dev/null -w "%{http_code}" http://influxdb_service:8086/health | grep -q "200"; do
  echo "InfluxDB not ready yet..."
  sleep 2
done

echo "InfluxDB is ready. Creating additional bucket: selenium..."

# Get org ID (needed for bucket creation)
ORG_ID=$(curl -s -H "Authorization: Token mysecrettoken" http://influxdb_service:8086/api/v2/orgs \
  | grep -oP '"id"\s*:\s*"\K[^"]+' | head -1)

echo "ORG_ID resolved as: $ORG_ID"

# Create the selenium bucket
RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X POST http://influxdb_service:8086/api/v2/buckets \
  -H "Authorization: Token mysecrettoken" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "selenium",
    "orgID": "'"$ORG_ID"'",
    "retentionRules": []
  }')

if [ "$RESPONSE" = "201" ]; then
  echo "✅ Bucket 'selenium' created successfully!"
else
  echo "❌ Failed to create bucket. HTTP response code: $RESPONSE"
  exit 1
fi
