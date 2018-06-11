FROM java:latest
CMD ["sbt","dist"]
CMD ["sbt","publishLocal"]
ENTRYPOINT bash
EXPOSE 9000
CMD []
