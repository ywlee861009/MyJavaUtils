
public class ImageUtil {
    // getCroppedBitmap() 에서 사용할 비트맵 crop 방향
    public static final int DIRECTION_TOP = 1;
    public static final int DIRECTION_BOTTOM = 2;
    public static final int DIRECTION_RIGHT = 3;
    public static final int DIRECTION_LEFT = 4;
    
    
    /**
     * 원본 bitmap을 받아, direction(방향) 을 px 만큼 잘라서 리턴
     *
     *
     * @param origin 원본 비트맵
     * @param direction 자를 방향
     * @param px 자를 px
     *
     * @return 크롭된 비트맵
     */
    public static Bitmap getCroppedBitmap(Bitmap origin, int direction, int px) {
        int x = 0;
        int y = 0;
        int width = origin.getWidth();
        int height = origin.getHeight();

        // 상하를 자른다면 height > px 여야하고, 좌우를 자른다면, width < px 여야 한다.
        if ((((direction == DIRECTION_TOP) || (direction == DIRECTION_BOTTOM)) && (origin.getHeight() <= px)) ||
                (((direction == DIRECTION_LEFT) || (direction == DIRECTION_RIGHT)) && (origin.getWidth() <= px)))
            return origin;

        switch (direction) {
            case DIRECTION_TOP:
                // 탑을 px만큼 자름
                y += px;
                height -= px;
                break;

            case DIRECTION_BOTTOM:
                // 바텀을 px 만큼 자름
                height = origin.getHeight() - px;
                break;

            case DIRECTION_LEFT:
                // 왼쪽을 px 만큼 자름
                width = origin.getWidth() - px;
                break;

            case DIRECTION_RIGHT:
                // 오른쪽을 px 만큼 자름
                x += px;
                width -= px;
                break;
        }

        Bitmap result = Bitmap.createBitmap(origin,
                x,
                y,
                width,
                height);

        return result;
    }
}
