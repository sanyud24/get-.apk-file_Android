package com.librarytiral.librarytrial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class listadapter extends BaseAdapter {



    Context cont;
    LayoutInflater layoutInflater;
    List<listconstructer> listcon;

    public listadapter(Context cont , List<listconstructer> listcon)
    {
        this.cont= cont;
        this.listcon = listcon;
        layoutInflater= LayoutInflater.from(cont);
    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return  listcon.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listcon.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class ViewHolder{
        TextView filename;
        ImageView photo;




    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ViewGroup vg = null;
        if (convertView == null) {
            holder = new ViewHolder();



            convertView = layoutInflater.inflate(R.layout.listview_item, vg);
            holder.photo = (ImageView) convertView.findViewById(R.id.photo);
            holder.filename = (TextView) convertView.findViewById(R.id.filename);



            convertView.setTag(holder);
            // convertView.setBackgroundResource(R.drawable.listview_selector);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.photo.setImageBitmap(listcon.get(position).getphoto());
        holder.filename.setText(listcon.get(position).filename);
        final listconstructer listarray = listcon.get(position);

        return convertView;
    }
}