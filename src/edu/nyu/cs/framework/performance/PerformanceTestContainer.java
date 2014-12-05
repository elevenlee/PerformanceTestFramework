package edu.nyu.cs.framework.performance;

/**
 * @author shenli
 * <p>
 * Framework for performing timed tests of containers.
 */
public abstract class PerformanceTestContainer<T> {
    protected String name;
    
    public PerformanceTestContainer(String name) {
        this.name = name;
    }
    
    abstract int test(T container, PerformanceParam param);
    
}
