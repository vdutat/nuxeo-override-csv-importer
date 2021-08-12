package com.acme.csv;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.csv.core.CSVImporter;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import javax.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ PlatformFeature.class })
@Deploy("com.acme.csv.nuxeo-override-csv-importer-core")
@Deploy("com.acme.csv.nuxeo-override-csv-importer-core-test:OSGI-INF/test-csv-service.xml")
public class TestCSVImporter {

    @Inject
    protected CSVImporter csvimporter;

    @Test
    public void testService() {
        assertNotNull(csvimporter);
    }
}
