# New Relic OpenTracing Tracer Plugin

An OpenTracing [TracerFactory](https://github.com/opentracing-contrib/java-tracerresolver) that utilizes the OpenTelemetry [TraceShim](https://github.com/open-telemetry/opentelemetry-java/blob/master/opentracing_shim/src/main/java/io/opentelemetry/opentracingshim/TraceShim.java) 
to convert to an OpenTelemetry Tracer. This functions as a [Tracer Plugin](https://github.com/opentracing-contrib/java-specialagent#43-tracer-plugin) 
for the [Java SpecialAgent](https://github.com/opentracing-contrib/java-specialagent) which provides automatic OpenTracing-based instrumentation for 3rd-party Java libraries.
 
Data collected by the Tracer is sent to New Relic by the [New Relic OpenTelemetry Exporter](https://github.com/newrelic/opentelemetry-exporter-java).

> ⚠️ **NOTICE**: The Tracer Plugin utilizes the [Alpha v0.2 release](https://github.com/open-telemetry/opentelemetry-specification/blob/master/milestones.md#alpha-v02) of OpenTelemetry APIs. OpenTelemetry APIs are subject to change while approaching a stable release and are not yet recommended for use in production environments.

#### Provided Instrumentation

See [Instrumentation plugins](https://github.com/opentracing-contrib/java-specialagent#61-instrumentation-plugins) and [rules](https://github.com/opentracing-contrib/java-specialagent#63-instrumented-libraries-by-existing-rules) for lists of all instrumentation provided by the SpecialAgent.

## Getting Started: Requirements

* Java 8 or greater

## Building

`./gradlew shadowJar`

## Usage

The Tracer Plugin has not yet been publish but can be built and added to a project as a jar file dependency.

Gradle:  

```
dependencies {
    implementation files('libs/newrelic-tracer-java-0.2.0-SNAPSHOT-all.jar')
}
```

To use with manual OpenTracing instrumentation simply create an instance of the `NewRelicTracerFactory` and get a `Tracer` implementation from it via `getTracer()`:

```java
NewRelicTracerFactory newRelicTracerFactory = new NewRelicTracerFactory();
Tracer newrelicTracer = newRelicTracerFactory.getTracer();
```

To use the Tracer Plugin with the [Java Special Agent for OpenTracing](https://github.com/opentracing-contrib/java-specialagent#43-tracer-plugin) simply add system properties such as the following to the run configuration of your service:

```java
-javaagent:path/to/opentracing-specialagent-1.5.7. -Dsa.tracer=./libs/newrelic-tracer-java-0.2.0-SNAPSHOT-all.jar -Dsa.log.level=INFO -Dnr.apiKey=XXX -Dnr.serviceName="MyService"
```

## Testing

`LGTM!`

## Configuration

A New Relic [API insert key](https://docs.newrelic.com/docs/insights/insights-data-sources/custom-data/introduction-event-api#register) is required to send data to New Relic. The API insert key can be configured directly via a system property or with an optional config file.

### Config Mechanisms

(Option 1) Individual system properties

`-Dnr.apiKey=123xyz -Dnr.serviceName=foo`

(Option 2) Config file `nr_tracer.properties` specified by the Java SpecialAgent system property

`-Dsa.config=nr_tracer.properties`

with the contents:

```
nr.apiKey=123xyz
nr.serviceName=foo
```

Additionally, while this is subject to change, the `OC_RESOURCE_LABELS` OpenConsensus environment variable currently will decorate the generated OpenTelemetry spans with attributes that you specify:

`export OC_RESOURCE_LABELS="service.name=foo,env=prod,host=bar"`

### Config Properties

List of all configurable Tracer properties

| Config Key       | Value                                                     | Required |
| :--------------  | :-------------------------------------------------------- | :------  |
| `nr.apiKey`      | New Relic Insights API Insert key                         | Yes      |
| `nr.serviceName` | Display name of service, sets `service.name` on the spans | No       |

## Contributing

Full details are available in our [CONTRIBUTING.md](CONTRIBUTING.md) file.
We'd love to get your contributions to improve the New Relic OpenTracing Tracer Plugin! Keep in mind when you submit your pull request, you'll need to sign the CLA via the click-through using CLA-Assistant. You only have to sign the CLA one time per project.
To execute our corporate CLA, which is required if your contribution is on behalf of a company, or if you have any questions, please drop us an email at open-source@newrelic.com. 

### Licensing

The New Relic OpenTracing Tracer Plugin is licensed under the Apache 2.0 License.

The New Relic OpenTracing Tracer Plugin may use source code from third party libraries.
Full details on which libraries are used and the terms under which they are licensed can be found in [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).
