package com.isnotworking.recfwk.util;

/**
 * Misc string utils.
 * 
 * @author ricardocabral
 * 
 */
public class StringUtils {
	public static String slugify(final String title) {
		String slug;

		slug = title.replaceAll("\\s", "_");
		slug = slug.replaceAll("'", "");
		slug = slug.replaceAll("\\p{Punct}", "_");
		slug = slug.replaceAll("_{2,}", "_");
		slug = slug.replaceAll("_", "-");
		slug = slug.replaceAll("^-{1,}", "");
		slug = slug.replaceAll("-{1,}$", "");

		return slug;
	}
}
