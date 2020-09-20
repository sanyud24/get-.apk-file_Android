package com.librarytiral.librarytrial;

import android.graphics.Bitmap;

public class listconstructer {

    String filename;
    Bitmap photo;


    public listconstructer(String filename,Bitmap photo)
    {
        this.filename= filename;
        this.photo= photo;


    }

    public String getfilename(){
        return filename;
    }


    public  Bitmap getphoto()
    {
        return photo;
    }



}
