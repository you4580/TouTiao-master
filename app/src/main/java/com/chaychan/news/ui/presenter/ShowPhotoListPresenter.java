package com.chaychan.news.ui.presenter;

import android.text.TextUtils;

import com.chaychan.news.api.SubscriberCallBack;
import com.chaychan.news.constants.Constant;
import com.chaychan.news.model.entity.NewsDetail;
import com.chaychan.news.model.entity.ShowPhotoItem;
import com.chaychan.news.model.response.CommentResponse;
import com.chaychan.news.ui.base.BasePresenter;
import com.chaychan.news.utils.ParseUtil;
import com.chaychan.news.view.INewsDetailView;
import com.chaychan.news.view.lShowPhotoListView;
import com.socks.library.KLog;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * @author ChayChan
 * @description: 新闻详情获取数据的presenter
 * @date 2017/6/28  15:42
 */

public class ShowPhotoListPresenter extends BasePresenter<lShowPhotoListView> {

    public ShowPhotoListPresenter(lShowPhotoListView view) {
        super(view);
    }

    public void getShowPhotoList(String bbs, String boardid,String bookid) {
        addSubscription(mApiService.getPhotographList(bbs, boardid, bookid, "800"), new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
               e.printStackTrace();
                KLog.e(e.getLocalizedMessage());
                mView.onError();
            }

            @Override
            public void onNext(String response) {
                if(!TextUtils.isEmpty(response.toString())){
                    ArrayList<ShowPhotoItem> showPhotoItemList= ParseUtil.parserBbsPicList(response);
                    if (showPhotoItemList != null && showPhotoItemList.size()>0) {
                        mView.onGetShowPhotoListSuccess(showPhotoItemList,"");
                    }
                }
            }

        });
    }
}
