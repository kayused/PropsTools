/**
 * com.kevinserver.props.bases
 */
package com.kevinserver.props.bases;

import org.apache.log4j.Logger;

/**
 * class : LogKit.java
 * @author xukai
 * @date 2016年1月7日
 * From JFinal LogKit 2.1
 */
public class LogKit {
	private static class Holder {
		private static Logger log = Logger.getLogger(LogKit.class);
	}
	
	/**
	 * 当通过 Constants.setLogFactory(...) 或者 
	 * LogManager.me().setDefaultLogFacotyr(...)
	 * 指定默认日志工厂以后，重置一下内部 Log 对象，以便使内部日志实现与系统保持一致
	 */
	public static void synchronizeLog() {
		Holder.log = Logger.getLogger(LogKit.class);
	}
	
	/**
	 * Do nothing.
	 */
	public static void logNothing(Throwable t) {
		
	}
	
	public static void debug(String message) {
		Holder.log.debug(message);
	}
	
	public static void debug(String message, Throwable t) {
		Holder.log.debug(message, t);
	}
	
	public static void info(String message) {
		Holder.log.info(message);
	}
	
	public static void info(String message, Throwable t) {
		Holder.log.info(message, t);
	}
	
	public static void warn(String message) {
		Holder.log.warn(message);
	}
	
	public static void warn(String message, Throwable t) {
		Holder.log.warn(message, t);
	}
	
	public static void error(String message) {
		Holder.log.error(message);
	}
	
	public static void error(String message, Throwable t) {
		Holder.log.error(message, t);
	}
	
	public static void fatal(String message) {
		Holder.log.fatal(message);
	}
	
	public static void fatal(String message, Throwable t) {
		Holder.log.fatal(message, t);
	}
	
	public static boolean isDebugEnabled() {
		return Holder.log.isDebugEnabled();
	}
	
	public static boolean isInfoEnabled() {
		return Holder.log.isInfoEnabled();
	}
}
