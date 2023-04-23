#!/usr/bin/env bash

truststore_location=../src/main/resources/keystore/server/truststore.p12
truststore_pass=changeit

mkdir -p $(dirname $truststore_location) && rm -rf "$truststore_location"

for cert in $(ls src/main/resources/keystore/client/*.pem); do
  alias=$(basename $cert)

  keytool -import -alias "$alias" -file "$cert" -storetype PKCS12 -keystore "$truststore_location" -storepass "$truststore_pass" -noprompt
done

keytool -list -keystore "$truststore_location" -storepass "$truststore_pass"