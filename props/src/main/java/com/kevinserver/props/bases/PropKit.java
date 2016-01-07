/**
 * com.kevinserver.props.bases
 */
package com.kevinserver.props.bases;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class : PropKit.java
 * @author xukai
 * @date 2016年1月7日
 * PropKit. PropKit can load properties file from CLASSPATH or File object.
 * From JFinal 2.1
 */
public class PropKit {
	private static final ConcurrentHashMap<String, Prop> map = new ConcurrentHashMap<>();
	
	private PropKit() {
	}
	
	/**
	 * Using the properties file. It will loading the properties file if not loading.
	 * @see #use(String, String)
	 */
	public static Prop use(String fileName) {
		return use(fileName, Constacts.DEFAULT_ENCODING);
	}
	
	/**
	 * Using the properties file. It will loading the properties file if not loading.
	 * <p>
	 * Example:<br>
	 * PropKit.use("config.txt", "UTF-8");<br>
	 * PropKit.use("other_config.txt", "UTF-8");<br><br>
	 * String userName = PropKit.get("userName");<br>
	 * String password = PropKit.get("password");<br><br>
	 * 
	 * userName = PropKit.use("other_config.txt").get("userName");<br>
	 * password = PropKit.use("other_config.txt").get("password");<br><br>
	 * 
	 * PropKit.use("com/jfinal/config_in_sub_directory_of_classpath.txt");
	 * 
	 * @param fileName the properties file's name in classpath or the sub directory of classpath
	 * @param encoding the encoding
	 */
	public static Prop use(String fileName, String encoding) {
		Prop result = map.get(fileName);
		if (result == null) {
			result = new Prop(fileName, encoding);
			map.put(fileName, result);
		}
		return result;
	}
	
	/**
	 * Using the properties file bye File object. It will loading the properties file if not loading.
	 * @see #use(File, String)
	 */
	public static Prop use(File file) {
		return use(file, Constacts.DEFAULT_ENCODING);
	}
	
	/**
	 * Using the properties file bye File object. It will loading the properties file if not loading.
	 * <p>
	 * Example:<br>
	 * PropKit.use(new File("/var/config/my_config.txt"), "UTF-8");<br>
	 * Strig userName = PropKit.use("my_config.txt").get("userName");
	 * 
	 * @param file the properties File object
	 * @param encoding the encoding
	 */
	public static Prop use(File file, String encoding) {
		Prop result = map.get(file.getName());
		if (result == null) {
			result = new Prop(file, encoding);
			map.put(file.getName(), result);
		}
		return result;
	}
	
	public static Prop useless(String fileName) {
		Prop previous = map.remove(fileName);
		return previous;
	}
	
	public static Prop refresh(String fileName) {
		useless(fileName);
		return use(fileName);
	}
	
	public static void clear() {
		map.clear();
	}
	
	public static Prop getProp(String fileName) {
		return map.get(fileName);
	}
}
