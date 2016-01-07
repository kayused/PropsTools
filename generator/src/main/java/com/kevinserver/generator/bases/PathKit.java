/**
 * com.kevinserver.generator.bases
 */
package com.kevinserver.generator.bases;

import java.io.File;

/**
 * class : PathKit.java
 * @author xukai
 * @date 2016年1月7日
 * From JFinal 2.1
 */
public class PathKit {
	private static String rootPath;
	
	public static String getRootPath() {
		if (rootPath == null) {
			rootPath = detectRootPath();
		}
		return rootPath;
	}
	
	private static String detectRootPath() {
		try {
			String path = PathKit.class.getResource("/").toURI().getPath();
			return new File(path).getParentFile().getParentFile().getCanonicalPath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
