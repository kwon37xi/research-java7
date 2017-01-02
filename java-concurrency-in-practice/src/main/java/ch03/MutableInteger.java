package ch03;

import net.jcip.annotations.NotThreadSafe;

// Bad Code
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() { return value; }
    public void set(int value) { this.value = value; }
}
