package com.ja.markdown.modeller.sc.java.model;

public interface ContentProvider {

	String getContent();

	ContentProvider nullContentProvider = new ContentProvider() {

		@Override
		public String getContent() {
			return null;
		}

	};
}
