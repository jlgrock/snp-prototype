package com.github.jlgrock.snp.core.converters;

import com.github.jlgrock.snp.core.data.AssertionTags;
import com.github.jlgrock.snp.core.domain.ClassifiedAssertion;
import org.bson.Document;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 *
 */
public class ClassifiedClassifiedAssertionReadConverterTest {

    /**
     * public function returns void
     */
    @Test
    public void testConvert() {
        Document dbObj = mock(Document.class);
        when(dbObj.get(AssertionTags.ID_TAG)).thenReturn((Long) 123l);
        when(dbObj.get(AssertionTags.DESCRIPTION_TAG)).thenReturn("bla");

        ClassifiedAssertionReadConverter patientReadConverter = new ClassifiedAssertionReadConverter();
        ClassifiedAssertion assertion = patientReadConverter.convert(dbObj);

        assertEquals((Long) 123l, assertion.getId());
//        TODO: FIXME
//        assertEquals("bla", assertion.getDesc());
    }
}
