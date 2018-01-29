package com.company.seoultest;

public class DiaryListItem {

    private String[] mData; // Data 배열

    private String mId; // item id

    private boolean mSelectable = true; // 이 아이템이 선택가능할경유 true

    public DiaryListItem(String itemId, String[] obj) {
        mId = itemId;
        mData = obj;
    }

    public DiaryListItem(String memoId, String memoDate, String memoText,
                        String id_photo, String uri_photo)
    {
        mId = memoId;
        mData = new String[4];
        mData[0] = memoDate;
        mData[1] = memoText;
        mData[2] = id_photo; //4
        mData[3] = uri_photo; // 5
    }

    /**
     * True if this item is selectable
     */
    public boolean isSelectable() {
        return mSelectable;
    }

    /**
     * Set selectable flag
     */
    public void setSelectable(boolean selectable) {
        mSelectable = selectable;
    }

    public String getId() {
        return mId;
    }

    public void setId(String itemId) {
        mId = itemId;
    }


    /**
     * Get data array
     */
    public String[] getData() {
        return mData;
    }

    /**
     * Get data
     */
    public String getData(int index) {
        if (mData == null || index >= mData.length) {
            return null;
        }

        return mData[index];
    }

    /**
     * Set data array
     *
     * @param obj
     */
    public void setData(String[] obj) {
        mData = obj;
    }


    /**
     * Compare with the input object
     */
    public int compareTo(DiaryListItem other) {
        if (mData != null) {
            Object[] otherData = other.getData();
            if (mData.length == otherData.length) {
                for (int i = 0; i < mData.length; i++) {
                    if (!mData[i].equals(otherData[i])) {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        } else {
            throw new IllegalArgumentException();
        }

        return 0;
    }

}
