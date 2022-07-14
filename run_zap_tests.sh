#!/usr/bin/env bash

DEFAULT_BROWSER=chrome
BROWSER_TYPE=$1
ENV=$2

if [ -z "$BROWSER_TYPE" ]; then
    echo "BROWSER_TYPE value not set, defaulting to $DEFAULT_BROWSER..."
    echo ""
fi

# NOTE: It is not required to proxy every journey test via ZAP. The intention of proxying a test through ZAP is to expose all the
# relevant pages of an application to ZAP. So tagging a subset of the journey tests or creating a
# single ZAP focused journey test is sufficient.

sbt -Dbrowser="${BROWSER_TYPE:=$DEFAULT_BROWSER}" -Denvironment="${ENV:=local}" -Dzap.proxy=true "testOnly uk.gov.hmrc.test.ui.cucumber.runner.ZapRunner"
