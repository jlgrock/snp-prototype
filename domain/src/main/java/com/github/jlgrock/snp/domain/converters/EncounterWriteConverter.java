package com.github.jlgrock.snp.domain.converters;

import com.github.jlgrock.snp.apis.converters.WriteConverter;
import com.github.jlgrock.snp.domain.data.EncounterTags;
import com.github.jlgrock.snp.domain.data.SharedTags;
import com.github.jlgrock.snp.domain.types.Encounter;
import com.github.jlgrock.snp.domain.types.Observation;
import com.mongodb.BasicDBList;
import org.bson.Document;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A Conversion class to convert between an Encounter objects and a MongoDB DBObject.
 */
@Service
@Named
public class EncounterWriteConverter implements WriteConverter<Encounter, Document> {

    private final ObservationWriteConverter observationWriteConverter;
    /**
     * 
     * @param observationWriteConverterIn object of type ObservationWriteConverter that has values from
     *                                    an Observation object stored in a MongoDB object.
     */
    @Inject
    public EncounterWriteConverter(final ObservationWriteConverter observationWriteConverterIn) {
        observationWriteConverter = observationWriteConverterIn;
    }

    @Override
    public Document convert(final Encounter source) {
        Document dbo = new Document();
        if (source.getId() != null) {
            dbo.put(SharedTags.ID_TAG, source.getId());
        }
        if (source.getEncounterClass() != null) {
           dbo.put(EncounterTags.ENCOUNTER_CLASS, source.getEncounterClass());
        }
        if (source.getStatus() != null) {
            dbo.put(EncounterTags.STATUS, source.getStatus());
        }
        if (source.getSubject() != null) {
            dbo.put(EncounterTags.SUBJECT, source.getSubject());
        }
        if (source.getParticipant() != null) {
            dbo.put(EncounterTags.PARTICIPANT, source.getParticipant());
        }
        if (source.getPatientId() != null) {
            dbo.put(EncounterTags.PATIENT_ID, source.getPatientId());
        }

        if (source.getObservations() != null ) {
            List<Observation> observations = source.getObservations();

            BasicDBList observationObjs = new BasicDBList();
            if (observations != null) {
                observationObjs = observations
                        .stream()
                        .map(observation -> observationWriteConverter.convert(observation))
                        .collect(Collectors.toCollection(BasicDBList::new));
            }
            dbo.put(EncounterTags.OBSERVATIONS_TAG, observationObjs);
        }
        return dbo;
    }
}

