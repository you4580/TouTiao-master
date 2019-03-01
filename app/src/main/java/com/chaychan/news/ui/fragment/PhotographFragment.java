package com.chaychan.news.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chaychan.news.R;
import com.chaychan.news.model.entity.BBSPostItem;
import com.chaychan.news.ui.adapter.PhotographListAdapter;
import com.chaychan.news.ui.base.BaseFragment;
import com.chaychan.news.ui.presenter.PhotographListPresenter;
import com.chaychan.news.utils.ListUtils;
import com.chaychan.news.utils.NetWorkUtils;
import com.chaychan.news.utils.UIUtils;
import com.chaychan.news.view.lPhotographListView;
import com.chaychan.uikit.TipView;
import com.chaychan.uikit.powerfulrecyclerview.PowerfulRecyclerView;
import com.chaychan.uikit.refreshlayout.BGANormalRefreshViewHolder;
import com.chaychan.uikit.refreshlayout.BGARefreshLayout;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author ChayChan
 * @description: 摄影fragment
 * @date 2017/6/12  21:47
 */
public class PhotographFragment extends BaseFragment<PhotographListPresenter> implements lPhotographListView, BGARefreshLayout.BGARefreshLayoutDelegate, BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = com.chaychan.news.ui.fragment.PhotographFragment.class.getSimpleName();
    @Bind(R.id.tip_view)
    TipView mTipView;

    @Bind(R.id.refresh_layout)
    BGARefreshLayout mRefreshLayout;

    @Bind(R.id.fl_content)
    FrameLayout mFlContent;

    @Bind(R.id.rv_news)
    PowerfulRecyclerView mRvNews;


    private ArrayList<BBSPostItem> mPhotographList = new ArrayList<>();
    protected BaseQuickAdapter mPhotographAdapter;

    private int currentPage = 1;

    @Override
    protected PhotographListPresenter createPresenter() {
        return new PhotographListPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_photograph_list;
    }


    @Override
    public View getStateViewRoot() {
        return mFlContent;
    }

    @Override
    public void initView(View rootView) {
        mRefreshLayout.setDelegate(this);
        mRvNews.setLayoutManager(new GridLayoutManager(mActivity, 1));
        // 设置下拉刷新和上拉加载更多的风格,参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText(UIUtils.getString(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText(UIUtils.getString(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText(UIUtils.getString(R.string.refresh_ing_text));//刷新中的提示文字


        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        mRefreshLayout.shouldHandleRecyclerViewLoadingMore(mRvNews);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {
        mPhotographAdapter = new PhotographListAdapter(mPhotographList);
        mRvNews.setAdapter(mPhotographAdapter);

        mPhotographAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

        mPhotographAdapter.setEnableLoadMore(true);
        mPhotographAdapter.setOnLoadMoreListener(this, mRvNews);

    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        mPresenter.getPhotographList(currentPage);
        mStateView.showContent();//显示内容
    }

    @Override
    public void onGetPhotographListSuccess(List<BBSPostItem> newList, String tipInfo) {
        mRefreshLayout.endRefreshing();// 加载完毕后在 UI 线程结束下拉刷新
        //如果是第一次获取数据
        if (ListUtils.isEmpty(mPhotographList)) {
            if (ListUtils.isEmpty(newList)) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }
        if (ListUtils.isEmpty(newList)) {
            //已经获取不到新闻了，处理出现获取不到新闻的情况
            UIUtils.showToast(UIUtils.getString(R.string.no_news_now));
            return;
        }
        if(currentPage == 1){
            mPhotographList.clear();
            mPhotographList.addAll(0, newList);
        }else{
            mPhotographAdapter.loadMoreComplete();
            mPhotographList.addAll(newList);
        }
        mPhotographAdapter.notifyDataSetChanged();
//        mTipView.show(tipInfo);
    }



    @Override
    public void onError() {
//        mTipView.show();//弹出提示
        if (ListUtils.isEmpty(mPhotographList)) {
            //如果一开始进入没有数据
            mStateView.showRetry();//显示重试的布局
        }

        //收起刷新
        if (mRefreshLayout.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
            mRefreshLayout.endRefreshing();
        }
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (!NetWorkUtils.isNetworkAvailable(mActivity)) {
            //网络不可用弹出提示
//            mTipView.show();
            if (mRefreshLayout.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                mRefreshLayout.endRefreshing();
            }
            return;
        }
        currentPage = 1;
        mPresenter.getPhotographList(currentPage);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        // BGARefresh的加载更多，不处理,使用到的是BaseRecyclerViewAdapterHelper的加载更多
        return false;
    }

    @Override
    public void onLoadMoreRequested() {
        currentPage++;
        mPresenter.getPhotographList(currentPage);
    }


    @Override
    public void onStart() {
        super.onStart();
//        registerEventBus(PhotographFragment.this);
    }

    @Override
    public void onStop() {
        super.onStop();
//        unregisterEventBus(PhotographFragment.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

