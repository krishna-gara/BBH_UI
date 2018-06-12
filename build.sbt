name := """play-angular2-typescript"""
version := "0.2.0-beta.7"
lazy val root = (project in file(".")).enablePlugins(PlayScala)
scalaVersion := "2.11.8"
incOptions := incOptions.value.withNameHashing(true)
updateOptions := updateOptions.value.withCachedResolution(cachedResoluton = true)
//we use nodejs to make our typescript build as fast as possible
JsEngineKeys.engineType := JsEngineKeys.EngineType.Node
resolvers += Resolver.jcenterRepo
libraryDependencies ++= {
  val ngVersion="2.2.0"
  Seq(
    cache,
    javaWs,
    //angular2 dependencies
    "org.webjars.npm" % "angular__common" % ngVersion,
    "org.webjars.npm" % "angular__compiler" % ngVersion,
    "org.webjars.npm" % "angular__core" % ngVersion,
    "org.webjars.npm" % "angular__http" % ngVersion,
    "org.webjars.npm" % "angular__forms" % ngVersion,
    "org.webjars.npm" % "angular__router" % "3.2.0",
    "org.webjars.npm" % "angular__platform-browser-dynamic" % ngVersion,
    "org.webjars.npm" % "angular__platform-browser" % ngVersion,
    "org.webjars.npm" % "systemjs" % "0.19.40",
    "org.webjars.npm" % "rxjs" % "5.0.0-beta.12",
    "org.webjars.npm" % "reflect-metadata" % "0.1.8",
    "org.webjars.npm" % "zone.js" % "0.6.26",
    "org.webjars.npm" % "core-js" % "2.4.1",
    "org.webjars.npm" % "symbol-observable" % "1.0.1",
    "org.webjars.npm" % "ng2-bootstrap" % "1.6.3",
    "org.webjars.npm" % "typescript" % "2.2.1",
    "org.apache.kafka" % "kafka_2.11" % "0.10.0.1",
    "org.webjars.npm" % "ng2-pagination" % "1.0.0",
    //   "org.webjars.npm" % "codelyzer" % "2.0.0-beta.1",
    "org.webjars.npm" % "types__jasmine" % "2.2.26-alpha" % "test",
    "com.prowidesoftware" % "pw-swift-core" % "SRU2016-7.8.5",
    "com.typesafe.akka" %% "akka-stream-kafka" % "0.16"

  //test
    //  "org.webjars.npm" % "jasmine-core" % "2.4.1"
  )
}
dependencyOverrides += "org.webjars.npm" % "minimatch" % "3.0.0"
typingsFile := Some(baseDirectory.value / "typings" / "index.d.ts")
// use the webjars npm directory (target/web/node_modules ) for resolution of module imports of angular2/core etc
resolveFromWebjarsNodeModulesDir := true
routesGenerator := InjectedRoutesGenerator
