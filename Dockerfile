FROM java:latest
ADD BBH_UI/target/universal /tmp/
RUN unzip /tmp/play-angular2-typescript-0.2.0-beta.7.zip
ENTRYPOINT ["play-angular2-typescript-0.2.0-beta.7/bin/play-angular2-typescript"]
CMD []