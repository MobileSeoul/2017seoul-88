package com.company.seoultest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiaryListItemView extends LinearLayout {

    private ImageView itemPhoto;

    private TextView itemDate;

    private TextView itemText;

    Bitmap bitmap;

    public DiaryListItemView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.diarylist, this, true);

        itemPhoto = (ImageView) findViewById(R.id.itemPhoto);

        itemDate = (TextView) findViewById(R.id.itemDate);

        itemText = (TextView) findViewById(R.id.itemText);

    }

    public void setContents(int index, String data) {
        if (index == 0) {
            itemDate.setText(data);
        } else if (index == 1) {
            itemText.setText(data);
        } else if (index == 2) {

        } else if (index == 3) {
            if (data == null || data.equals("-1") || data.equals("")) {
                itemPhoto.setImageResource(R.drawable.photo);
            } else {
                if (bitmap != null) {
                    bitmap.recycle();
                }

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                bitmap = BitmapFactory.decodeFile(BasicInfo.FOLDER_PHOTO + data, options);

                itemPhoto.setImageBitmap(bitmap);

            }
        } else {
            throw new IllegalArgumentException();
        }
    }

}
