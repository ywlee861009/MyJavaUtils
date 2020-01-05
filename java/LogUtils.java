
/**
 * 로그 유틸 Android.util.log 의 기본 기능에
 * 로그가 남은 곳이 라인, 호출 클래스 등을 표시해주는 로깅 클래스
 *
 * @author YWLee
 */
public class LogUtils {
    private static final String TAG = "OTAQ";
    private static final String FORMAT = "[%s] : ====> %s ";

    public static void e(String msg) {
        if (!BuildConfig.DEBUG) return;

        Log.e(TAG, String.format(FORMAT, getCallerInfo(), msg));
    }


    /**
     *
     *
     * @return
     */
    private static String getCallerInfo() {
        try {
            StackTraceElement[] elements = new Exception().getStackTrace();
            String className = elements[2].getClassName();

            return className.substring(className.lastIndexOf(".") + 1, className.length()) +
                    "_" +
                    elements[2].getLineNumber();;
        } catch (Exception e) {
            return "";
        }
    }
}
