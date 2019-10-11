/*
 * Copyright 2019 New Relic Corporation. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.newrelic.opentracing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

final class Configuration {
    private Configuration() {}

    private final static Logger logger = Logger.getLogger(Configuration.class.getName());

    private final static String DEFAULT_CONFIGURATION_FILE_PATH = "tracer.properties";
    private final static String CONFIGURATION_FILE_KEY = "tracer.configurationFile";

    static Properties loadConfigurationFile() {
        String path = System.getProperty(CONFIGURATION_FILE_KEY);
        if (path == null)
            path = DEFAULT_CONFIGURATION_FILE_PATH;

        Properties props = new Properties();

        File file = new File(path);
        if (!file.isFile())
            return props;

        try (FileInputStream stream = new FileInputStream(file)) {
            props.load(stream);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read the Tracer configuration file '" + path + "'");
            logger.log(Level.WARNING, e.toString());
        }

        logger.log(Level.INFO, "Successfully loaded Tracer configuration file " + path);
        return props;
    }
}

