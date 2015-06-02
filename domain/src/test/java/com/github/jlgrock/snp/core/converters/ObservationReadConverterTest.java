package com.github.jlgrock.snp.core.converters;

import com.github.jlgrock.snp.domain.converters.ObservationReadConverter;
import com.github.jlgrock.snp.domain.data.ObservationTags;
import com.github.jlgrock.snp.domain.types.Observation;
import com.github.jlgrock.snp.domain.types.primitives.PrimitiveType;
import org.bson.Document;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.time.Instant;

public class ObservationReadConverterTest {
    /**
     * public function returns void
     */
    @Test
    public void testConvert() {
        Instant date = Instant.now();

        Document dbObj = Mockito.mock(Document.class);
        Mockito.when(dbObj.get(ObservationTags.ID_TAG)).thenReturn("stringId");
        Mockito.when(dbObj.get(ObservationTags.NAME_TAG)).thenReturn("asdf");
        Mockito.when(dbObj.get(ObservationTags.NAME_TYPE_TAG)).thenReturn(PrimitiveType.STRING.getId());
        Mockito.when(dbObj.get(ObservationTags.VALUE_TAG)).thenReturn(true);
        Mockito.when(dbObj.get(ObservationTags.VALUE_TYPE_TAG)).thenReturn(PrimitiveType.BOOLEAN.getId());
        Mockito.when(dbObj.get(ObservationTags.APPLIES_TAG)).thenReturn("applies");
        Mockito.when(dbObj.get(ObservationTags.ISSUED_TAG)).thenReturn(date.toEpochMilli());
        Mockito.when(dbObj.get(ObservationTags.SUBJECT_TAG)).thenReturn("subject");

        ObservationReadConverter observationReadConverter = new ObservationReadConverter();
        Observation observation = observationReadConverter.convert(dbObj);

//        TODO: FIXME
//        assertEquals("stringId", observation.getIdentifier());
//        assertEquals(PrimitiveType.STRING, observation.getName().getType());
//        assertEquals("asdf", observation.getName().getValue());
//        assertEquals(PrimitiveType.BOOLEAN, observation.getValue().getType());
//        assertEquals(true, observation.getValue().getValue());
//        assertEquals("applies", observation.getApplies());
//        assertEquals(date, observation.getIssued());
//        assertEquals("subject", observation.getSubject());

    }
}

