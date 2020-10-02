#!/bin/bash

SERVICE_NAME="search-engine-api"

echo "=================================================="
echo "'${SERVICE_NAME}' is going to be destroyed!"
echo "=================================================="

CONFIRM_STR="Destroy ${SERVICE_NAME}"

read -r -p "Type '${CONFIRM_STR}' to proceed: " confirm
if [ "${confirm}" = "${CONFIRM_STR}" ]; then

   terraform destroy -auto-approve \
      -var="service_name=${SERVICE_NAME}"

   echo "'${SERVICE_NAME}' has been destroyed. R.I.P."
   exit 0
fi

echo ""
echo "Wrong confirmation"
echo ""
