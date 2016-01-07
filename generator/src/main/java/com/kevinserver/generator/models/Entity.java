/**
 * com.kevinweb.app._tools.prop_gen
 */
package com.kevinserver.generator.models;

import java.util.List;

/**
 * class : Entity.java
 * @author xukai
 * @date 2016年1月6日
 */
public class Entity {
	private String javaPackage;
	private String className;
	private String superClass;
	private List<Property> properties;
	private boolean constructors;
	private String propFile;
	private boolean needDebug;
	
	public String getJavaPackage() {
		return javaPackage;
	}
	public void setJavaPackage(String javaPackage) {
		this.javaPackage = javaPackage;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSuperClass() {
		return superClass;
	}
	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	public boolean isConstructors() {
		return constructors;
	}
	public void setConstructors(boolean constructors) {
		this.constructors = constructors;
	}
	public String getPropFile() {
		return propFile;
	}
	public void setPropFile(String propFile) {
		this.propFile = propFile;
	}
	public boolean isNeedDebug() {
		return needDebug;
	}
	public void setNeedDebug(boolean needDebug) {
		this.needDebug = needDebug;
	}
}
