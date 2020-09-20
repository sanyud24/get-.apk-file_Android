package com.librarytiral.librarytrial;

import android.app.Application;
import android.content.pm.PackageInfo;

public class appdata extends Application {


    PackageInfo packageInfo;

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }
}

