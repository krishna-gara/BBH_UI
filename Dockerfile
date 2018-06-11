FROM java:latest
RUN ["chown", "-R", "daemon:daemon", "."]
CMD ["sbt","dist"]
CMD ["sbt","publishLocal"]
CMD []
