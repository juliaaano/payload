# Payload
[![Build Status](https://travis-ci.com/juliaaano/payload.svg)](https://travis-ci.com/juliaaano/payload)
[![Release](https://img.shields.io/github/release/juliaaano/payload.svg)](https://github.com/juliaaano/payload/releases/latest)
[![Maven Central](https://img.shields.io/maven-central/v/com.juliaaano/payload.svg)](https://maven-badges.herokuapp.com/maven-central/com.juliaaano/payload)
[![Javadocs](http://www.javadoc.io/badge/com.juliaaano/payload.svg?color=blue)](http://www.javadoc.io/doc/com.juliaaano/payload)

Easy conversion of HTTP payloads (JSON, XML, etc.) for Java.

Payload acts as a Java-to-\<MediaTypeAsString> facade or abstraction for various libraries such as Gson, Jackson, JAXB
and more. The underlying library used is known as the Provider and it gets evaluated in runtime by classpath lookup.

Conceptually similar to what [SLF4J](https://www.slf4j.org/) does for logging.

## How to use it

```java
import static com.juliaaano.payload.MediaType.*;

class MyObject {
  String value = "abc";
  MyObject() {
    // no-args constructor
  }
}

/* Serialization */

Payload<MyObject> jsonPayload = JSON.payload().newInstance(new MyObject());
String json = jsonPayload.raw();
// ==> json is {"value":"abc"}

Payload<MyObject> xmlPayload = XML.payload().newInstance(new MyObject());
String xml = xmlPayload.raw();
// ==> xml is <MyObject><value>abc</value></MyObject>

/* Deserialization */

Payload<MyObject> payload_1 = JSON.payload().newInstance(json, MyObject.class);
MyObject obj_1 = payload_1.get();

Payload<MyObject>  payload_2 = XML.payload().newInstance(xml, MyObject.class);
MyObject obj_2 = payload_2.get();
```

## Build and install

Payload does not bring any transitive dependencies, however it is required to deliberately have at least one of the
pre defined providers in the classpath. You can also [implement your own](#custom-provider). 

#### Maven

```xml
<dependency>
    <groupId>com.juliaaano</groupId>
    <artifactId>payload</artifactId>
    <version>1.1.2</version>
</dependency>
```

In case you just need JSON conversion, add [Google Gson](https://github.com/google/gson) or any of the
other JSON pre defined [providers](#providers).

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>${gson.version}</version>
    <scope>runtime</scope>
</dependency>
```

### Providers

This library does not do any (de)serialization itself. It depends on finding in the classpath one of the
following:

###### JSON

* [Gson](https://github.com/google/gson)
* [Jackson Databind](https://github.com/FasterXML/jackson-databind)
* [Json Binding](http://json-b.net/)

###### XML

* [Jackson XML](https://github.com/FasterXML/jackson-dataformat-xml)
* [JAXB](https://en.wikipedia.org/wiki/Java_Architecture_for_XML_Binding)

In case one or more are to be found, the priority is given by the order listed above.

###### Priority

It is possible to determine the priority of which providers are loaded.
For example, if Gson and Jackson are present in the classpath, Payload can be configured to use Jackson instead of Gson
by default.

All required is to include a file named payload.properties in the root of the classpath:

```
json.provider=JacksonJson
xml.provider=JacksonXml
``` 

###### Custom Provider

1. Implement one of the available \<MediaType>ProviderFactory.java.
2. Declare the full name of your implementation class in a file as per
[java.util.ServiceLoader](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html) specification
*/META-INF/services/com.juliaaano.payload.json.\<MediaType>ProviderFactory*

```java
package com.your.project;
public class MyProvider implements JsonProviderFactory {
  @Override
  public Optional<Provider> newInstance() {
    return Optional.of(new Provider() {
      @Override
      public String serialize(Object object) {
        return "your way to serialize";
      }
      @Override
      public <T> T deserialize(String raw, Class<T> type) {
        return null; // your way to deserialize
      }
    });
  }
}
```

```
>/META-INF/services/com.juliaaano.payload.json.JsonProviderFactory
com.your.project.MyProvider
```   

## Further Details

Requires Java 8.
