package com.github.jlgrock.snp.core.domain.fhir.converters;

import com.github.jlgrock.snp.core.domain.fhir.model.Encounter;
import com.github.jlgrock.snp.domain.data.PatientRepository;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static org.testng.Assert.assertEquals;

public class FhirEncounterConverterTest extends AbstractConverterTest {
	
	public final static String xmlFile = "Encounter-example-f201-20130404.xml";

	@Test
	public void testConvert() throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(path);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<Encounter> je = (JAXBElement<Encounter>) jaxbUnmarshaller.unmarshal(
				readFile(xmlFile));
		Encounter encounterIn = je.getValue();
		PatientRepository patientRepositoryMock = Mockito.mock(PatientRepository.class);
		FhirEncounterConverter encounterConverter = new FhirEncounterConverter(patientRepositoryMock);
		
		com.github.jlgrock.snp.domain.types.Encounter encounter = encounterConverter.convert(encounterIn);
		
		//TODO check the patient id somehow
		assertEquals(encounter.getEncounterClass(), "OUTPATIENT");
		assertEquals(encounter.getStatus(), "FINISHED");
		assertEquals(encounter.getParticipant(), "Practitioner/f201");
		assertEquals(encounter.getSubject(), "Patient/f201");
	}
}
