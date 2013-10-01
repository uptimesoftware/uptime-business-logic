package com.uptimesoftware.business.messaging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.Test;

public class ServerUuidsTest {

	@Test
	public void testRoundTrip() {
		UUID expectedUuid = ServerUuids.generate();
		assertNotNull(expectedUuid);
		Path uuidPath = Paths.get("my.uuid");
		try {
			ServerUuids.storeOnce(expectedUuid, uuidPath);
		} catch (IOException e) {
			fail("Should not throw.");
		}
		try {
			try {
				UUID actualUuid = ServerUuids.load(uuidPath);
				assertEquals(expectedUuid, actualUuid);
			} catch (IOException e1) {
				fail("Should not throw.");
			}
		} finally {
			try {
				Files.delete(uuidPath);
			} catch (IOException e) {
				fail("Could not clean up uuid file. Should not throw.");
			}
		}
	}

	@Test
	public void testExceptionWhenUuidFileExists() {
		UUID expectedUuid = ServerUuids.generate();
		assertNotNull(expectedUuid);
		Path uuidPath = Paths.get("my.uuid");
		try {
			Files.createFile(uuidPath);
		} catch (IOException e2) {
			fail("Should not throw.");
		}
		try {
			try {
				ServerUuids.storeOnce(expectedUuid, uuidPath);
				fail("Should throw.");
			} catch (IOException e) {
				assertTrue("Should get an IOException", true);
			}
		} finally {
			try {
				Files.delete(uuidPath);
			} catch (IOException e) {
				fail("Could not clean up uuid file. Should not throw.");
			}
		}
	}

}
