package org.jrocky.kgraph.core.security;
/**
 * This will handle 
 * java.lang.IllegalStateException: The store should be empty when modifying the configuration
 * @author wangzhijie
 *
 */
public interface IGephiCheck {

	boolean check();
}
