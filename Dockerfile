FROM java:latest
CMD ["sbt","dist"]
CMD ["sbt","docker:publishLocal"]
CMD "sbt","run"]
CMD []
