FROM java:latest
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
ENTRYPOINT ["bin/play-angular2-typescript"]
CMD []
