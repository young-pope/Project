package com.example.chenwe.mvp.presenter.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenwe on 2020/8/20.
 */

public class WrapperPresenter extends AbstractPresenter {
    private static volatile WrapperPresenter mWrapperPresenter = null;
    /**
     * 存放所有Presenter}
     */
    private Map<String, AbstractPresenter> mMap = new HashMap<>();

    private WrapperPresenter(){

    }

    public static WrapperPresenter getInstance(){
        if (mWrapperPresenter == null){
            synchronized (WrapperPresenter.class){
                if (mWrapperPresenter == null){
                    mWrapperPresenter = new WrapperPresenter();
                }
            }
        }
        return mWrapperPresenter;
    }

    @Override
    Map<String, AbstractPresenter> getPresentsMap() {
        return mMap;
    }

    /**
     * { Presenter}添加到包裹WrapperPresenter中,持有各个presenter引用便于presenter之间通信
     *
     * @param presenters 需要添加到WrapperPresenter中的所有子的Presenter集合
     */
    public void add(Presenter... presenters) {
        if (presenters == null || presenters.length == 0) {
            return;
        }
        for (Presenter presenter : presenters) {
            mMap.put(presenter.getClass().getName(), presenter);
            presenter.initWrapperPresenter(this);
        }
    }

    public void removeAll(){
        Presenter p = null;
        for (AbstractPresenter presenter:mMap.values()){
            ((Presenter)presenter).onDestroy();
            presenter = null;
        }
        mMap.clear();
        mWrapperPresenter = null;
    }

    public void remove(Class presenterClass){
        mMap.remove(presenterClass.getName());
    }

}
