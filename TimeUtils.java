public class TimeUtils {
    /**
     * 밀리세컨드를 기준으로 00:00:00 포멧 형태의 시간 String 을 얻어오는 메소드
     *
     * @param milsec long 밀리세컨드
     *
     * @return 00:00:00 포멧의 String
     */
    public static String milsecToTimeFormat(String milsec) {
        String formattedTimeString = "";

        String divider = ":";

        String hourString = "";
        String minString = "";
        String secString = "";

        try {
            long mil = Long.parseLong(milsec) / 1000;

            long seconds = mil % 60;
            long minutes = (mil / 60) % 60;
            long hours = (mil / (60 * 60)) % 24;

            hourString = String.format(Locale.getDefault(), "%d", hours);
            minString = String.format(Locale.getDefault(), "%02d", minutes);
            secString = String.format(Locale.getDefault(), "%02d", seconds);

            if (!hourString.startsWith("0"))
                formattedTimeString += hourString + divider;

            formattedTimeString += minString + divider + secString;
        } catch (Exception e) {
            // TODO ywlee implements catch logic
            return "";
        }

        return formattedTimeString;
    }
}
