package com.ja.markdown.modeller.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Resources {

	public static InputStream asInputStream(final Object owner,
			final String resource) {
		final String dir = owner.getClass().getPackage().getName()
				.replace(".", "/");
		final String ownerName = owner.getClass().getSimpleName();
		final String resourceName = String.format("%s/%s_%s", dir, ownerName,
				resource);
		log.info("Classpath lookup: {}", resourceName);
		final URL url = owner.getClass().getClassLoader()
				.getResource(resourceName);
		if (url == null) {
			log.info("Resource not found: {}", resourceName);
			return null;
		}
		try {
			return url.openStream();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String asString(final Object owner, final String resource) {
		try (InputStream is = asInputStream(owner, resource);
				Scanner scanner = new Scanner(is, "UTF-8")) {
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
