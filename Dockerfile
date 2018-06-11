FROM java:latest
RUN /sbin/apk add --no-cache bash
WORKDIR /opt/docker
ADD --chown=daemon:daemon opt /opt
USER daemon
ENTRYPOINT []
CMD []
