package com.deloitte.mongo.converters;

import com.deloitte.mongo.data.ObservationTags;
import com.deloitte.mongo.domain.Observation;
import com.deloitte.mongo.domain.primitives.PrimitiveType;
import com.mongodb.DBObject;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObservationReadConverterTest {

    @Test
    public void testConvert() {
        Date date = new Date();

        DBObject dbObj = mock(DBObject.class);
        when(dbObj.get(ObservationTags.ID_TAG)).thenReturn("stringId");
        when(dbObj.get(ObservationTags.NAME_TAG)).thenReturn("asdf");
        when(dbObj.get(ObservationTags.NAME_TYPE_TAG)).thenReturn(PrimitiveType.STRING.getId());
        when(dbObj.get(ObservationTags.VALUE_TAG)).thenReturn(true);
        when(dbObj.get(ObservationTags.VALUE_TYPE_TAG)).thenReturn(PrimitiveType.BOOLEAN.getId());
        when(dbObj.get(ObservationTags.APPLIES_TAG)).thenReturn("applies");
        when(dbObj.get(ObservationTags.ISSUED_TAG)).thenReturn(date.getTime());
        when(dbObj.get(ObservationTags.SUBJECT_TAG)).thenReturn("subject");

        ObservationReadConverter observationReadConverter = new ObservationReadConverter();
        Observation observation = observationReadConverter.convert(dbObj);

        assertEquals("stringId", observation.getIdentifier());
        assertEquals(PrimitiveType.STRING, observation.getName().getType());
        assertEquals("asdf", observation.getName().getValue());
        assertEquals(PrimitiveType.BOOLEAN, observation.getValue().getType());
        assertEquals(true, observation.getValue().getValue());
        assertEquals("applies", observation.getApplies());
        assertEquals(date, observation.getIssued());
        assertEquals("subject", observation.getSubject());

    }
}