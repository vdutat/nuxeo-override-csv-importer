package com.acme.csv;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.transientstore.api.TransientStore;
import org.nuxeo.ecm.core.work.api.WorkManager;
import org.nuxeo.ecm.csv.core.CSVImportLog;
import org.nuxeo.ecm.csv.core.CSVImportStatus;
import org.nuxeo.ecm.csv.core.CSVImporterOptions;
import org.nuxeo.ecm.csv.core.CSVImporterWork;
import org.nuxeo.runtime.api.Framework;

public class CustomCSVImporterWork extends CSVImporterWork {

    static public final Serializable EMPTY_LOGS = new ArrayList<CSVImportLog>();
    
    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(CustomCSVImporterWork.class);

	public CustomCSVImporterWork(String id) {
		super(id);
	}

	public CustomCSVImporterWork(String repositoryName, String parentPath, String name, Blob blob,
			CSVImporterOptions options) {
		super(repositoryName, parentPath, name, blob, options);
	}

    public String launch() {
        WorkManager works = Framework.getService(WorkManager.class);

        TransientStore store = getStore();
        store.putParameter(id, "logs", EMPTY_LOGS);
        store.putParameter(id, "status", new CSVImportStatus(CSVImportStatus.State.SCHEDULED));
        works.schedule(this);
        return id;
    }

	@Override
	protected Blob createBlobFromFilePath(String fileRelativePath) throws IOException {
		if (log.isDebugEnabled()) {
	        log.debug("<createBlobFromFilePath> " + fileRelativePath);
		}
		// TODO Remove call to super.createBlobFromFilePath() below and 
		// implement your own blob store for the CSV importer
		return super.createBlobFromFilePath(fileRelativePath);
	}

}
