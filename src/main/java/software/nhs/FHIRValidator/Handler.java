package software.nhs.FHIRValidator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler implements RequestHandler<String, ValidatorResponse> {

    private final Validator validator;

    public Handler() {
        log.info("Creating the Validator instance for the first time...");

        validator = new Validator();

        log.info("Validating once to force the loading of all the validator related classes");
        // Validating a complex Patient yields better results. validating a trivial "empty" Patient won't load all the validation classes.
        String someSyntheaPatient = "{\"resourceType\":\"Patient\",\"id\":\"a8bc0c9f-47b3-ee31-60c6-fb8ce8077ac7\",\"text\":{\"status\":\"generated\",\"div\":\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\">Generated by <a href=\\\"https://github.com/synthetichealth/synthea\\\">Synthea</a>.Version identifier: master-branch-latest-2-gfd2217b\\n .   Person seed: -5969330820059413579  Population seed: 1614314878171</div>\"},\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/patient-mothersMaidenName\",\"valueString\":\"Son314 Vandervort697\"},{\"url\":\"http://hl7.org/fhir/StructureDefinition/patient-birthPlace\",\"valueAddress\":{\"city\":\"New Bedford\",\"state\":\"Massachusetts\",\"country\":\"US\"}},{\"url\":\"http://synthetichealth.github.io/synthea/disability-adjusted-life-years\",\"valueDecimal\":1.1872597438165626},{\"url\":\"http://synthetichealth.github.io/synthea/quality-adjusted-life-years\",\"valueDecimal\":70.81274025618343}],\"identifier\":[{\"system\":\"https://github.com/synthetichealth/synthea\",\"value\":\"a8bc0c9f-47b3-ee31-60c6-fb8ce8077ac7\"},{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"MR\",\"display\":\"Medical Record Number\"}],\"text\":\"Medical Record Number\"},\"system\":\"http://hospital.smarthealthit.org\",\"value\":\"a8bc0c9f-47b3-ee31-60c6-fb8ce8077ac7\"},{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"SS\",\"display\":\"Social Security Number\"}],\"text\":\"Social Security Number\"},\"system\":\"http://hl7.org/fhir/sid/us-ssn\",\"value\":\"999-49-6778\"},{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"DL\",\"display\":\"Driver's License\"}],\"text\":\"Driver's License\"},\"system\":\"urn:oid:2.16.840.1.113883.4.3.25\",\"value\":\"S99922723\"},{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"PPN\",\"display\":\"Passport Number\"}],\"text\":\"Passport Number\"},\"system\":\"http://standardhealthrecord.org/fhir/StructureDefinition/passportNumber\",\"value\":\"X72123203X\"}],\"name\":[{\"use\":\"official\",\"family\":\"Beier427\",\"given\":[\"Minnie888\"],\"prefix\":[\"Mrs.\"]},{\"use\":\"maiden\",\"family\":\"Jaskolski867\",\"given\":[\"Minnie888\"],\"prefix\":[\"Mrs.\"]}],\"telecom\":[{\"system\":\"phone\",\"value\":\"555-390-9260\",\"use\":\"home\"}],\"gender\":\"female\",\"birthDate\":\"1949-01-01\",\"address\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/geolocation\",\"extension\":[{\"url\":\"latitude\",\"valueDecimal\":41.83492774608349},{\"url\":\"longitude\",\"valueDecimal\":-70.58336455010793}]}],\"line\":[\"862 Sauer Station Suite 31\"],\"city\":\"Plymouth\",\"state\":\"Massachusetts\",\"country\":\"US\"}],\"maritalStatus\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v3-MaritalStatus\",\"code\":\"M\",\"display\":\"M\"}],\"text\":\"M\"},\"multipleBirthInteger\":3,\"communication\":[{\"language\":{\"coding\":[{\"system\":\"urn:ietf:bcp:47\",\"code\":\"en-US\",\"display\":\"English\"}],\"text\":\"English\"}}]}";
        validator.validate(someSyntheaPatient);
        log.info("Validator is ready");
    }

    @Override
    public ValidatorResponse handleRequest(String event, Context context) {
        log.info(event);
        ValidatorResponse validate = validator.validate(event);
        return validate;

    }
}
