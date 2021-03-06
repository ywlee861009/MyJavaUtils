
/**
 * 기기 관련된 유틸
 *
 * @author YWLee
 */
public class DeviceUtils {
    
    /**
     * 기기 스크린 사이즈 반환 (large, small, xlarge..)
     */
    public static String getScreenLayoutSize(Context c) {
        String screenSize = "";
        if ((c.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
            screenSize = "Large";
        else if ((c.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL)
            screenSize = "Small";
        else if ((c.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL)
            screenSize = "Normal";
        else if ((c.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            screenSize = "Xlarge";
        else
            screenSize = "none";    
    }
    
    
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
    
    /**
     * px를 dp로 변환
     *
     * @param context
     * @param px
     * @return
     */
    public static int pxToDp(Context context, int px) {
        int dp = (int) (px / context.getResources().getDisplayMetrics().density);
        return dp;
    }
    
}
