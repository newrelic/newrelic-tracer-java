/*
 * Copyright 2019 New Relic Corporation. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.newrelic.opentracing;

import com.newrelic.telemetry.Attributes;
import com.newrelic.telemetry.opentelemetry.export.NewRelicSpanExporter;
import io.opentelemetry.opentracingshim.TraceShim;
import io.opentelemetry.sdk.distributedcontext.DistributedContextManagerSdk;
import io.opentelemetry.sdk.trace.TracerSdk;
import io.opentelemetry.sdk.trace.export.BatchSpansProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentracing.Tracer;
import io.opentracing.contrib.tracerresolver.TracerFactory;

import javax.annotation.Priority;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.newrelic.opentracing.TracerParameters.NR_API_KEY;
import static com.newrelic.opentracing.TracerParameters.NR_SERVICE_NAME;

@Priority(1)
public class NewRelicTracerFactory implements TracerFactory {
    private static final Logger logger = Logger.getLogger(NewRelicTracerFactory.class.getName());

    @Override
    public Tracer getTracer() {
        final Map<String, String> parameters = TracerParameters.getParameters();

        if (parameters.isEmpty()) {
            logger.log(Level.WARNING, "No nr.apiKey value was provided, failed to initialize the New Relic Tracer");
            return null;
        }

        try {
            String apiKey = parameters.get(NR_API_KEY);
            String serviceName = parameters.get(NR_SERVICE_NAME);
            final Attributes commonAttributes = new Attributes();

            if (serviceName != null) {
                commonAttributes.put("service.name", serviceName);
            }

            TracerSdk openTelemetryTracer = new TracerSdk();
            SpanExporter newRelicExporter = NewRelicSpanExporter.newBuilder()
                    .apiKey(apiKey)
                    .enableAuditLogging()
                    .commonAttributes(commonAttributes)
                    .build();
            openTelemetryTracer.addSpanProcessor(BatchSpansProcessor.newBuilder(newRelicExporter).build());
            Tracer tracer = TraceShim.createTracerShim(openTelemetryTracer, new DistributedContextManagerSdk());
            logger.log(Level.INFO, "Created New Relic Tracer: " + tracer);
            return tracer;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to create a New Relic Tracer instance: " + e);
            return null;
        }
    }
}
