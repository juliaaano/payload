# Payload [![Build Status](https://travis-ci.org/juliaaano/payload.svg)](https://travis-ci.org/juliaaano/payload) [![Release](https://img.shields.io/github/release/juliaaano/payload.svg)](https://github.com/juliaaano/payload/releases/latest)

Easy conversion of HTTP payloads (JSON, XML, etc.) for Java.

Payload acts as a Java-to-\<MediaTypeAsString> facade or abstraction for various libraries such as Gson, Jackson, JAXB
and more. The underlying library used is known as the Provider and it gets evaluated in runtime by classpath lookup.

Conceptually similar to what [SLF4J](https://www.slf4j.org/){:target="_blank"} does for logging. 

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
// ==> xml is <Dummy><value>abc</value></Dummy>

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
    <version>${TO-BE-RELEASED}</version>
</dependency>
```

In case you just need JSON conversion, add [Google Gson](https://github.com/google/gson){:target="_blank"} or any of the
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

* Gson
* Jackson
* JsonB

###### XML

* Jackson
* JAXB

In case one or more are to be found, the priority is given by the order listed above.

###### Custom Provider

1. Implement one of the available \<MediaType>ProviderFactory.java.
2. Declare the full name of your implementation class in a file as per
[java.util.ServiceLoader](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html){:target="_blank"} specification
*/META-INF/services/com.juliaaano.payload.json.\<MediaType>ProviderFactor*

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

/META-INF/services/com.juliaaano.payload.json.JsonProviderFactory
```
com.your.project.MyProvider
```   

## Further Details

Requires Java 8.

// TODO
