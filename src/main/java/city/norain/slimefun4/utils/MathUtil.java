package city.norain.slimefun4.utils;

public class MathUtil {
    public static int saturatedAdd(int x, int y) {
        int r = x + y;
        if (((r ^ x) & (r ^ y)) < 0) {
            return x < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else {
            return r;
        }
    }
}
