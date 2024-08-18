#!/bin/bash
docker exec cons-be-course bash -c 'git pull && exit' && docker restart cons-be-course
