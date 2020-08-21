package com.example.chenwe.mvp.presenter.framework;

import java.util.Map;

/**
 * Created by chenwe on 2020/8/20.
 */

public abstract class AbstractPresenter {
    /**
     * 外层presenter 下包裹的所有presenter的集合
     *
     * @return presenter的集合
     */
    abstract Map<String, AbstractPresenter> getPresentsMap();

    /**
     * 根据具体 的类型获取对应的实例
     *
     * @param presenterClass presenter类型
     * @param <T> 限制获取实例类型
     * @return 返回对应类型的实例
     */
    public synchronized <T extends Presenter> T getPresenter(Class<T> presenterClass) {
        T presenter = (T) getPresentsMap().get(presenterClass.getName());
        return presenter;
    }
}
