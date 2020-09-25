package basics;

public class string {
    public static void main(String[] args) {
        String a = "12ab12ab";
        int len = a.length();

        StringBuilder sb = new StringBuilder();

        // substring
        System.out.println(a.substring(0, len));

//        sb.setLength(0);
//        System.out.println(a.substring(0, len+1));

        sb.setLength(0);
        System.out.println(a.substring(0, len-1));

//        sb.setLength(0);
//        System.out.println(a.substring(-1, len-1));

        sb.setLength(0);
        System.out.println(a.substring(0, 3));

    }
}
