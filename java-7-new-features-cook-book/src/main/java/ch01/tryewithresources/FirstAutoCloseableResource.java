package ch01.tryewithresources;

public class FirstAutoCloseableResource implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("FirstAutoCloseableResource close method executed.");
        throw new UnsupportedOperationException("A problem has occured in FirstAutoCloseableResource");
    }

    public void manipulateResource() {
        System.out.println("FirstAutoCloseableResource manipulateResource method executed.");
    }
}
