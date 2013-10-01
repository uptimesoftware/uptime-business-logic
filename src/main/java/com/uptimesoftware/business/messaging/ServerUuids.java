package com.uptimesoftware.business.messaging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import com.google.common.base.Charsets;

public class ServerUuids {
	
	public enum ServerType {
		CORE(Paths.get("uptime-core.uuid")),
		CONTROLLER(Paths.get("etc/uptime-controller.uuid"));
		
		private Path path;
		
		private ServerType(Path uuidPath) {
			this.path = uuidPath;
		}

		public Path getPath() {
			return path;
		}
	}

	public static UUID generate() {
		return UUID.randomUUID();
	}

	public static void storeOnce(UUID uuid, Path file) throws IOException {
		Path realPath = file.toAbsolutePath();
		Path tempFile = Files.createTempFile(realPath.getParent(), "uptime-uuid", ".uuid");
		Files.write(tempFile, uuid.toString().getBytes(Charsets.UTF_8), StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING);
		// Implementations define whether atomic moves throw an exception or not when the target file exists. We always want to
		// throw.
		synchronized (ServerUuids.class) {
			if (Files.exists(realPath)) {
				Files.delete(tempFile);
				throw new IOException("The uuid file " + realPath.toString() + " already exists.");
			}
			Files.move(tempFile, realPath, StandardCopyOption.ATOMIC_MOVE);
		}
	}

	public static UUID load(Path file) throws IOException {
		byte[] bytes = Files.readAllBytes(file);
		return UUID.fromString(new String(bytes, Charsets.UTF_8));
	}
}
