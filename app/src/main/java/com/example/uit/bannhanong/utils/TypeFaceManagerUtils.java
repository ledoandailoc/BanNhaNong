package com.example.uit.bannhanong.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.example.uit.bannhanong.BNNApplication;

public class TypeFaceManagerUtils {
    // Bold italic
    private static Typeface sFsMeBI;
    //Bold
    private static Typeface sFontBoldTypeFace;

    // Heavy Italic
    private static Typeface mFsMeHI;
    // Heavy
    private static Typeface mFsMeH;

    // Italic
    private static Typeface mFsMeI;

    // Light Italic
    private static Typeface mFsMeLI;

    // Light
    private static Typeface mFsMeL;

    // Regular
    private static Typeface sFontRegularTypeFace;

    private static Context mContext;

    private TypeFaceManagerUtils() {
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static Typeface getFsMeBoldItalicTypeface() {
        if (sFsMeBI == null) {
            sFsMeBI = Typeface.createFromAsset(mContext.getAssets(), "fonts/FS-Me-Bold-Italic.otf");
        }
        return sFsMeBI;
    }

    public static Typeface getFontBoldTypeface() {
        if (sFontBoldTypeFace == null) {
            sFontBoldTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Lato Bold.TTF");
        }
        return sFontBoldTypeFace;
    }

    public static Typeface getFsMeHeavyItalicTypeface() {
        if (mFsMeHI == null) {
            mFsMeHI = Typeface.createFromAsset(mContext.getAssets(), "fonts/FS-Me-Heavy-Italic.otf");
        }
        return mFsMeHI;
    }

    public static Typeface getFsMeHeavyTypeface() {
        if (mFsMeH == null) {
            mFsMeH = Typeface.createFromAsset(mContext.getAssets(), "fonts/FS-Me-Heavy.otf");
        }
        return mFsMeH;
    }

    public static Typeface getFsMeItalicTypeface() {
        if (mFsMeI == null) {
            mFsMeI = Typeface.createFromAsset(mContext.getAssets(), "fonts/FS-Me-Heavy-Italic.otf");
        }
        return mFsMeI;
    }

    public static Typeface getFsMeLightItalicTypeface() {
        if (mFsMeLI == null) {
            mFsMeLI = Typeface.createFromAsset(mContext.getAssets(), "fonts/FS-Me-Light-Italic.otf");
        }
        return mFsMeLI;
    }

    public static Typeface getFsMeLightTypeface() {
        if (mFsMeL == null) {
            mFsMeL = Typeface.createFromAsset(mContext.getAssets(), "fonts/FS-Me-Light.otf");
        }
        return mFsMeL;
    }

    public static Typeface getFontRegularTypeface() {
        if (sFontRegularTypeFace == null) {
            sFontRegularTypeFace = Typeface.createFromAsset(BNNApplication.getContext().getAssets()
                    , "fonts/Lato Regular.TTF");
        }
        return sFontRegularTypeFace;
    }
}
