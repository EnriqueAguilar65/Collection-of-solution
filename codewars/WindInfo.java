package codewars;

/**
 * Created by Marco on 1/20/2015.
 */
public class WindInfo {
    public static String message(String rwy, int windDirection, int windSpeed) {
        int rwyHeadingDeg = Integer.valueOf(rwy.substring(0, 2)) * 10;
        double windAngleRad = (windDirection - rwyHeadingDeg) * Math.PI / 180;

        int crossWind = (int) Math.round(Math.sin(windAngleRad) * windSpeed);
        int headWind = (int) Math.round(Math.cos(windAngleRad) * windSpeed);

        String transverse = crossWind < 0 ? "left" : "right";
        String longitudinal = headWind >= 0 ? "Head" : "Tail";

        return longitudinal + "wind " + Math.abs(headWind) + " knots. Crosswind " + Math.abs(crossWind) + " knots from your " + transverse + ".";
    }
}
