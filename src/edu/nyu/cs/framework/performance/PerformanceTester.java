package edu.nyu.cs.framework.performance;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author shenli
 * <p>
 * Applies {@link edu.nyu.cs.framework.performance.PerformanceTestContainer} objects to lists of different containers.
 */
public class PerformanceTester<T> {
    private static final Logger LOGGER = Logger.getLogger("edu.nyu.cs.framework.performance.PerformanceTester");
    
    private static final int FIELD_WIDTH = 8;
    private static final int SIZE_WIDTH = 5;
    private static final PerformanceParam[] DEFAULT_PARAMS = 
            PerformanceParam.array(10, 5000, 100, 5000, 1000, 5000, 10000, 500);
    private String headline = "";
    private List<PerformanceTestContainer<T>> testContainers;
    private PerformanceParam[] paramList = DEFAULT_PARAMS;
    protected T container;
    
    public PerformanceTester(T container, List<PerformanceTestContainer<T>> testContainers) {
        this.container = container;
        this.testContainers = testContainers;
    }
    
    public PerformanceTester(T container, List<PerformanceTestContainer<T>> testContainers, PerformanceParam[] paramList) {
        this(container, testContainers);
        this.paramList = paramList;
    }
    
    public void setHeadLine(String headLine) {
        this.headline = headLine;
    }
    
    public static <T> void run(T container, List<PerformanceTestContainer<T>> testContainers) {
        new PerformanceTester<T>(container, testContainers).timedTest();
    }
    
    public static <T> void run(T container, List<PerformanceTestContainer<T>> testContainers, PerformanceParam[] paramList) {
        new PerformanceTester<T>(container, testContainers, paramList).timedTest();
    }
    
    protected T initialize(int size) {
        return container;
    }
    
    private void timedTest() {
        displayHeader();
        for (PerformanceParam param : paramList) {
            LOGGER.info(Integer.toString(param.getSize()));
            for (PerformanceTestContainer<T> testContainer : testContainers) {
                T container = initialize(param.getSize());
                long start = System.nanoTime();
                int reps = testContainer.test(container, param);
                long duration = System.nanoTime() - start;
                long timePerRep = duration / reps;
                LOGGER.info(Long.toString(timePerRep));
            }
        }
    }
    
    private void displayHeader() {
        int width = FIELD_WIDTH * testContainers.size() + SIZE_WIDTH;
        int dashLength = width - headline.length() - 1;
        StringBuilder head = new StringBuilder(width);
        for (int i = 0; i < dashLength / 2; i++) {
            head.append('-');
        }
        head.append(' ' + headline + ' ');
        for (int i = 0; i < dashLength / 2; i++) {
            head.append('-');
        }
        LOGGER.info(head.toString());
        LOGGER.info("size");
        for (PerformanceTestContainer<T> testContainer: testContainers) {
            LOGGER.info(testContainer.name);
        }
    }
    
}
