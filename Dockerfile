FROM java:latest
CMD ["sbt","dist"]
CMD ["sbt","publishLocal"]
CMD []
