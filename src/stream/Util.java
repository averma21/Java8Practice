package stream;

class Util {
    static void print(int [] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int t : arr) {
            sb.append(t).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1).append("]");
        System.out.print(sb);
    }

    static void print(int a) {
        System.out.print(a + ", ");
    }
}
