package com.ja.markdown.modeller.parser.spi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Document;

import com.ja.markdown.modeller.parser.MarkdownParser;
import com.ja.markdown.modeller.parser.ParseException;

@Slf4j
public class SpiMarkdownParser implements MarkdownParser {

	@Override
	public Document parse(final InputStream inputStream, final String charset)
			throws ParseException, IOException {

		final ServiceLoader<MarkdownParser> loader = ServiceLoader
				.load(MarkdownParser.class);

		listParsers(loader);

		final Iterator<MarkdownParser> it = loader.iterator();
		Exception lastException = null;
		while (it.hasNext()) {
			final MarkdownParser parser = it.next();
			log.debug("Trying {} parser.", parser.getClass().getName());
			try {
				return parser.parse(inputStream, charset);
			} catch (IOException | ParseException e) {
				log.debug("Parser {} failed with {}. Will try next parser.",
						parser.getClass().getName(), e.toString());
				lastException = e;
			}
		}
		log.error("No parser was able to parse input. Will rethrow last exeption.");
		if (lastException instanceof IOException) {
			throw (IOException) lastException;
		} else if (lastException instanceof ParseException) {
			throw (ParseException) lastException;
		}
		throw new RuntimeException("Unreachable code", lastException);
	}

	private void listParsers(final ServiceLoader<MarkdownParser> loader) {
		final Iterator<MarkdownParser> it = loader.iterator();
		int count = 0;
		while (it.hasNext()) {
			count++;
			log.info("Found MarkdownParser {}.", it.next().getClass().getName());
		}
		if (count == 0) {
			throw new RuntimeException("No MarkdownParsers configured.");
		}
	}

}
