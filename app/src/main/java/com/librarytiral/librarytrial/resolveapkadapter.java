package com.librarytiral.librarytrial;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class resolveapkadapter  extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<resolveapkcons> AppUserList;

   //
           // PackageManager packageManager;

    public resolveapkadapter(Context context, List<resolveapkcons> AppUserList) {
        super();

        this.mContext = context;

        this.AppUserList = AppUserList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return  AppUserList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return AppUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class ViewHolder{
        TextView filename;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ViewGroup vg=null;
        if (convertView==null){
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.listview,vg);
            holder.filename = (TextView) convertView.findViewById(R.id.filename);

            convertView .setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView .getTag();
        }

        holder.filename.setText(AppUserList.get(position).sanfilename);

        return convertView;
    }

}

