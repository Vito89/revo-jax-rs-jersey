JAX-RS/Jersey application for Revolut
========================================

A project of a Java web application is based on:

* [JAX-RS API](https://java.net/projects/jax-rs-spec) — Java API for RESTful services
* [Jersey](https://jersey.java.net/) — JAX-RS implementation
* [Gradle](http://www.gradle.org/) — Build Automation tool

## Building and running

### Building WAR file

```
./gradlew --daemon war
```

### Running on Jetty for development

```
./gradlew --daemon jettyRun
```

### Running tests

```
./gradlew --daemon test
```
