package com.chaychan.news.ui.adapter.provider.photograph;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.chaychan.news.R;
import com.chaychan.news.model.entity.BBSPostItem;
import com.chaychan.news.model.entity.BBSPostReplyItem;
import com.chaychan.news.ui.adapter.BBSListImageAdapter;
import com.chaychan.news.ui.photograph.PicShowFactory;
import com.chaychan.news.ui.photograph.PicturShowActi;
import com.chaychan.news.utils.DateUtils;
import com.chaychan.news.utils.GlideUtils;
import com.chaychan.news.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chaychan.news.ui.adapter.PhotographListAdapter.PHOTOGRAPH_PICS_NEWS;

/**
 * @author ChayChan
 * @description: 将新闻中设置数据公共部分抽取
 * @date 2018/3/22  14:48
 */

public class PhotographItemProvider extends BaseItemProvider<BBSPostItem,BaseViewHolder> {

    /**
     * 是否加载图片
     */
    private boolean isLoadImage = true;


    public PhotographItemProvider() {
    }

    @Override
    public int viewType() {
        return PHOTOGRAPH_PICS_NEWS;
    }

    @Override
    public int layout() {
        return R.layout.bbs_list_item;
    }

    @Override
    public void convert(BaseViewHolder helper, BBSPostItem bbsPostItem, int position) {
        setBBSPostListData(helper, bbsPostItem);
    }

    /**
     * 设置普通帖子样式数据
     *
     * @param helper
     * @param bbsPostItem
     */
    private void setBBSPostListData(BaseViewHolder helper, final BBSPostItem bbsPostItem) {
        if (bbsPostItem == null) {
            return;
        }

        /**设置精选文章**/
        if (!TextUtils.isEmpty(bbsPostItem.getGood()) && !bbsPostItem.getGood().equals("0")) {
            SpannableStringBuilder style = new SpannableStringBuilder(" " + bbsPostItem.getTitle());
            Drawable d = mContext.getResources().getDrawable(R.drawable.bbs_subpage_top_essence_one);
            d.setBounds(0, 1, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            style.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            holder.itemInfo.setText(style);
            helper.setText(R.id.item_info, style);
        } else {
//            holder.itemInfo.setText(bbsPostItem.getTitle());
            helper.setText(R.id.item_info, bbsPostItem.getTitle());
        }

        TextView itemDescribe = helper.itemView.findViewById(R.id.item_describe);
        if (TextUtils.isEmpty(bbsPostItem.getContent())) {
            itemDescribe.setVisibility(View.GONE);
        } else {
            itemDescribe.setVisibility(View.VISIBLE);
//            holder.itemDescribe.setText(bbsPostItem.getContent());
            helper.setVisible(R.id.item_describe,true);
            helper.setText(R.id.item_describe, bbsPostItem.getContent());
        }
//        holder.lastTime.setText(DateTimeUtils.twoDateDistance(bbsPostItem.getPosted_time()));
        helper.setText(R.id.last_time, DateUtils.twoDateDistance(bbsPostItem.getPosted_time()));

//        holder.bbsWatchNumber.setText(bbsPostItem.getWatch());
        helper.setText(R.id.bbs_watch_number,bbsPostItem.getWatch());

        /**
         * 评论数超过100，设置评论数和图标为红色
         */
        String replyCount = bbsPostItem.getReply_count();
//        holder.bbsReplyNumber.setText(replyCount);
        helper.setText(R.id.bbs_reply_number,replyCount);

        ArrayList<String> imageList = bbsPostItem.getImageArrayList();
        ImageView singleImage = helper.itemView.findViewById(R.id.single_image);
        GridView moreImage = helper.itemView.findViewById(R.id.more_image);

        if (imageList != null && imageList.size() > 0) {
            if (imageList.size() < 3) {
                moreImage.setVisibility(View.GONE);

                //单张大图点击事件
                singleImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("bbs", bbsPostItem.getBbsName());
                        map.put("boardid", bbsPostItem.getBoardId());
                        map.put("bookid", bbsPostItem.getBookId());
                        map.put("position", "0");
                        map.put("author", bbsPostItem.getUserId());
                        if (mContext != null) {
                            PicShowFactory.openPicturNetwork(map, PicturShowActi.NETWORK_BBS, mContext);
                        }
                    }
                });
                if (isLoadImage) {
                    singleImage.setVisibility(View.VISIBLE);
//                    Glide.with(mContext).load(imageList.get(0)).override(340, 340).thumbnail(0.5f).dontAnimate().placeholder(R.drawable.pdplaceholder).error(R.drawable.pdplaceholder).diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.singleImage);
                    GlideUtils.load(mContext, imageList.get(0), singleImage);
                } else {
                    singleImage.setVisibility(View.GONE);
                }
            } else {
                singleImage.setVisibility(View.GONE);
                if (isLoadImage) {
                    moreImage.setVisibility(View.VISIBLE);
                    BBSListImageAdapter myGridAdapter = new BBSListImageAdapter(mContext);

                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) moreImage.getLayoutParams();
                    /**
                     * 根据图片数量，设置GRIDView的高度，
                     * 并设置每行显示数量，根据设计（720p设计）
                     *  3张图：每行显示3个，显示一行，高度根据720p的设计稿计算
                     *  9张图：每行显示3个，显示三行行，高度根据720p的设计稿计算
                     */
                    moreImage.setNumColumns(3);
                    List<String> picList = imageList;
                    if (imageList.size() >= 3 && imageList.size() < 6) {
                        int size = 184 * UIUtils.getResolution()[0] / 720;
                        layoutParams.height = size;
                        picList = imageList.subList(0, 3);
                    } else if (imageList.size() >= 6) {
                        int size = 372 * UIUtils.getResolution()[0] / 720;
                        layoutParams.height = size;
                        picList = imageList.subList(0, 6);
                    }
                    myGridAdapter.updateData(picList);
                    moreImage.setLayoutParams(layoutParams);
                    moreImage.setAdapter(myGridAdapter);
                    moreImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("bbs", bbsPostItem.getBbsName());
                            map.put("boardid", bbsPostItem.getBoardId());
                            map.put("bookid", bbsPostItem.getBookId());
                            map.put("position", position + "");
                            map.put("author", bbsPostItem.getUserId());
                            if (mContext != null) {
                                PicShowFactory.openPicturNetwork(map, PicturShowActi.NETWORK_BBS, mContext);
                            }
                        }
                    });
                } else {
                    moreImage.setVisibility(View.GONE);
                }
            }
        } else {
            moreImage.setVisibility(View.GONE);
            singleImage.setVisibility(View.GONE);
        }
    }
}
