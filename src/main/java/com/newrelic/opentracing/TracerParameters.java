/*
 * Copyright 2019 New Relic Corporation. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.newrelic.opentracing;

import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

final class TracerParameters {
    private TracerParameters() {
    }

    private final static Logger logger = Logger.getLogger(TracerParameters.class.getName());
    final static String NR_API_KEY = "nr.apiKey";
    final static String NR_SERVICE_NAME = "nr.serviceName";

    private final static String[] ALL = {
            NR_API_KEY,
            NR_SERVICE_NAME
    };

    @SuppressWarnings("unchecked")
    static Map<String, String> getParameters() {
        Properties props = Configuration.loadConfigurationFile();
        loadSystemProperties(props);

        for (String propName : props.stringPropertyNames())
            logger.log(Level.INFO, "Retrieved New Relic Tracer parameter " + propName + "=" + props.getProperty(propName));

        // A Properties object is expected to only contain String keys/values.
        return (Map) props;
    }

    private static void loadSystemProperties(Properties props) {
        for (String paramName : ALL) {
            String paramValue = System.getProperty(paramName);
            if (paramValue != null)
                props.setProperty(paramName, paramValue);
        }
    }
}

