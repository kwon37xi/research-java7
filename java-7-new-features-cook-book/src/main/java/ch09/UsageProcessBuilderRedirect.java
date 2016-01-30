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
        System.out.println(pb.redirectInput());
        System.out.println(pb.redirectOutput());
        System.out.println(pb.redirectError());

        pb.redirectInput(commands);
        pb.redirectOutput(output);
        pb.redirectError(errors);

        System.out.println(pb.redirectInput());
        System.out.println(pb.redirectOutput());
        System.out.println(pb.redirectError());

        pb.start();
    }
}
