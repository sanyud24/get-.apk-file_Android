package com.librarytiral.librarytrial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    PackageManager packageManager;
    ListView apkList;
 String sanfile;
 File sanfile3;
    int size;
    ProgressBar fbar;
 EditText search ;
   private ArrayAdapter<String> adpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (EditText)findViewById(R.id.search);
        fbar = (ProgressBar)findViewById(R.id.progressBar); 
                
        packageManager = getPackageManager();
        List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);


        final List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();

        /*To filter out System apps*/
        for (PackageInfo pi : packageList) {
            boolean b = isSystemPackage(pi);
            if (!b) {
                packageList1.add(pi);

            }
        }
        apkList = (ListView) findViewById(R.id.applist);
      //  apkList.setAdapter(new apkadapter(this, packageList1, packageManager));

       /* apkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str= String.valueOf(packageList1.get(position));
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }
        });


        */




        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        apkList = (ListView) findViewById(R.id.applist);
        List<ResolveInfo> apps = getPackageManager().queryIntentActivities(mainIntent, 0);
        final ArrayList<String> filename = new ArrayList<String>();
        final ArrayList<File> newfile  = new ArrayList<File>();
               for (ResolveInfo info : apps) {
                      File f1 = new File(info.activityInfo.applicationInfo.publicSourceDir);
                  newfile.add(f1);
                   // Toast.makeText(context, String.valueOf(file), Toast.LENGTH_SHORT).show();

                   final String file_name = info.loadLabel(getPackageManager()).toString();
                   filename.add(file_name);
                   //  Toast.makeText(MainActivity.this, filename.get(2), Toast.LENGTH_SHORT).show();

                   apkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                           float increment,Treceived = 0;

                           sanfile =  filename.get(position);
                           sanfile3 = newfile.get(position);

                           Toast.makeText(MainActivity.this, String.valueOf(sanfile), Toast.LENGTH_SHORT).show();

                           File f2 = new File(Environment.getExternalStorageDirectory().toString() + "/SanyuGetAPK");
                               f2.mkdirs();

                           f2 = new File(f2.getPath() + "/" + sanfile + ".apk");
                           try {
                               f2.createNewFile();
                               long remaining = size;
                               increment=size/1024;
                               increment=100/increment;

                               InputStream in = new FileInputStream(sanfile3);

                               OutputStream out = new FileOutputStream(f2);

                               byte[] buf = new byte[1024];
                               int len;
                               while ((len = in.read(buf)) > 0) {
                                   out.write(buf, 0, len);
                                   Treceived=Treceived+increment;
                                   final float finalTreceived = Treceived;
                                   MainActivity.this.runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           fbar.setProgress((int) finalTreceived);
                                       }
                                   });
                               }
                               in.close();
                               out.close();
                               System.out.println("File copied.");
                           } catch (FileNotFoundException ex) {
                               System.out.println(ex.getMessage() + " in the specified directory.");
                           } catch (IOException e) {
                               System.out.println(e.getMessage());
                           }





             /*   final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> apps = new ArrayList<ResolveInfo>();
                apps = getPackageManager().queryIntentActivities(mainIntent, 0);

              */

              /*  for (ResolveInfo info : apps) {
                 //   File f1 = new File(info.activityInfo.applicationInfo.publicSourceDir);
                     ArrayList<String>  filename =  new ArrayList<String>();
                    // Toast.makeText(context, String.valueOf(file), Toast.LENGTH_SHORT).show();

                     String file_name = info.loadLabel(getPackageManager()).toString();
                     filename.add(file_name);
                    Toast.makeText(MainActivity.this, filename.get(2), Toast.LENGTH_SHORT).show();

                    File f2 = new File(Environment.getExternalStorageDirectory().toString() + "/Folderapksa");
                    f2.mkdirs();
                    f2 = new File(f2.getPath() + "/" + file_name + ".apk");
                    try {
                        f2.createNewFile();


                        InputStream in = new FileInputStream(f1);

                        OutputStream out = new FileOutputStream(f2);

                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();
                        System.out.println("File copied.");
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex.getMessage() + " in the specified directory.");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }







                }

                 */
                       }

                   });
                  // apkList.setAdapter(new apkadapter(this, filename, packageManager));
                    adpt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,filename);
                  apkList.setAdapter(adpt);
                  search.addTextChangedListener(new TextWatcher() {
                      @Override
                      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                      }

                      @Override
                      public void onTextChanged(CharSequence s, int start, int before, int count) {
                          (MainActivity.this).adpt.getFilter().filter(s);
                      }

                      @Override
                      public void afterTextChanged(Editable s) {

                      }
                  });

               }






    }


    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }


}
