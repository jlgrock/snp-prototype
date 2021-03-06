package com.github.jlgrock.snp.apis.classifier;

import gov.vha.isaac.ochre.api.logic.LogicalExpression;
import org.ihtsdo.otf.tcc.api.concept.ConceptChronicleBI;
import org.jvnet.hk2.annotations.Contract;

import java.io.IOException;
import java.util.UUID;

/**
 * Classify Post Coordinated Expressions using the classification store.
 */
@Contract
public interface LogicalExpressionClassifier {

	/**
	 * Get the native identifier
	 * 
	 * @param sctid
	 *            SNOMED clinical terms identifier
	 * @return the native identifier (for all objects)
	 */
	int getNidFromSNOMED(String sctid);

	/**
	 *
	 * @param sctid
	 *            SNOMED clinical terms identifier
	 * @return the UUID
	 */
	UUID getUUIDFromSNOMED(String sctid);

	/**
	 * Find a chronicle based on the sctId
	 * 
	 * @param sctid
	 *            SNOMED clinical terms identifier
	 * @return concept chronicle
	 */
	ConceptChronicleBI findChronicle(String sctid);

	/**
	 * Find a chronicle based on the nid
	 * 
	 * @param nid
	 *            native identifier
	 * @return concept chronicle
	 */
	ConceptChronicleBI findChronicle(int nid);

	/**
	 * Find the native id for the indexed LogicGraph. If one does not exist, it
	 * will create one and return the native id.
	 *
	 * @param pce
	 *            Post Coordinated Expression
	 * @return the native identifier
	 */
	Integer classify(LogicalExpression pce);

	/**
	 * Get the stated terminology logic graph for the given native id.
	 * 
	 * @param nid
	 *            native identifier to get the respective logic graph
	 * @return the logic graph for the respective nid
	 * @throws IOException
	 *             if there was IO related error encountered while looking up
	 *             the logic graph
	 */
	LogicalExpression getInferredTermLogicalExpression(int nid) throws IOException;

}
