FROM openjdk:8
COPY ["build.sbt", "/tmp/build/"]
COPY ["project/plugins.sbt", "project/build.properties", "/tmp/build/project/"]
RUN cd /tmp/build && \
 sbt compile && \
 sbt test:compile && \
 rm -rf /tmp/build

# copy code
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN sbt compile && sbt test:compile

EXPOSE 9000
CMD ["sbt"]
