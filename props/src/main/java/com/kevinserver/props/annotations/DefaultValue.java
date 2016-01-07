/**
 * com.kevinserver.props.annotations
 */
package com.kevinserver.props.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * class : DefaultValue.java
 * @author xukai
 * @date 2016年1月7日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DefaultValue {
	String value();
}
