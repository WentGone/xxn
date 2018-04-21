package cn.mdruby.baselibrary.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import cn.mdruby.baselibrary.R;


/**
 * Created by Went_Gone on 2018/1/15.
 */

public class ExpandView extends FrameLayout {

    private Animation mExpandAnimation;
    private Animation mCollapseAnimation;
    private boolean mIsExpand ;
    private int contentViewId = 0;

    public ExpandView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    public ExpandView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        // TODO Auto-generated constructor stub
    }
    public ExpandView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initAttrs(context,attrs);

        initExpandView();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.ExpandView);
        mIsExpand = array.getBoolean(R.styleable.ExpandView_isExpand,false);
        array.recycle();
    }

/*
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }*/

    private void initExpandView() {
        int childCount = getChildCount();
        if (childCount==0){
//            LayoutInflater.from(getContext()).inflate(contentViewId > 0?contentViewId:R.layout.layout_expand, this, true);
        }

        mExpandAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.expand);
        mExpandAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                setVisibility(View.VISIBLE);
            }
        });

        mCollapseAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.collapse);
        mCollapseAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(View.GONE);
            }
        });
        if (mIsExpand){
            mIsExpand = !mIsExpand;
            expand();
        }else {
            mIsExpand = !mIsExpand;
            collapse();
        }
    }
    public void collapse() {
        if (mIsExpand) {
            mIsExpand = false;
            clearAnimation();
            startAnimation(mCollapseAnimation);
        }
    }

    public void expand() {
        if (!mIsExpand) {
            mIsExpand = true;
            clearAnimation();
            startAnimation(mExpandAnimation);
        }
    }

    public boolean isExpand() {
        return mIsExpand;
    }

    public void setContentView(){
        View view = null;
//        view = LayoutInflater.from(getContext()).inflate(contentViewId > 0?contentViewId:R.layout.layout_expand, null);
        removeAllViews();
//        addView(view);
    }

    public void setContentViewId(int contentViewId) {
        this.contentViewId = contentViewId;
        setContentView();
    }
}
