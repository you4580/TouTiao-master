package com.chaychan.news.view;

import com.chaychan.news.model.entity.BBSPostItem;

import java.util.List;

/**
 * @author ChayChan
 * @description: 获取各种频道广告的View回调接口
 * @date 2017/6/18  9:33
 */

public interface lPhotographListView {

    void onGetPhotographListSuccess(List<BBSPostItem> photographList, String tipInfo);

    void  onError();
}
