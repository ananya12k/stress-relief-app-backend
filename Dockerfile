FROM ubuntu:latest
LABEL authors="anany"

ENTRYPOINT ["top", "-b"]