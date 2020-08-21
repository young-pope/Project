package com.example.chenwe.mvp.presenter.framework;

import java.util.Map;

/**
 * Created by chenwe on 2020/8/20.
 */

public class Presenter extends AbstractPresenter{

    WrapperPresenter mWrapperPresenter;

    /**
     * 将外层的包裹 引用给被包裹的presenter，目的是获取外层包裹的presenter集合
     *
     * @param wrapperPresenter
     */
    public void initWrapperPresenter(WrapperPresenter wrapperPresenter) {
        mWrapperPresenter = wrapperPresenter;
    }

    public WrapperPresenter getWrapperPresenter() {
        return this.mWrapperPresenter;
    }

    @Override
    Map<String, AbstractPresenter> getPresentsMap() {
        return getWrapperPresenter() != null ? getWrapperPresenter().getPresentsMap() : null;
    }

    public void onDestroy(){

    }
}
