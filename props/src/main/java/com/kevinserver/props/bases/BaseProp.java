package com.kevinserver.props.bases;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class BaseProp {
	protected Prop p;
	
	public BaseProp(String p) {
		this.p = PropKit.use(p);
	}
	
	public boolean isDebug() {
		if(p.containsKey("isDebug")) {
			return p.getBoolean("isDebug");
		}
		return false;
	}
	private String getDebugKey(String key) {
		if (key.contains("[") && key.contains("]")) {
			String t = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
			String k = getString(t);
			key = key.replace("[" + t + "]", k);
		}
		
		if(isDebug() && !key.startsWith("debug.")) {
			key = "debug." + key;
		}
		return key;
	}
	
	public int getInt(String key) {
		return getInt(key, 0);
	}
	public int getInt(String key, int defVal) {
		String dkey = getDebugKey(key);
		if(p.containsKey(dkey)) {
			return p.getInt(dkey, defVal);
		} else {
			return p.getInt(key, defVal);
		}
	}
	
	public long getLong(String key) {
		return getLong(key, 0L);
	}
	public long getLong(String key, long defVal) {
		String dkey = getDebugKey(key);
		if(p.containsKey(dkey)) {
			return p.getLong(dkey, defVal);
		} else {
			return p.getLong(key, defVal);
		}
	}
	
	public float getFloat(String key) {
		return getFloat(key, 0f);
	}
	public float getFloat(String key, float defVal) {
		String v = getString(key, null);
		if (StringUtils.isEmpty(v)) {
			return defVal;
		}
		return NumberUtils.toFloat(v, defVal);
	}
	
	public double getDouble(String key) {
		return getDouble(key, 0d);
	}
	public double getDouble(String key, double defVal) {
		String v = getString(key, null);
		if (StringUtils.isEmpty(v)) {
			return defVal;
		}
		return NumberUtils.toDouble(v, defVal);
	}
	
	public String getString(String key) {
		return getString(key, "");
	}
	public String getString(String key, String defVal) {
		String dkey = getDebugKey(key);
		if(p.containsKey(dkey)) {
			return p.get(dkey, defVal);
		} else {
			return p.get(key, defVal);
		}
	}
	
	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}
	public boolean getBoolean(String key, boolean defVal) {
		String dkey = getDebugKey(key);
		if(p.containsKey(dkey)) {
			return p.getBoolean(dkey, defVal);
		} else {
			return p.getBoolean(key, defVal);
		}
	}
}
