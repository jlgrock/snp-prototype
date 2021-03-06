package com.github.jlgrock.snp.core.domain.fhir.logicalexpression;

import com.github.jlgrock.snp.apis.classifier.LogicalExpressionClassifier;
import com.github.jlgrock.snp.core.domain.fhir.model.CodeableConcept;
import gov.vha.isaac.ochre.api.logic.LogicalExpression;
import gov.vha.isaac.ochre.api.logic.LogicalExpressionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Build a LogicalExpression from a FHIR Codeable Concept.
 */
public class FhirCodeableConceptGraphBuilder extends AbstractFhirLogicGraphBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(FhirCodeableConceptGraphBuilder.class);

    @Override
    public LogicalExpression build(final CodeableConcept codeableConcept) {
        LOGGER.trace("building Coding Graph Builder");
        LogicalExpressionBuilder.SufficientSet(buildCodeableConcept(codeableConcept));
        return getLogicalExpressionBuilder().build();
    }

    /**
     * Constructor.
     * @param logicalExpressionClassifierIn the classifier to store the logical expression, once it has been created
     */
    public FhirCodeableConceptGraphBuilder(final LogicalExpressionClassifier logicalExpressionClassifierIn) {
        super(logicalExpressionClassifierIn);
    }

}
