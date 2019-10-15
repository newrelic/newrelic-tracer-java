> ⚠️ **NOTICE**: This project is currently a work in progress and is not yet intended for production use!

# New Relic OpenTracing Tracer Plugin

An OpenTracing [TracerFactory](https://github.com/opentracing-contrib/java-tracerresolver) that utilizes the OpenTelemetry [TracerShim](https://github.com/open-telemetry/opentelemetry-java/blob/master/opentracing_shim/src/main/java/io/opentelemetry/opentracingshim/TracerShim.java) 
to convert to an OpenTelemetry Tracer. This functions as a [Tracer Plugin](https://github.com/opentracing-contrib/java-specialagent#43-tracer-plugin) 
for the [Java SpecialAgent](https://github.com/opentracing-contrib/java-specialagent) which provides automatic OpenTracing-based instrumentation for 3rd-party Java libraries.
 
Data collected by the Tracer is sent to New Relic by the [New Relic OpenTelemetry Exporter](https://github.com/newrelic/opentelemetry-exporters-newrelic) (to be publicly released soon).

### Provided Instrumentation

See [Instrumentation plugins](https://github.com/opentracing-contrib/java-specialagent#61-instrumentation-plugins) and [rules](https://github.com/opentracing-contrib/java-specialagent#63-instrumented-libraries-by-existing-rules) for lists of all instrumentation provided by the SpecialAgent.

## Getting Started: Requirements
* Java 8 or greater

## Building

`./gradlew shadowJar`

## Testing

`TODO`

## Configuration

A New Relic [Insights API insert key](https://docs.newrelic.com/docs/insights/insights-data-sources/custom-data/introduction-event-api#register) is required to send data to New Relic. The Insights API insert key can be configured directly via a system property or an optional properties config file.

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
