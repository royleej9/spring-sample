#! /bin/bash

readonly CONTAINER_NAME="mysql"
readonly DB_CONF_DIR="/home/asd/doc/mariadb/conf"
readonly DB_DATA_DIR="/home/asd/doc/mariadb/store"

readonly RUN_COMMAND=$1
#-------------- functions ---------------------------------------------

# https://stackoverflow.com/questions/3685970/check-if-a-bash-array-contains-a-value
containsElement () {
  local e match="$1"
  shift
  for e; do [[ "$e" == "$match" ]] && return 0; done
  return 1
}

check_run_command() {
    local commands=("build" "up" "down" "remove")
    if ! containsElement "$RUN_COMMAND" "${commands[@]}" ; then
        echo "Please select run command(build/up/down/remove): ex) ./docker-compose-run.sh up"
        exit 1
    fi
}

create_dir() {    
    mkdir -p $DB_CONF_DIR
    mkdir -p $DB_DATA_DIR
    echo "----------------- Create directory -----------------"
    echo "DB CONF volume: $DB_CONF_DIR"
    echo "DB DATA volume: $DB_DATA_DIR"
}

create_env() {
    echo "----------------- Create environment file: .env -----------------"
    cat >.env <<EOF
GID=$(id -g)
UID=$(id -u)
DB_CONF_DIR=$DB_CONF_DIR
DB_DATA_DIR=$DB_DATA_DIR
EOF

    # print .env file
    cat .env
}

execute_docker_compose() {
    echo "----------------- Execute docker-compose -----------------"
    if [ "$RUN_COMMAND" == "up" ]; then
        docker-compose -f docker-compose.yml up -d
    elif [ "$RUN_COMMAND" == "remove" ]; then
        docker-compose -f docker-compose.yml down --rmi all
        rm .env
    else 
        docker-compose -f docker-compose.yml "$RUN_COMMAND"
    fi

    echo "-----------------------------------------------"
    docker ps -f "name=${CONTAINER_NAME}"
}

execute_command() {    
    # env $(cat docker-compose.env) docker-compose -f docker-compose.yml -f docker-compose-build.yml up -d
    if [ "$RUN_COMMAND" == "build" ]; then
        create_dir
        create_env
        cp custom.cnf $DB_CONF_DIR
    elif [ "$RUN_COMMAND" == "up" ]; then
        if [ ! -f ".env" ]; then
            echo "Please execute the build command before executing the up command"
            exit 1
        fi
    fi

    execute_docker_compose
}

#----------------------- start -------------------------------------
check_run_command
execute_command