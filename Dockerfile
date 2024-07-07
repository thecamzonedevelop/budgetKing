FROM ubuntu:latest
LABEL authors="soriya"

ENTRYPOINT ["top", "-b"]

# Build the image
