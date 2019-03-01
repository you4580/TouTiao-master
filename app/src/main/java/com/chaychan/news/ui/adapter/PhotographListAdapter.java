package com.chaychan.news.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.MultipleItemRvAdapter;
import com.chaychan.news.model.entity.BBSPostItem;
import com.chaychan.news.ui.adapter.provider.photograph.PhotographItemProvider;

import java.util.List;

/**
 * @author ChayChan
 * @description: 新闻列表的适配器
 * @date 2018/3/22  11
 */

public class PhotographListAdapter extends MultipleItemRvAdapter<BBSPostItem,BaseViewHolder> {
    /**
     * 摄影布局
     */
    public static final int PHOTOGRAPH_PICS_NEWS = 100;


    public PhotographListAdapter(@Nullable List<BBSPostItem> data) {
        super(data);
        finishInitialize();
    }

    @Override
    protected int getViewType(BBSPostItem news) {
        return PHOTOGRAPH_PICS_NEWS;
    }

    @Override
    public void registerItemProvider() {
        //注册itemProvider
        mProviderDelegate.registerProvider(new PhotographItemProvider());
    }
}
