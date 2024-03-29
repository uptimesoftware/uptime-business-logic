package com.uptimesoftware.business.element.add;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uptimesoftware.business.snmp.SnmpVersion;
import com.uptimesoftware.business.validation.oval.ValidatorFactory;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class AddNetworkDeviceElementCollectionMethodTest {

	private static final String INVALID = "INVALID";
	private static final AddNetworkDeviceElementCollectionMethod V1_VALID = new AddNetworkDeviceElementCollectionMethod(false,
			"v1", 1234,"pub", null, "user", "pass", null, null, null, true);
	private static final AddNetworkDeviceElementCollectionMethod V1_INVALID = new AddNetworkDeviceElementCollectionMethod(false,
			"v1", 1234,null, null, "user", "pass", null, null, null, true);
	private static final AddNetworkDeviceElementCollectionMethod V2_VALID = new AddNetworkDeviceElementCollectionMethod(false,
			"v2", 1234,null, "pub", "user", "pass", null, null, null, true);
	private static final AddNetworkDeviceElementCollectionMethod V2_INVALID = new AddNetworkDeviceElementCollectionMethod(false,
			"v2", 1234,null, null, "user", "pass", null, null, null, true);
	private static final AddNetworkDeviceElementCollectionMethod V3_VALID = new AddNetworkDeviceElementCollectionMethod(false,
			"v3", 1234,null, null, "user", "pass", "MD5", "pass", "DES", true);
	private static final AddNetworkDeviceElementCollectionMethod V3_INVALID_PASSWORD = new AddNetworkDeviceElementCollectionMethod(
			false, "v3", 1234,null, null, "user", null, "MD5", "pass", "DES", true);
	private static final AddNetworkDeviceElementCollectionMethod V3_INVALID_AUTH = new AddNetworkDeviceElementCollectionMethod(
			false, "v3", 1234,null, null, "user", "pass", INVALID, "pass", "DES", true);
	private static final String V1_VALID_JSON = "{\"connectionType\":\"snmp\",\"useGlobalConnectionSettings\":false,\"snmpVersion\":\"v1\",\"snmpPort\":1234,\"snmpV1ReadCommunity\":\"pub\",\"isPingable\":true}";
	private static final String V1_VALID_JSON_WITH_NULLS = "{\"connectionType\":\"snmp\",\"useGlobalConnectionSettings\":false,\"snmpVersion\":\"v1\",\"snmpPort\":1234,\"snmpV1ReadCommunity\":\"pub\",\"snmpV2ReadCommunity\":null,\"snmpV3Username\":\"user\",\"snmpV3AuthenticationPassword\":\"pass\",\"snmpV3AuthenticationMethod\":null,\"snmpV3PrivacyPassword\":null,\"snmpV3PrivacyType\":null,\"isPingable\":true}";
	private static final String V2_VALID_JSON = "{\"connectionType\":\"snmp\",\"useGlobalConnectionSettings\":false,\"snmpVersion\":\"v2\",\"snmpPort\":1234,\"snmpV2ReadCommunity\":\"pub\",\"isPingable\":true}";
	private static final String V2_VALID_JSON_WITH_NULLS = "{\"connectionType\":\"snmp\",\"useGlobalConnectionSettings\":false,\"snmpVersion\":\"v2\",\"snmpPort\":1234,\"snmpV1ReadCommunity\":null,\"snmpV2ReadCommunity\":\"pub\",\"snmpV3Username\":\"user\",\"snmpV3AuthenticationPassword\":\"pass\",\"snmpV3AuthenticationMethod\":null,\"snmpV3PrivacyPassword\":null,\"snmpV3PrivacyType\":null,\"isPingable\":true}";
	private static final String V3_VALID_JSON = "{\"connectionType\":\"snmp\",\"useGlobalConnectionSettings\":false,\"snmpVersion\":\"v3\",\"snmpPort\":1234,\"snmpV3Username\":\"user\",\"snmpV3AuthenticationPassword\":\"pass\",\"snmpV3AuthenticationMethod\":\"MD5\",\"snmpV3PrivacyPassword\":\"pass\",\"snmpV3PrivacyType\":\"DES\",\"isPingable\":true}";
	private static final String V3_VALID_JSON_WITH_NULLS = "{\"connectionType\":\"snmp\",\"useGlobalConnectionSettings\":false,\"snmpVersion\":\"v3\",\"snmpPort\":1234,\"snmpV1ReadCommunity\":null,\"snmpV2ReadCommunity\":null,\"snmpV3Username\":\"user\",\"snmpV3AuthenticationPassword\":\"pass\",\"snmpV3AuthenticationMethod\":\"MD5\",\"snmpV3PrivacyPassword\":\"pass\",\"snmpV3PrivacyType\":\"DES\",\"isPingable\":true}";

	private final ObjectMapper jsonMapper = new ObjectMapper();
	private final Validator validator = ValidatorFactory.getValidator();

	@Test
	public void testCreateV1FromJson() {
		AddNetworkDeviceElementCollectionMethod v1;
		try {
			v1 = jsonMapper.readValue(V1_VALID_JSON, AddNetworkDeviceElementCollectionMethod.class);
		} catch (IOException e) {
			fail("deserialize V1_VALID failed: " + e.getMessage());
			return;
		}
		assertTrue(v1.getSnmpVersion() == SnmpVersion.v1);
	}

	@Test
	public void testCreateV1FromJsonWithNulls() {
		String v1ValidJsonWithNulls;
		try {
			v1ValidJsonWithNulls = jsonMapper.writeValueAsString(V1_VALID);
		} catch (IOException e) {
			fail("serialize V1_VALID failed");
			return;
		}
		assertEquals(V1_VALID_JSON_WITH_NULLS, v1ValidJsonWithNulls);

		AddNetworkDeviceElementCollectionMethod v1;
		try {
			v1 = jsonMapper.readValue(v1ValidJsonWithNulls, AddNetworkDeviceElementCollectionMethod.class);
		} catch (IOException e) {
			fail("deserialize v1ValidJsonWithNulls failed");
			return;
		}
		assertTrue(v1.getSnmpVersion() == SnmpVersion.v1);
		assertEquals(V1_VALID.getSnmpVersion(), v1.getSnmpVersion());
	}
	
	@Test
	public void testCreateV2FromJson() {
		AddNetworkDeviceElementCollectionMethod v2;
		try {
			v2 = jsonMapper.readValue(V2_VALID_JSON, AddNetworkDeviceElementCollectionMethod.class);
		} catch (IOException e) {
			fail("deserialize V2_VALID failed: " + e.getMessage());
			return;
		}
		assertTrue(v2.getSnmpVersion() == SnmpVersion.v2);
	}

	@Test
	public void testCreateV2FromJsonWithNulls() {
		String v2ValidJsonWithNulls;
		try {
			v2ValidJsonWithNulls = jsonMapper.writeValueAsString(V2_VALID);
		} catch (IOException e) {
			fail("serialize V2_VALID failed");
			return;
		}
		assertEquals(V2_VALID_JSON_WITH_NULLS, v2ValidJsonWithNulls);

		AddNetworkDeviceElementCollectionMethod v2;
		try {
			v2 = jsonMapper.readValue(v2ValidJsonWithNulls, AddNetworkDeviceElementCollectionMethod.class);
		} catch (IOException e) {
			fail("deserialize v2ValidJsonWithNulls failed");
			return;
		}
		assertTrue(v2.getSnmpVersion() == SnmpVersion.v2);
		assertEquals(V2_VALID.getSnmpVersion(), v2.getSnmpVersion());
	}

	@Test
	public void testCreateV3FromJson() {
		AddNetworkDeviceElementCollectionMethod v3;
		try {
			v3 = jsonMapper.readValue(V3_VALID_JSON, AddNetworkDeviceElementCollectionMethod.class);
		} catch (IOException e) {
			fail("deserialize V3_VALID failed: " + e.getMessage());
			return;
		}
		assertTrue(v3.getSnmpVersion() == SnmpVersion.v3);
	}

	@Test
	public void testCreateV3FromJsonWithNulls() {
		String v3ValidJsonWithNulls;
		try {
			v3ValidJsonWithNulls = jsonMapper.writeValueAsString(V3_VALID);
		} catch (IOException e) {
			fail("serialize V3_VALID failed");
			return;
		}
		assertEquals(V3_VALID_JSON_WITH_NULLS, v3ValidJsonWithNulls);

		AddNetworkDeviceElementCollectionMethod v3;
		try {
			v3 = jsonMapper.readValue(v3ValidJsonWithNulls, AddNetworkDeviceElementCollectionMethod.class);
		} catch (IOException e) {
			fail("deserialize v3ValidJsonWithNulls failed");
			return;
		}
		assertTrue(v3.getSnmpVersion() == SnmpVersion.v3);
		assertEquals(V3_VALID.getSnmpVersion(), v3.getSnmpVersion());
	}

	@Test
	public void testValidateV1() {
		List<ConstraintViolation> validate = validator.validate(V1_VALID);
		assertTrue(validate.isEmpty());

		validate = validator.validate(V1_INVALID);
		assertTrue(validate.size() == 1);
		assertEquals("The snmp v1 read community is required", validate.get(0).getMessage());
	}


	@Test
	public void testValidateV2() {
		List<ConstraintViolation> validate = validator.validate(V2_VALID);
		assertTrue(validate.isEmpty());

		validate = validator.validate(V2_INVALID);
		assertTrue(validate.size() == 1);
		assertEquals("The snmp v2 read community is required", validate.get(0).getMessage());
	}

	@Test
	public void testValidateV3() {
		List<ConstraintViolation> validate = validator.validate(V3_VALID);
		assertTrue(validate.isEmpty());

		validate = validator.validate(V3_INVALID_PASSWORD);
		assertTrue(validate.size() == 1);
		assertEquals("The snmp v3 authentication password is required", validate.get(0).getMessage());

		validate = validator.validate(V3_INVALID_AUTH);
		assertTrue(validate.size() == 1);
		assertEquals("The snmp v3 authentication method is invalid. It must be one of [MD5, SHA].", validate.get(0).getMessage());
		assertEquals(INVALID, validate.get(0).getInvalidValue());
	}

}
