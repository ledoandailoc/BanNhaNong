package com.example.uit.bannhanong.caches;

import android.graphics.Bitmap;
import android.view.View;

public interface SimpleImageLoaderUtilCallback {

    void onCompleted(String imageUri, View view, Bitmap loadedImage);
}