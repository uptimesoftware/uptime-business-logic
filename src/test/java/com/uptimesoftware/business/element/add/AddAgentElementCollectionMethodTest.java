package com.uptimesoftware.business.element.add;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uptimesoftware.business.validation.oval.ValidatorFactory;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AddAgentElementCollectionMethodTest {

	private static final String VALID_JSON_GLOBAL_FALSE = "{\"connectionType\":\"agent\",\"useGlobalConnectionSettings\":false,\"port\":9998,\"useSSL\":false}";
	private static final String VALID_JSON_GLOBAL_TRUE = "{\"connectionType\":\"agent\",\"useGlobalConnectionSettings\":true}";

	private final ObjectMapper jsonMapper = new ObjectMapper();
	private final Validator validator = ValidatorFactory.getValidator();

	@Test
	public void testFromJson() {
		AddAgentElementCollectionMethod validJsonGlobalFalse = getCollectionMethodFromJson(VALID_JSON_GLOBAL_FALSE);
		assertEquals(false, validJsonGlobalFalse.isUseGlobalConnectionSettings());
	}

	@Test
	public void testValidateGlobalFalse() {
		AddAgentElementCollectionMethod validJsonGlobalFalse = getCollectionMethodFromJson(VALID_JSON_GLOBAL_FALSE);
		assertEquals(false, validJsonGlobalFalse.isUseGlobalConnectionSettings());
		List<ConstraintViolation> constraints = validator.validate(validJsonGlobalFalse);
		assertEquals(0, constraints.size());
	}

	@Test
	public void testValidateGlobalTrue() {
		AddAgentElementCollectionMethod validJsonGlobalTrue = getCollectionMethodFromJson(VALID_JSON_GLOBAL_TRUE);
		assertEquals(true, validJsonGlobalTrue.isUseGlobalConnectionSettings());
		List<ConstraintViolation> constraints = validator.validate(validJsonGlobalTrue);
		assertEquals(0, constraints.size());
	}

	private AddAgentElementCollectionMethod getCollectionMethodFromJson(String json) {
		ElementCollectionMethod elementCollectionMethod;
		try {
			elementCollectionMethod = jsonMapper.readValue(json, ElementCollectionMethod.class);
		} catch (IOException e) {
			fail("exception while deserializing: " + json);
			return null;
		}
		AddAgentElementCollectionMethod validJsonGlobalFalse;
		try {
			validJsonGlobalFalse = (AddAgentElementCollectionMethod) elementCollectionMethod;
		} catch (ClassCastException e) {
			fail("exception while casting to AddAgentElementCollectionMethod");
			return null;
		}
		return validJsonGlobalFalse;
	}

}
