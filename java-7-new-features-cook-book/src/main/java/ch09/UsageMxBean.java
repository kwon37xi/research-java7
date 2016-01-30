package ch09;


import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

public class UsageMxBean {
    public static void main(String[] args) {
        // java.lang.management.PlatformManagedObject 를 상속하는 MXBean을 얻는다.
        RuntimeMXBean mxBean = ManagementFactory.getPlatformMXBean(RuntimeMXBean.class);

        System.out.println("JVM Name: " + mxBean.getName());
        System.out.println("JVM Specification Name: " + mxBean.getSpecName());
        System.out.println("JVM Specification Version: " + mxBean.getSpecVersion());
        System.out.println("JVM Implementation Name: " + mxBean.getVmName());
        System.out.println("JVM Implementation Vendor: " + mxBean.getVmVendor());
        System.out.println("JVM Implementation Version: " + mxBean.getVmVersion());

        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("JVM OS Name: " + operatingSystemMXBean.getName());
        System.out.println("JVM OS Version: " + operatingSystemMXBean.getVersion());
        System.out.println("JVM OS Architecture: " + operatingSystemMXBean.getArch());
    }
}
