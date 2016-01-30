package ch09;

import java.io.File;
import java.io.IOException;

/**
 * ProcessBuilder로 프로세스 생성시 input/output/error 를 명시적으로 리다이렉트 시킬 수 있다.
 */
public class UsageProcessBuilderRedirect {
    public static void main(String[] args) throws IOException {
        File commands = new File("java-7-new-features-cook-book/src/main/resources/ProcessCommands.txt");
        File output = new File("/tmp/ProcessLog.txt");
        File errors = new File("/tmp/ErrorLog.txt");

        ProcessBuilder pb = new ProcessBuilder("/bin/sh");
        System.out.println(pb.redirectInput()); // default Redirect.PIPE
        System.out.println(pb.redirectOutput()); // default Redirect.PIPE
        System.out.println(pb.redirectError()); // default Redirect.PIPE

        pb.redirectInput(commands);
        pb.redirectOutput(output);
        pb.redirectError(ProcessBuilder.Redirect.appendTo(errors));

        System.out.println(pb.redirectInput());
        System.out.println(pb.redirectOutput());
        System.out.println(pb.redirectError());

        pb.start();

        // bp.inheritIO() 모든 리다이렉트를  Redirect.INHERIT 으로 지정하는것과 같다.
    }
}
