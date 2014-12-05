package edu.nyu.cs.framework.performance;

/**
 * @author shenli
 * <p>
 * A data transfer object.
 */
final class PerformanceParam {
    private final int size;
    private final int loops;
    
    PerformanceParam(int size, int loops) {
        this.size = size;
        this.loops = loops;
    }

    int getSize() {
        return size;
    }

    int getLoops() {
        return loops;
    }
    
    static PerformanceParam[] array(int...values) {
        int size = values.length / 2;
        PerformanceParam[] result = new PerformanceParam[size];
        for (int i = 0, n = 0; i < size; i++, n++) {
            result[i] = new PerformanceParam(values[n], values[n]);
        }
        return result;
    }
    
    static PerformanceParam[] array(String...values) {
        int[] vals = new int[values.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.decode(values[i]);
        }
        return array(vals);
    }
    
}
