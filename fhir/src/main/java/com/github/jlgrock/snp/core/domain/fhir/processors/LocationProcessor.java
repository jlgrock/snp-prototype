package com.github.jlgrock.snp.core.domain.fhir.processors;

import com.github.jlgrock.snp.apis.classifier.LogicalExpressionClassifier;
import com.github.jlgrock.snp.core.domain.fhir.model.Location;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class LocationProcessor extends AbstractFhirProcessor {

    @Inject
    public LocationProcessor(final LogicalExpressionClassifier logicalExpressionClassifierIn) {
        super(logicalExpressionClassifierIn);
    }

	@Override
	public void process(final String identifier, final Object unmarshalledObject) {
        Location location = (Location) unmarshalledObject;
		throw new UnsupportedOperationException();
		
	}

    @Override
    public Class processesType() {
        return Location.class;
    }
}
