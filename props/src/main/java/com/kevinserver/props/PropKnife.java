package com.kevinserver.props;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.kevinserver.props.annotations.DefaultValue;
import com.kevinserver.props.annotations.PropBind;
import com.kevinserver.props.bases.BaseProp;


public class PropKnife {
	private static Logger LOG = Logger.getLogger(PropKnife.class);
	private static boolean debug = false;
	
	public static void debug(boolean debug) {
		PropKnife.debug = debug;
	}
	
	public static void bind(Class<?> targetClass, String file)
			throws IllegalArgumentException, IllegalAccessException {
		if (debug) {
			LOG.info("Looking up prop for " + targetClass.getName());
		}
		BaseProp prop = new BaseProp(file);
		Field[] fields = targetClass.getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(PropBind.class)) {
				PropBind b = field.getAnnotation(PropBind.class);
				if (b != null) {
					String key = b.value();
					Class<?> type = field.getType();
					DefaultValue defVal = field.getAnnotation(DefaultValue.class);
					Object dv = null;
					if (defVal == null) {
						dv = field.get(targetClass);
					} else {
						dv = defVal.value();
					}
					
					if(type == int.class || type == Integer.class) {
						field.setInt(targetClass, prop.getInt(key, Integer.valueOf(String.valueOf(dv))));
					} else if(type == long.class || type == Long.class) {
						field.setLong(targetClass, prop.getLong(key, Long.valueOf(String.valueOf(dv))));
					} else if(type == boolean.class || type == Boolean.class) {
						field.setBoolean(targetClass, prop.getBoolean(key, Boolean.valueOf(String.valueOf(dv))));
					} else if(type == float.class || type == Float.class) {
						field.setFloat(targetClass, prop.getFloat(key, Float.valueOf(String.valueOf(dv))));
					} else if(type == double.class || type == Double.class) {
						field.setDouble(targetClass, prop.getDouble(key, Double.valueOf(String.valueOf(dv))));
					} else {
						field.set(targetClass, prop.getString(key, String.valueOf(dv)));
					}
					
				}
			}
		}
	}
	
}
