//package dev.alexnader.fallfest.client;
//
//public class ColorUtil {
//    public static final byte BLUE_SHIFT = 0;
//    public static final byte GREEN_SHIFT = BLUE_SHIFT + 8;
//    public static final byte RED_SHIFT = GREEN_SHIFT + 8;
//    public static final byte ALPHA_SHIFT = RED_SHIFT + 8;
//    public static final byte LIGHT_SHIFT = ALPHA_SHIFT + 8;
//    public static final byte SAT_SHIFT = LIGHT_SHIFT + 8;
//    public static final byte HUE_SHIFT = SAT_SHIFT + 8;
//    public static final byte RGB_INDICATOR_SHIFT = HUE_SHIFT + 8;
//    public static final byte HSL_INDICATOR_SHIFT = RGB_INDICATOR_SHIFT + 1;
//
//    public static final long RGB_PRESENT = 1L << RGB_INDICATOR_SHIFT;
//    public static final long HSL_PRESENT = 1L << HSL_INDICATOR_SHIFT;
//
//    // COLOR FORMAT: 1 long represents one color, storing alpha, RGB, and HSL, and whether a certain representation is stored.
//    // The rest of the long is unused.
//    // The data is packed as such, where the . represents the region used to store the initialzation state of the RGB and HSL data.
//    // .HSLARGB
//
//    public static boolean hasRgb(long color) {
//        return (color & RGB_PRESENT) == 1;
//    }
//
//    public static boolean hasHsl(long color) {
//        return (color & HSL_PRESENT) == 1;
//    }
//
//    public static long fromArgb(int argb) {
//        return argb | RGB_PRESENT;
//    }
//
//    public static long fromHsla(int hsla) {
//        return (hsla << ALPHA_SHIFT) | HSL_PRESENT;
//    }
//
//    public static int toArgb(long color) {
//        if (hasRgb(color)) {
//            return toArgbUnchecked(color);
//        } else {
//            return hslaToArgb(toHslaUnchecked(color));
//        }
//    }
//
//    public static int toArgbUnchecked(long color) {
//        return (int) color;
//    }
//
//    public static int toHsla(long color) {
//        if (hasHsl(color)) {
//            return toHslaUnchecked(color);
//        } else {
//            return argbToHsla(toArgbUnchecked(color));
//        }
//    }
//
//    public static int toHslaUnchecked(long color) {
//        return (int) (color >> ALPHA_SHIFT);
//    }
//
//    public static int toArgbInt(long color) {
//        if (hasRgb(color)) {
//            return (int) color;
//        }
//    }
//}
