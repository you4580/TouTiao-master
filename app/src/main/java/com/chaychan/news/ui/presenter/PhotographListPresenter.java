package com.chaychan.news.ui.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.chaychan.news.api.API;
import com.chaychan.news.model.entity.BBSPostItem;
import com.chaychan.news.model.response.BBSDataResponse;
import com.chaychan.news.ui.base.BasePresenter;
import com.chaychan.news.utils.ParseUtil;
import com.chaychan.news.view.lPhotographListView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Map;

import rx.Subscriber;

/**
 * @author ChayChan
 * @description: 摄影的presenter
 * @date 2017/6/18  10:04
 */

public class PhotographListPresenter extends BasePresenter<lPhotographListView> {

    public PhotographListPresenter(lPhotographListView view) {
        super(view);
    }

    public void getPhotographList(int page){
        addSubscription(mApiService.getPhotographList(page+""), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                KLog.e(e.getLocalizedMessage());
                mView.onError();
            }

            @Override
            public void onNext(String response) {
                if(!TextUtils.isEmpty(response.toString())){
                    Map<String, Object> map = ParseUtil.parserPost(response);
                    if (map != null && !map.isEmpty()) {
                        ArrayList<BBSPostItem> photographList = (ArrayList<BBSPostItem>) map.get("postList");
//                        postDate = (String) map.get("nextDate");
                        KLog.e(photographList);
                        mView.onGetPhotographListSuccess(photographList,"");
                    }
                }
            }
        });
    }
}
