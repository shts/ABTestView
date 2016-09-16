package jp.shts.android.library.abtestview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ABTestView extends View {

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
        int FIX = 0;
        int FLOW = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Which {
        int UNKNOWN = -10;
        int A = 10;
        int B = 11;
    }

    private int inflatedId;

    @LayoutRes
    private int aId, bId;

    @Mode
    private int mode = Mode.FLOW;

    @Which
    private int which = Which.UNKNOWN;

    private String unique;

    public ABTestView(Context context) {
        this(context, null);
    }

    public ABTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ABTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ABTestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        unique = getClass().getSimpleName();

        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ABTestView, defStyleAttr, defStyleRes);

        aId = a.getResourceId(R.styleable.ABTestView_layoutA, NO_ID);
        bId = a.getResourceId(R.styleable.ABTestView_layoutB, NO_ID);
        inflatedId = a.getResourceId(R.styleable.ABTestView_inflatedId, NO_ID);

        mode = a.getInteger(R.styleable.ABTestView_mode, Mode.FLOW);

        a.recycle();
    }

    @Nullable
    public View inflate() {
        View w = whichView();
        setupView(w);
        return w;
    }

    private void setupView(View view) {
        final ViewGroup parent = (ViewGroup) getParent();
        final int index = parent.indexOfChild(this);
        parent.removeViewInLayout(this);

        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            parent.addView(view, index, layoutParams);
        } else {
            parent.addView(view, index);
        }
    }

    protected View whichView() {
        final ViewParent viewParent = getParent();
        if (viewParent != null && viewParent instanceof ViewGroup) {
            @LayoutRes final int w = whichLayout();
            if (w != 0) {
                View v = LayoutInflater.from(getContext()).inflate(whichLayout(), (ViewGroup) viewParent, false);
                if (inflatedId != NO_ID) v.setId(inflatedId);
                return v;
            } else {
                throw new IllegalArgumentException("ABTestView must have a valid layoutResource");
            }
        } else {
            throw new IllegalStateException("ABTestView must have a non-null ViewGroup viewParent");
        }
    }

    @Which
    public int getWhich() {
        if (mode == Mode.FIX) {
            return Store.get(getContext(), unique);
        }
        return which;
    }

    @LayoutRes
    protected int whichLayout() {
        if (mode == Mode.FIX) {
            if (Store.get(getContext(), unique) == Which.UNKNOWN) {
                Store.set(getContext(), unique, which());
            }
            return Store.get(getContext(), unique) == Which.A ? aId : bId;
        }
        which = which();
        return which == Which.A ? aId : bId;
    }

    @Which
    protected int which() {
        return random();
    }

    @Which
    private int random() {
        return (int) (Math.random() * 10) % 2 == 0 ? Which.A : Which.B;
    }
}
