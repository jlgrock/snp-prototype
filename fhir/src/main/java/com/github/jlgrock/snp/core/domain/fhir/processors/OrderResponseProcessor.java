package com.github.jlgrock.snp.core.domain.fhir.processors;

import com.github.jlgrock.snp.apis.classifier.LogicalExpressionClassifier;
import com.github.jlgrock.snp.core.domain.fhir.model.OrderResponse;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

/**
 * The processor used for handling OrderResponse objects unmarshalled from FHIR XML.
 */
@Service
public class OrderResponseProcessor extends AbstractFhirProcessor {

    /**
     * Constructor.
     * @param logicalExpressionClassifierIn the classification utility for when a logical encounter is created
     */
    @Inject
    public OrderResponseProcessor(final LogicalExpressionClassifier logicalExpressionClassifierIn) {
        super(logicalExpressionClassifierIn);
    }

	@Override
	public void process(final String identifier, final Object unmarshalledObject) {
        OrderResponse orderResponse = (OrderResponse) unmarshalledObject;
		throw new UnsupportedOperationException();
		
	}

    @Override
    public Class processesType() {
        return OrderResponse.class;
    }
}
