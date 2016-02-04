package ch11;

public class UsageUnicode {
    public static void main(String[] args) {
        int codePoint = Character.codePointAt("朝鲜圆", 0);
        System.out.println("isBmpCodePoint: " + Character.isBmpCodePoint(codePoint));
        System.out.println("isSurrogate: " + Character.isSurrogate('朝')); // == false. 이 경우, highSurrogate와 lowSurrogate 값은 무의미함.
        System.out.println("highSurrogate: " + (int)Character.highSurrogate(codePoint));
        System.out.println("lowSurrogate: " + (int)Character.lowSurrogate(codePoint));
        System.out.println("isAlphabetic: " + Character.isAlphabetic(codePoint));
        System.out.println("isIdeographic: " + Character.isIdeographic(codePoint));
        System.out.println("getName: " + Character.getName(codePoint));
    }
}
