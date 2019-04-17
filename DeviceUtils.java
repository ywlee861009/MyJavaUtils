
/**
 * 기기 관련된 유틸
 *
 * @author YWLee
 */
public class DeviceUtils {
    /**
     * 기기 사이즈를 Point 객체로 리턴
     * 
     * @param c windowmanager 를 얻기 위한 컨텍스트
     * 
     * @return Point 객체
     */
    public static Point getDeviceSize(Context c) {
        WindowManager manager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();

        Point size = new Point();

        display.getSize(size);

        return size;
    }
    
    /**
     * dp를 px로 변환
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dpToPixel(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
