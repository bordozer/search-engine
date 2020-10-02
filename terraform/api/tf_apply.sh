#!/bin/bash

SERVICE_NAME="search-engine-api"

terraform init -backend-config="key=${SERVICE_NAME}.tfstate"

terraform apply -auto-approve \
  -var="service_name=${SERVICE_NAME}"
