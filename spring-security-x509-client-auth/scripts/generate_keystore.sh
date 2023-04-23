#!/usr/bin/env bash

keystore_alias=spring-security-x509-demo

keystore_pass=${1:-changeit}

keystore_location=../src/main/resources/keystore/server/keystore.p12

mkdir -p $(basename "$keystore_location") && rm -f "$keystore_location"

keytool -genkeypair -alias "$keystore_alias" -keyalg RSA -keysize 4096 \
  -validity 3650 -dname "CN=localhost" -ext 'SAN=ip:127.0.0.1' -keypass "$keystore_pass" -keystore "$keystore_location" \
  -storeType PKCS12 -storepass "$keystore_pass"

keytool -list -storepass "$keystore_pass" -keypass "$keystore_pass" -keystore "$keystore_location"