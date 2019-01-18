package com.github.dnbn.submerge.api.utils;


import com.github.dnbn.submerge.api.parser.exception.InvalidColorCode;


public final class ColorUtils {

    /**
     * Convert the hexadecimal color code to BGR code
     *
     * @param hex
     * @return
     */
    public static int hexToRGB(String hex) {
        // TODO: 2019/1/4 revert alpha
        System.out.println("hex = [" + hex + "]");
//        int in = Color.parseColor(hex);
//        int red = (in) & 0xFF;
//        int green = (in >> 8) & 0xFF;
//        int blue = (in >> 16) & 0xFF;
//        return (red << 16) | (blue) | (green << 8);
        return 0;
    }

    /**
     * Convert a &HAABBGGRR to hexadecimal
     *
     * @param haabbggrr: the color code
     * @return the hexadecimal code
     * @throws InvalidColorCode
     */
    public static String HAABBGGRRToHex(String haabbggrr) {
        if (haabbggrr.length() != 10) {
            throw new InvalidColorCode("Invalid pattern, must be &HAABBGGRR");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(haabbggrr.substring(8));
        sb.append(haabbggrr, 6, 8);
        sb.append(haabbggrr, 4, 6);
        sb.append(haabbggrr, 2, 4);
        return sb.toString().toLowerCase();
    }

    /**
     * Convert a &HBBGGRR to hexadecimal
     *
     * @param hbbggrr: the color code
     * @return the hexadecimal code
     * @throws InvalidColorCode
     */
    public static String HBBGGRRToHex(String hbbggrr) {
        if (hbbggrr.length() != 8) {
            throw new InvalidColorCode("Invalid pattern, must be &HBBGGRR");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(hbbggrr.substring(6));
        sb.append(hbbggrr, 4, 6);
        sb.append(hbbggrr, 2, 4);
        return sb.toString().toLowerCase();
    }

    /**
     * Convert a &HAABBGGRR to BGR
     *
     * @param haabbggrr: the color code
     * @return the BGR code
     * @throws InvalidColorCode
     */
    public static int HAABBGGRRToRGB(String haabbggrr) {
        return hexToRGB(HAABBGGRRToHex(haabbggrr));
    }

    /**
     * Convert a &HBBGGRR to BGR
     *
     * @param hbbggrr: the color code
     * @return the BGR code
     * @throws InvalidColorCode
     */
    public static int HBBGGRRToRGB(String hbbggrr) {
        return hexToRGB(HBBGGRRToHex(hbbggrr));
    }

}
