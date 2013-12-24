package com.locatemystickers;

import com.locatemystickers.json.StickersJSON;
import com.locatemystickers.json.UsersJSON;
import com.locatemystickers.type.Sticker;

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
    public UsersJSON _uj = new UsersJSON();
    public List<Sticker> _listStickers = null;
    //TODO public User _user;
}