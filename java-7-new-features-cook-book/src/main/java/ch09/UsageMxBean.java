package ch09;


import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class UsageMxBean {
    public static void main(String[] args) {
        RuntimeMXBean mxBean = ManagementFactory.getPlatformMXBean(RuntimeMXBean.class);

        System.out.println("JVM Name: " + mxBean.getName());
        System.out.println("JVM Specification Name: " + mxBean.getSpecName());
        System.out.println("JVM Specification Version: " + mxBean.getSpecVersion());
        System.out.println("JVM Implementation Name: " + mxBean.getVmName());
        System.out.println("JVM Implementation Vendor: " + mxBean.getVmVendor());
        System.out.println("JVM Implementation Version: " + mxBean.getVmVersion());
    }
}
