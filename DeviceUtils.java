
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
}
