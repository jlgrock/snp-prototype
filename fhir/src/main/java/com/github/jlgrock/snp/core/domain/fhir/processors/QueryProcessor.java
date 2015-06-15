package com.github.jlgrock.snp.core.domain.fhir.processors;

import com.github.jlgrock.snp.apis.classifier.LogicGraphClassifier;
import com.github.jlgrock.snp.core.domain.fhir.model.Query;
import gov.vha.isaac.logic.LogicGraph;

/**
 *
 */
public class QueryProcessor extends AbstractFhirProcessor {

    private final Query query;

    public QueryProcessor(final LogicGraphClassifier logicGraphClassifierIn, final Query queryIn) {
        super(logicGraphClassifierIn);
        query = queryIn;
    }

    @Override
    protected LogicGraph getLogicGraph() {
        throw new UnsupportedOperationException();
    }

	@Override
	public void process() {
		throw new UnsupportedOperationException();
		
	}
}
