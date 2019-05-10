/**
 * View 관련 유틸 클래스
 *
 * @author ywlee
 */
public class ViewUtil {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Id 가 없는 뷰의 ID 를 생성해주는 util class
     * 주로 constraint layout 의 동적 생성을 위해 사용함
     *
     * * API 17버전 이상은 View 의 generateViewId() 를 사용가능하나, 그 이하는 불가능함.
     * 따라서, ID generate 를 수동 로직화 하여 구현,
     * 기존의 aapt2 R id 와 충돌하지 않음
     *
     * @return
     */
    @SuppressLint("NewApi")
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < 17) {
            for (;;) {
                final int result = sNextGeneratedId.get();

                // AAPT 를 통해 생성되는 id 값의 범위보다 낮은 범위내에서 반복하기 때문에 R resource 와 충돌할 일이 없다.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF)
                    newValue = 1;
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }

    /**
     *
     * ConstraintLayout Programmatically 설정
     *
     * @param constraint 부모 컨스트레인트 레이아웃
     * @param childView 컨스트레인트를 적용할 뷰
     * @param topConstraint 적용할 뷰의 탑 이 붙을 대상
     * @param topViewDirection 적용할 뷰의 Top 이 topConstraintView 의 어디에 붙는지
     * @param topMargin top margin
     * @param bottomConstraint 적용할 뷰의 바텀이 붙을 대상
     * @param bottomViewDirection 적용할 뷰의 Bottom 이 bottomConstraintView 의 어디에 붙는지
     * @param bottomMargin bottom margin
     * @param leftConstraint 적용할 뷰의 left 가 붙을 대상
     * @param leftViewDirection 적용할 뷰의 left 이 leftConstraintView 의 어디에 붙는지
     * @param leftMargin left margin
     * @param rightConstraint 적용할 뷰의 right 가 붙을 대상
     * @param rightViewDirection 적용할 뷰의 right 이 rightConstraint 의 어디에 붙는지
     * @param rightMargin right margin
     */
    public static void setConstraint(ConstraintLayout constraint,
                                     View childView,
                                     View topConstraint, int topViewDirection, int topMargin,
                                     View bottomConstraint, int bottomViewDirection, int bottomMargin,
                                     View leftConstraint, int leftViewDirection, int leftMargin,
                                     View rightConstraint, int rightViewDirection, int rightMargin) {

        ConstraintSet set = new ConstraintSet();
        set.clone(constraint);

        if (topConstraint != null) set.connect(childView.getId(), ConstraintSet.TOP, topConstraint.getId(), topViewDirection, topMargin);
        if (bottomConstraint != null) set.connect(childView.getId(), ConstraintSet.BOTTOM, bottomConstraint.getId(), bottomViewDirection, bottomMargin);
        if (leftConstraint != null) set.connect(childView.getId(), ConstraintSet.LEFT, leftConstraint.getId(), leftViewDirection, leftMargin);
        if (rightConstraint != null) set.connect(childView.getId(), ConstraintSet.RIGHT, rightConstraint.getId(), rightViewDirection, rightMargin);

        set.applyTo(constraint);
    }


    /**
     *
     * ConstraintLayout Programmatically 설정
     *
     * @param constraint 부모 컨스트레인트 레이아웃
     * @param childView 컨스트레인트를 적용할 뷰
     * @param topConstraint 적용할 뷰의 탑 이 붙을 대상
     * @param topViewDirection 적용할 뷰의 Top 이 topConstraintView 의 어디에 붙는지
     * @param bottomConstraint 적용할 뷰의 바텀이 붙을 대상
     * @param bottomViewDirection 적용할 뷰의 Bottom 이 bottomConstraintView 의 어디에 붙는지
     * @param leftConstraint 적용할 뷰의 left 가 붙을 대상
     * @param leftViewDirection 적용할 뷰의 left 이 leftConstraintView 의 어디에 붙는지
     * @param rightConstraint 적용할 뷰의 right 가 붙을 대상
     * @param rightViewDirection 적용할 뷰의 right 이 rightConstraint 의 어디에 붙는지
     */
    public static void setConstraint(ConstraintLayout constraint,
                                     View childView,
                                     View topConstraint, int topViewDirection,
                                     View bottomConstraint, int bottomViewDirection,
                                     View leftConstraint, int leftViewDirection,
                                     View rightConstraint, int rightViewDirection) {
        setConstraint(constraint, childView,
                topConstraint, topViewDirection, 0,
                bottomConstraint, bottomViewDirection, 0,
                leftConstraint, leftViewDirection, 0,
                rightConstraint, rightViewDirection, 0);
    }

    /**
     *
     * ConstraintLayout Programmatically 설정
     *
     * @param constraint 부모 컨스트레인트 레이아웃
     * @param childView 컨스트레인트를 적용할 뷰
     * @param topConstraint 적용할 뷰의 Top 이 어떤 뷰의 top 에 붙는지
     * @param bottomConstraint 적용할 뷰의 bottom 이 어떤 뷰의 bottom 에 붙는지
     * @param leftConstraint 적용할 뷰의 left 가 어떤 뷰의 left 에 붙는지
     * @param rightConstraint 적용할 뷰의 right 가 어떤 뷰의 right 에 붙는지
     */
    public static void setSimpleConstraint(ConstraintLayout constraint,
                                     View childView,
                                     View topConstraint,
                                     View bottomConstraint,
                                     View leftConstraint,
                                     View rightConstraint) {
        setConstraint(constraint,
                childView,
                topConstraint, ConstraintSet.TOP,0,
                bottomConstraint, ConstraintSet.BOTTOM, 0,
                leftConstraint, ConstraintSet.LEFT, 0,
                rightConstraint, ConstraintSet.RIGHT, 0);
    }

    /**
     * childView 의 Constraint 설정
     *
     * top anchor 는 topConstraintView 의 Bottom 에,
     * left, right 는 부모뷰에 붙임
     *
     * @param constraint 부모 레이아웃
     * @param childView 컨스트레인트를 적용할 뷰
     * @param topConstraintView childView 위에 있는 뷰
     */
    public static void setConstraintUnderView(ConstraintLayout constraint,
                                              View childView,
                                              View topConstraintView) {
        setConstraint(constraint, childView,
                topConstraintView, ConstraintSet.BOTTOM, 0,
                null, 0, 0,
                constraint, ConstraintSet.LEFT, 0,
                constraint, ConstraintSet.RIGHT, 0);
    }
}
