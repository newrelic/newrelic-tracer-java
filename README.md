[![Community Project header](https://github.com/newrelic/open-source-office/raw/master/examples/categories/images/Community_Project.png)](https://github.com/newrelic/open-source-office/blob/master/examples/categories/index.md#community-project)

# New Relic OpenTracing Tracer Plugin

An OpenTracing [TracerFactory](https://github.com/opentracing-contrib/java-tracerresolver) that utilizes the OpenTelemetry [TraceShim](https://github.com/open-telemetry/opentelemetry-java/blob/master/opentracing_shim/src/main/java/io/opentelemetry/opentracingshim/TraceShim.java) 
to convert to an OpenTelemetry Tracer. This functions as a [Tracer Plugin](https://github.com/opentracing-contrib/java-specialagent#43-tracer-plugin) 
for the [Java SpecialAgent](https://github.com/opentracing-contrib/java-specialagent) which provides automatic OpenTracing-based instrumentation for 3rd-party Java libraries.
 
Data collected by the Tracer is sent to New Relic by the [New Relic OpenTelemetry Exporter](https://github.com/newrelic/opentelemetry-exporter-java).

> ⚠️ **NOTICE**: The Tracer Plugin utilizes the [Alpha v0.2 release](https://github.com/open-telemetry/opentelemetry-specification/blob/master/milestones.md#alpha-v02) of OpenTelemetry APIs. OpenTelemetry APIs are subject to change while approaching a stable release and are not yet recommended for use in production environments.

#### Provided Instrumentation

See [Instrumentation plugins](https://github.com/opentracing-contrib/java-specialagent#61-instrumentation-plugins) and [rules](https://github.com/opentracing-contrib/java-specialagent#63-instrumented-libraries-by-existing-rules) for lists of all instrumentation provided by the SpecialAgent.

## Getting Started

See [Usage](#usage)

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

## Building

* Requirements: Java 8 or greater

`./gradlew shadowJar`

### Configuration

A New Relic [API insert key](https://docs.newrelic.com/docs/insights/insights-data-sources/custom-data/introduction-event-api#register) is required to send data to New Relic. The API insert key can be configured directly via a system property or with an optional config file.

#### Config Mechanisms

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

#### Config Properties

List of all configurable Tracer properties

| Config Key       | Value                                                     | Required |
| :--------------  | :-------------------------------------------------------- | :------  |
| `nr.apiKey`      | New Relic Insights API Insert key                         | Yes      |
| `nr.serviceName` | Display name of service, sets `service.name` on the spans | No       |

## Testing

`TODO`

## Support

Should you need assistance with New Relic products, you are in good hands with several support channels.

If the issue has been confirmed as a bug or is a feature request, file a GitHub issue.

**Support Channels**

* [New Relic Documentation](https://docs.newrelic.com/docs/agents/java-agent/configuration/java-agent-configuration-config-file#h2-transaction-tracer): Comprehensive guidance for using our platform
* [New Relic Community](https://discuss.newrelic.com/tags/javaagent): The best place to engage in troubleshooting questions
* [New Relic Developer](https://developer.newrelic.com/): Resources for building a custom observability applications
* [New Relic University](https://learn.newrelic.com/): A range of online training for New Relic users of every level

## Privacy
At New Relic we take your privacy and the security of your information seriously, and are committed to protecting your information. We must emphasize the importance of not sharing personal data in public forums, and ask all users to scrub logs and diagnostic information for sensitive information, whether personal, proprietary, or otherwise.

We define “Personal Data” as any information relating to an identified or identifiable individual, including, for example, your name, phone number, post code or zip code, Device ID, IP address, and email address.

For more information, review [New Relic’s General Data Privacy Notice](https://newrelic.com/termsandconditions/privacy).

## Contribute

We encourage your contributions to improve newrelic-tracer-java! Keep in mind that when you submit your pull request, you'll need to sign the CLA via the click-through using CLA-Assistant. You only have to sign the CLA one time per project.

If you have any questions, or to execute our corporate CLA (which is required if your contribution is on behalf of a company), drop us an email at opensource@newrelic.com.

**A note about vulnerabilities**

As noted in our [security policy](../../security/policy), New Relic is committed to the privacy and security of our customers and their data. We believe that providing coordinated disclosure by security researchers and engaging with the security community are important means to achieve our security goals.

If you believe you have found a security vulnerability in this project or any of New Relic's products or websites, we welcome and greatly appreciate you reporting it to New Relic through [HackerOne](https://hackerone.com/newrelic).

If you would like to contribute to this project, review [these guidelines](./CONTRIBUTING.md).

To [all contributors](https://github.com/newrelic/newrelic-tracer-java/graphs/contributors), we thank you!  Without your contribution, this project would not be what it is today.  We also host a community project page dedicated to [New Relic OpenTracing Tracer Plugin](https://opensource.newrelic.com/projects/newrelic/newrelic-tracer-java).

### Licensing

The newrelic-tracer-java is licensed under the Apache 2.0 License.

The newrelic-tracer-java may use source code from third party libraries.
Full details on which libraries are used and the terms under which they are licensed can be found in [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).
