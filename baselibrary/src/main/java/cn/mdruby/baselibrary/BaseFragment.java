package cn.mdruby.baselibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Went_Gone on 2017/9/9.
 */

public abstract class BaseFragment extends Fragment implements ViewListener{
    protected static final String TAG = "BaseFragment";
    protected Context mContext;
    protected int page = 1;
    protected int pageSize = 15;
    protected int refreshTime = 300;
    private static final String STATE_SAVE_IS_HIDDEN = "state_save_is_hidden";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        mContext = getActivity();
        initBeforeDatas();
        initViews(view);
        setListeners();
        return view;
    }


    @Override
    public void initViews() {
    }

    public abstract void initViews(View view);
}
