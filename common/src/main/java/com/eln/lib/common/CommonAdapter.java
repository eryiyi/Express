package com.eln.lib.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用Adapter代码
 * 
 * @author tony.zxb@alibaba-inc.com
 * @since 2016/1/28 18:00
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> dataList;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<T> dataList, int itemLayoutId) {
        this.mContext = context;
        this.dataList = dataList;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = ViewHolder.getHolder(mContext, convertView, parent, mItemLayoutId, position);
        createView(viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    public abstract void createView(ViewHolder holder, T item);
    
}
