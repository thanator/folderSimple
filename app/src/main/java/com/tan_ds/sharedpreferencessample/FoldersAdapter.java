package com.tan_ds.sharedpreferencessample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Tan-DS on 6/6/2017.
 */

public class FoldersAdapter extends BaseAdapter{

    private File[] mFiles;

    public FoldersAdapter(File[] files){this.mFiles = files;}

    @Override
    public int getCount() {
        return mFiles.length;
    }

    @Override
    public Object getItem(int position) {
        return mFiles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =convertView;

        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.folder_item, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.mText = (TextView) view.findViewById(R.id.item_elem);

            view.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        String text = mFiles[position].toString();
        holder.mText.setText(text);

        return view;
    }

    private static class ViewHolder{
        private TextView mText;
    }



}
