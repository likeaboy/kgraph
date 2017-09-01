package org.jrocky.kgraph.core;

import org.gephi.graph.api.Configuration;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.impl.GraphModelImpl;
import org.jrocky.kgraph.core.policy.IPreprocessPolicy;
import org.jrocky.kgraph.internal.KGraphRenderImpl;

/**
 * 知识图谱渲染
 * @author wangzhijie
 *
 */
public interface IKGraphRender {
	
	 /**
     * Utility to create new kwonledge graph render instances.
     */
    public static class Factory {

        /**
         * Returns a new instance with default configuration.
         *
         * @return new instance
         */
        public static IKGraphRender newInstance() {
            return new KGraphRenderImpl();
        }

        /**
         * Returns a new instance with the given configuration.
         *
         * @param config configuration
         * @return new instance
         */
        public static IKGraphRender newInstance(IPreprocessPolicy preprocessPolicy) {
            return new KGraphRenderImpl(preprocessPolicy);
        }
    }

	boolean rendering(KGraphModel kgModel);
	
}
