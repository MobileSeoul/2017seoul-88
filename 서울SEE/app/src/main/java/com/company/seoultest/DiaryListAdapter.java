package com.company.seoultest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class DiaryListAdapter extends BaseAdapter {

    private Context mContext;

    private List<DiaryListItem> mItems = new ArrayList<DiaryListItem>();

    public DiaryListAdapter(Context context) {
        mContext = context;
    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(DiaryListItem it) {
        mItems.add(it);
    }

    public void setListItems(List<DiaryListItem> lit) {
        mItems = lit;
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DiaryListItemView itemView;
        if (convertView == null) {
            itemView = new DiaryListItemView(mContext);
        } else {
            itemView = (DiaryListItemView) convertView;
        }

        // 현재아이템 불러오기
        itemView.setContents(0, ((String) mItems.get(position).getData(0)));
        itemView.setContents(1, ((String) mItems.get(position).getData(1)));
        itemView.setContents(3, ((String) mItems.get(position).getData(3)));


        return itemView;
    }

}
