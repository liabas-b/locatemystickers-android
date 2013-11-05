package com.locatemystickers;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.locatemystickers.json.StickersJSON;
import com.locatemystickers.json.UsersJSON;

import java.util.List;

public class Singleton {
    public static Singleton getInstance() {
        if (null == instance) {
            instance = new Singleton();
        }
        return instance;
    }
    private Singleton() {
    }
    private static Singleton instance;
    public StickersJSON _sj;
    public UsersJSON _uj;
    public List<Sticker> _listStickers = null;

}