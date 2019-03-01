
package com.chaychan.news.ui.photograph;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaychan.news.R;
import com.chaychan.news.model.pictour.PicRelative;
import com.chaychan.news.view.extral.AdProductModel;
import com.chaychan.news.view.showbigpic.LodingTouchImageView;

/**
 */
public class ImageLoadPagerAdapter extends PagerAdapter {

    public static int GRAPHIC = 1;
    public static int PICTUR = 2;

    private List<String> mResources;
    private ArrayList<PicRelative> mPicRelative;
    private List<AdProductModel> mAdList;

    private Context mContext;

    private int screenWidth;
    private int mType;
    private String size;
    /**
     * 是否是产品/资讯
     * 0 是产品
     * 1是资讯
     **/
    private String isPro = "1";

    private MyViewOnClickListener mListener;
    private MyViewOnTapClickListener mViewTapListener;


    public ImageLoadPagerAdapter(Context context, List<String> resources, int screenWidth, String size, int type) {
        this.mResources = resources;
        this.mContext = context;
        this.screenWidth = screenWidth;
        this.size = size;
        this.mType = type;
    }

    @Override
    public Object instantiateItem(View collection, final int position) {
        if (position == mResources.size() && mContext != null) {
            View priRelative = LayoutInflater.from(mContext).inflate(R.layout.pic_relative_grid, null);
            GridView gridView = (GridView) priRelative.findViewById(R.id.pic_relative_grid);
            PicRelativeAdapter picRelativeAdapter = new PicRelativeAdapter();
            gridView.setAdapter(picRelativeAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position >= mPicRelative.size()) {
                        return;
                    }
                    if (mContext != null) {
                        PicRelative priRelative1 = mPicRelative.get(position);
                        PicShowFactory.openGraphic(priRelative1.getDocId(), priRelative1.getsTitle(), priRelative1.getPic(), isPro, mContext);
                    }
                }
            });
            ((ViewPager) collection).addView(priRelative, 0);
            return priRelative;
        } else {
            View view = null;

//            boolean isGDTAd = mResources.get(position).equals(GDTAdPicView.GDT_PIC_URL);
            boolean isGDTAd = false;
            if (isGDTAd) {
                //调用render方法后sdk才会开始展示广告
//                try {
//                    this.mGDTAdView.render();
//                } catch (Exception e) {
//                }
//                view = this.mGDTAdView;

            } else {
                LodingTouchImageView iv = new LodingTouchImageView(mContext, R.color.black);
                iv.setMyOnClickListener(new LodingTouchImageView.MyOnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onClick(v);
                        }
                    }
                });
                iv.setMyOnViewTapListener(new LodingTouchImageView.MyOnViewTapClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mViewTapListener != null) {
                            mViewTapListener.onClick(v);
                        }
                    }
                });
                String url = null;
                if (mType == GRAPHIC) {
                    url = matchGraphicUrl(mResources.get(position));
                } else if (mType == PICTUR) {
                    url = matchPicturUrl(mResources.get(position));
                }

                if (position == 0) {
                    iv.setAdInfo(this.mAdList);
                }
                iv.setUrl(url);

                iv.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

                view = iv;
            }


            ((ViewPager) collection).addView(view, 0);

            view.setTag(position);

            return view;
        }
    }

    @Override
    public void destroyItem(View collection, int position, Object view) {
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public int getCount() {
        if (mPicRelative != null && mPicRelative.size() > 0) {
            return mResources.size() + 1;
        }
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
    }

    public void setPicRelative(ArrayList<PicRelative> list, String isProdu) {
        this.mPicRelative = list;
        this.isPro = isProdu;
        this.notifyDataSetChanged();
    }

    public void setList(List<String> resources) {
        mResources = resources;
    }


    /**
     * 图赏url的匹配
     *
     * @param url
     * @return
     */
    private String matchPicturUrl(String url) {
        if (screenWidth >= 720) {
            if (size != null && size.length() != 0) {
                url = url.replace(size, "960x720");
            } else {
                url = url.replaceFirst("_\\d{1,4}x\\d{1,4}/", "_960x720/");
            }
        } else if (screenWidth >= 480) {
            if (size != null && size.length() != 0) {
                url = url.replace(size, "640x480");//500x375480x320 480x320
            } else {
                url = url.replaceFirst("_\\d{1,4}x\\d{1,4}/", "_640x480/");
            }
        } else if (screenWidth > 0) {
            if (size != null && size.length() != 0) {
                url = url.replace(size, "370x280");//480x320 280x210
            } else {
                url = url.replaceFirst("_\\d{1,4}x\\d{1,4}/", "_370x280/");
            }
        }
        return url;
    }

    /**
     * 不带文本的url匹配
     *
     * @param url
     * @return
     */
    private String matchGraphicUrl(String url) {
        if (screenWidth >= 720) {
            url = url.replace(size, "720x540");
        } else if (screenWidth >= 480) {
            url = url.replace(size, "480x320");//500x375480x320
        } else if (screenWidth > 0) {
            url = url.replace(size, "280x210");//480x320
        }
        return url;
    }

    public void setMyOnClickListener(MyViewOnClickListener listener) {
        this.mListener = listener;
    }

    public void setMyViewOnTapClickListener(MyViewOnTapClickListener listener) {
        this.mViewTapListener = listener;
    }

    public interface MyViewOnClickListener {
        void onClick(View v);
    }

    public interface MyViewOnTapClickListener {
        void onClick(View v);
    }

    /**
     * 推荐图集页面view
     */
    class PicRelativeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mPicRelative != null && mPicRelative.size() > 0) {
                return mPicRelative.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return mPicRelative.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (mContext == null) {
                return convertView;
            }
            ViewHolder holder;
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.pic_relative_item, parent, false);
                holder = new ViewHolder();
                holder.picRelative = (ImageView) convertView.findViewById(R.id.relative_image);
                holder.picRelativeTitle = (TextView) convertView.findViewById(R.id.relative_title);
                convertView.setTag(holder);
            }
            if (position >= mPicRelative.size()) {
                return convertView;
            }
            PicRelative picRelative = mPicRelative.get(position);
            holder.picRelativeTitle.setText(picRelative.getTitle());
            int imageWidth = (screenWidth - 60) / 2;
            int imageHeight = (screenWidth - 60) / 2 - 100;
            RelativeLayout.LayoutParams bigLayout = (RelativeLayout.LayoutParams) holder.picRelative.getLayoutParams();
            bigLayout.height = imageHeight;
            holder.picRelative.setLayoutParams(bigLayout);
            holder.picRelative.setScaleType(ImageView.ScaleType.CENTER_CROP);

            if (!TextUtils.isEmpty(picRelative.getPic())) {
                if (mContext != null) {
                    try {
                        Glide.with(mContext).load(picRelative.getPic())
                                .into(holder.picRelative);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView picRelative;
        TextView picRelativeTitle;
    }
}