/**
 * 컨버팅 유틸
 *
 * @author YWLee
 */
public class ConvertUtil {
  

    /**
     * 밀리세컨드를 기준으로 00:00:00 포멧 형태의 시간 String 을 얻어오는 메소드
     *
     * @param milsec long 밀리세컨드
     *
     * @return hh:mm:ss 포멧의 String (hour 가 존재하지 않을 경우 mm:ss)
     */
    public static String milsecToTimeFormat(String milsec) {
        String formattedTimeString = "";

        String divider = ":";

        String hourString = "";
        String minString = "";
        String secString = "";

        try {
            long mil = ConvertUtil.convertToLong(milsec);

            long hour = TimeUnit.MILLISECONDS.toHours(mil);
            long min = TimeUnit.MILLISECONDS.toMinutes(mil) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mil));
            long sec = TimeUnit.MILLISECONDS.toSeconds(mil) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mil));

            hourString = String.format(Locale.getDefault(), "%d", hour);
            minString = String.format(Locale.getDefault(), "%02d", min);
            secString = String.format(Locale.getDefault(), "%02d", sec);

            if (!hourString.startsWith("0"))
                formattedTimeString += hourString + divider;

            formattedTimeString += minString + divider + secString;
        } catch (Exception e) {
            return "00:00";
        }

        return formattedTimeString;
    }
  
    /**
     * Long Type 으로 컨버팅
     *
     * @param value 컨버팅 할 값
     * @param defaultValue 기본 값
     *
     * @return 컨버팅 되거나 기본값 리턴
     */
    public static long convertToLong(Object value, long defaultValue) {
        try {
            if (value == null) return defaultValue;
            if (value instanceof BigDecimal) {
                return ((BigDecimal) value).longValue();

            } else if (value instanceof Integer) {
                return ((Integer) value).longValue();

            } else if (value instanceof Double) {
                return ((Double) value).longValue();

            } else if (value instanceof Float) {
                return ((Float) value).longValue();

            } else if (value instanceof Short) {
                return ((Short) value).longValue();

            } else if (value instanceof BigInteger) {
                return ((BigInteger) value).longValue();

            } else if (value instanceof Byte) {
                return ((Byte) value).longValue();

            } else {
                return Long.parseLong(value.toString());
            }
        } catch (Exception e) {
        }

        return defaultValue;
    }
  
  
    /**
     * Object -> int
     *
     * @param value 컨버팅 할 값
     * @param defaultValue 디폴트 값
     * @return
     */
    public static int convertToInt(Object value, int defaultValue) {
        try {
            if (value == null) return defaultValue;
            if (value instanceof BigDecimal) {
                return ((BigDecimal) value).intValue();

            } else if (value instanceof Integer) {
                return ((Integer) value).intValue();

            } else if (value instanceof Double) {
                return ((Double) value).intValue();

            } else if (value instanceof Long) {
                return ((Long) value).intValue();

            } else if (value instanceof Float) {
                return ((Float) value).intValue();

            } else if (value instanceof Short) {
                return ((Short) value).intValue();

            } else if (value instanceof BigInteger) {
                return ((BigInteger) value).intValue();

            } else if (value instanceof Byte) {
                return ((Byte) value).intValue();

            } else {
                return Integer.parseInt(value.toString());
            }
        } catch (Exception e) {
        }
      
        return defaultValue;
    }
}
