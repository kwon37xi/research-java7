package ch01.tryewithresources;

public class SecondAutoCloseableResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("SecondAutoCloseableResource close method executed.");
        throw new UnsupportedOperationException("A problem has occured in SecondAutoCloseableResource");
    }

    public void manipulateResource() {
        System.out.println("SecondAutoCloseableResource manipulateResource method executed.");
    }
}
