/**
 * com.kevinweb.app._tools.prop_gen
 */
package com.kevinserver.generator.models;

/**
 * class : Property.java
 * @author xukai
 * @date 2016年1月6日
 */
public class Property {
	private String javaType;
	private String propertyName;
	private boolean string;
	private String defVal;
	private String annotationName;
	private String notes;
	
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getDefVal() {
		return defVal;
	}
	public void setDefVal(String defVal) {
		this.defVal = defVal;
	}
	public boolean isString() {
		return string;
	}
	public void setString(boolean string) {
		this.string = string;
	}
	public String getAnnotationName() {
		return annotationName;
	}
	public void setAnnotationName(String annotationName) {
		this.annotationName = annotationName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
