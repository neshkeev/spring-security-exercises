#!/usr/bin/env bash

client_keystore_pass=${1:-changeit}

client_keystore_location=../src/main/resources/keystore/client
client_keystore_name=keystore.p12

mkdir -p "$client_keystore_location" && rm -rf "$client_keystore_location"/*

for alias in admin user1 user2; do
  keystore_path="$client_keystore_location"/"$alias"_"$client_keystore_name"

  keytool -genkeypair -alias "$alias" -keyalg RSA -keysize 4096 \
    -validity 3650 -dname "CN=$alias" -keypass "$client_keystore_pass" -keystore "$keystore_path" \
    -storeType PKCS12 -storepass "$client_keystore_pass"

  keytool -list -storepass "$client_keystore_pass" -keypass "$client_keystore_pass" -keystore "$keystore_path"

  pem_cert_path="$client_keystore_location"/"$alias".pem
  keytool -exportcert -alias "$alias" -keystore "$keystore_path" -storepass changeit -rfc -file "$pem_cert_path"

  private_key_path="$client_keystore_location"/"$alias".key
  openssl pkcs12 -in "$keystore_path" -passin pass:"$client_keystore_pass" -nodes -nocerts -out "$private_key_path"
done