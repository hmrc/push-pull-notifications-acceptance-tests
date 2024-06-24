#!/bin/bash
set -e
set -o pipefail

function usage() {
  echo "Usage: run_tests [-h] [-r] [-t TAGS] [-e ENVIRONMENTS]"
  echo
  echo "Run acceptance tests"
  echo
  echo "optional arguments:"
  echo "  -e ENVIRONMENTS          Comma separated list with no spaces. Specifies Serenity environments to be used. These are defined in the Serenity.conf file. For example 'qa,chrome,grid,zap'"
  echo "  -h                       Show this help"
  echo "  -r                       Retry failed tests. Default 'false'"
  echo "  -t TAGS                  Tags to run. For example '@tag1' or '@tag1 and @tag2'"
}

# Defaults
environments=qa,chrome
retry=false

# Parse CLI arguments
while getopts 'he:rt:' OPTION; do
  case "$OPTION" in
  e)
    environments=$OPTARG
    ;;
  h)
    usage
    exit 1
    ;;
  r)
    retry="true"
    ;;
  t)
    tag=$OPTARG
    ;;
  ?)
    usage
    exit 1
    ;;
  esac
done

gradlew_return_code=0

echo "== Using environments: '${environments}' =="
echo "== Using tag: '${tag}' =="
echo "== Using retry: '${retry}' =="

if [[ "$retry" == "true" && -f target/failed_scenarios.txt ]]; then
  rm target/failed_scenarios.txt
fi

./gradlew clean test -Denvironment="${environments}" -Dcucumber.filter.tags="${tag}" || gradlew_return_code=$?

if [[ "$retry" == "true" && "$gradlew_return_code" != "0" ]]; then
  echo "== Retrying failed tests =="

  echo "Contents of 'target/failed_scenarios.txt': $(cat target/failed_scenarios.txt)"

  ./gradlew test -Denvironment="${environments}" -Dcucumber.features="@target/failed_scenarios.txt" && gradlew_return_code=$?
fi

echo "Exiting with error code: '$gradlew_return_code'"

exit $gradlew_return_code
