package com.github.jlgrock.snp.core.domain.fhir.processors;

import com.github.jlgrock.snp.apis.classifier.LogicalExpressionClassifier;
import com.github.jlgrock.snp.core.domain.fhir.converters.FhirEncounterConverter;
import com.github.jlgrock.snp.core.domain.fhir.model.Encounter;
import com.github.jlgrock.snp.domain.data.EncounterRepository;
import com.github.jlgrock.snp.domain.data.PatientRepository;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The processor used for handling Encounter objects unmarshalled from FHIR XML.
 */
@Service
public class EncounterProcessor extends AbstractFhirProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncounterProcessor.class);

    private final EncounterRepository encounterRepository;
    private final FhirEncounterConverter fhirEncounterConverter;
    private final PatientRepository patientRepository;

    /**
     * Constructor.
     * @param logicalExpressionClassifierIn the classification utility for when a logical encounter is created
     * @param fhirEncounterConverterIn the converter that will change a FHIR XML Encounter to a domain Encounter
     * @param encounterRepositoryIn the repository to create the newly created Encounter
     * @param patientRepositoryIn the repository to create the newly created Patient, if necessary
     */
    @Inject
    public EncounterProcessor(final LogicalExpressionClassifier logicalExpressionClassifierIn,
                              final FhirEncounterConverter fhirEncounterConverterIn,
                              final EncounterRepository encounterRepositoryIn,
                              final PatientRepository patientRepositoryIn) {
        super(logicalExpressionClassifierIn);
        fhirEncounterConverter = fhirEncounterConverterIn;
        encounterRepository = encounterRepositoryIn;
        patientRepository = patientRepositoryIn;
    }

	@Override
	public void process(final String identifier, final Object unmarshalledObject) {
        Encounter encounter = (Encounter) unmarshalledObject;

        LOGGER.trace("Processing Encounter {}", encounter);

        // find the patient by the fhir id
        String reference = encounter.getSubject().getReference().getValue();
        com.github.jlgrock.snp.domain.types.Patient patient = patientRepository.findOneByFhirId(reference);

        // save the patient if it doesn't exists
        if (patient == null) {
            patient = new com.github.jlgrock.snp.domain.types.Patient();
            patientRepository.save(patient);
        }

        // convert the encounter to the domain type of encounter
        com.github.jlgrock.snp.domain.types.Encounter saveVal = fhirEncounterConverter.convert(encounter);

        // set the patient for the encounter
        saveVal.setPatientId(patient.getId());

        // Copy the assertions over if they exist already
        com.github.jlgrock.snp.domain.types.Encounter foundEncounter = encounterRepository.findOneByFhirId(identifier);
        if (foundEncounter != null) {
            saveVal.setId(foundEncounter.getId());
            saveVal.setAssertions(foundEncounter.getAssertions());
        }

        // insert/update the encounter
        encounterRepository.save(saveVal);

	}

    @Override
    public Class processesType() {
        return Encounter.class;
    }
}
