package com.locatemystickers.json;


import com.locatemystickers.Everything;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EverythingJSON extends ParseJSON{
    private int _id_user;

    public EverythingJSON(int id_user) {
        _id_user = id_user;
    }

    public List<Everything> readSearchEverything(String urnSticker, String urnUser) {
        List<Everything> le = new ArrayList<Everything>();
        try {
            JSONArray jArrayStickers = this.readGET(StickersJSON.URL + _id_user + StickersJSON.FILE + urnSticker).getJSONArray("data"); // readGET Sticker
            JSONArray jsonArrayUsers = this.readGetArray(UsersJSON.URI.concat(urnUser));
            for (int i=0;i<jArrayStickers.length();i++) {
                JSONObject jObject = jArrayStickers.getJSONObject(i);
                le.add(new Everything(StickersJSON.addSticker(jObject)));
            }
            for (int i=0;i<jsonArrayUsers.length();i++) {
                JSONObject jObject = jsonArrayUsers.getJSONObject(i);
                le.add(new Everything(UsersJSON.addUser(jObject)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return le;
    }

}
