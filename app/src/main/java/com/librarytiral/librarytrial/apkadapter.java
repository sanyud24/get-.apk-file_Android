package com.librarytiral.librarytrial;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class apkadapter extends BaseAdapter {


    List<PackageInfo> packageList;
    Activity context;
    PackageManager packageManager;

    public apkadapter(Activity context, List<PackageInfo>packageList,
                      PackageManager packageManager) {
        super();
        this.context = context;
        this.packageList = packageList;
        this.packageManager = packageManager;
    }

    private class ViewHolder {
        TextView apkName;
    }

    public int getCount() {
        return packageList.size();
    }

    public Object getItem(int position) {
        return packageList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, null);
            holder = new ViewHolder();

            holder.apkName = (TextView) convertView.findViewById(R.id.filename);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PackageInfo packageInfo = (PackageInfo) getItem(position);
      /*  Drawable appIcon = packageManager
                .getApplicationIcon(packageInfo.applicationInfo);
                */
        String appName = packageManager.getApplicationLabel(
                packageInfo.applicationInfo).toString();
       /* appIcon.setBounds(0, 0, 40, 40);
        holder.apkName.setCompoundDrawables(appIcon, null, null, null);
        holder.apkName.setCompoundDrawablePadding(15);

        */
        holder.apkName.setText(appName);

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hello fellas", Toast.LENGTH_SHORT).show();
                final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(mainIntent, 0);
                for (ResolveInfo info : apps) {
                    File f1 = new File(info.activityInfo.applicationInfo.publicSourceDir);

                   // Toast.makeText(context, String.valueOf(file), Toast.LENGTH_SHORT).show();

                    String file_name = info.loadLabel(context.getPackageManager()).toString();
                    File f2 = new File(Environment.getExternalStorageDirectory().toString()+"/Folderapksa");
                    f2.mkdirs();
                    f2 = new File(f2.getPath()+"/"+file_name+".apk");
                    try
                    {
                        f2.createNewFile();


                    InputStream in = new FileInputStream(f1);

                    OutputStream out = new FileOutputStream(f2);

                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0){
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();
                    System.out.println("File copied.");
                }
                catch(FileNotFoundException ex){
                    System.out.println(ex.getMessage() + " in the specified directory.");
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
                }
            }
        });

        */

        return convertView;
    }
}