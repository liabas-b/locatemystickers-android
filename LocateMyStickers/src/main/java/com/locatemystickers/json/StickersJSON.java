package com.locatemystickers.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.TunnelRefusedException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.locatemystickers.Sticker;

public class StickersJSON  extends ParseJSON {
	public final static String URL = "https://locatemystickers-dev.herokuapp.com/users/";
	public final static String FILE = "/stickers.json";

    private int _user_id;
	
	public StickersJSON(int id_user) {
		super();
		_user_id = id_user;
	}

	public List<Sticker> readAllStickers() throws JSONException
	{
		List<Sticker> stickers = new ArrayList<Sticker>();
		
		try {
			//JSONArray jArray = this.readGET(URL + _user_id + FILE).getJSONArray("data");
			JSONArray jArray = this.readGetArray(URL + _user_id + FILE);
            for (int i=0;i<jArray.length();i++)
			{
				JSONObject jObject = jArray.getJSONObject(i);
				Sticker s = addSticker(jObject);
				stickers.add(s);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return stickers;
	}

    public List<Sticker> readSearchSticker(String urn)
    {
        List<Sticker> ls = new ArrayList<Sticker>();
        try
        {
            //JSONArray jArray = this.readGET(URL + _user_id + FILE + urn).getJSONArray("data");
            JSONArray jArray = this.readGetArray(URL + _user_id + FILE + urn);
            for (int i=0;i<jArray.length();i++)
            {
                JSONObject jObject = jArray.getJSONObject(i);
                Sticker s = addSticker(jObject);
                ls.add(s);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ls;
    }
	
	public Sticker readSticker(int id_sticker)
	{
		Sticker s = null;
		try
		{
			//JSONObject jObject = this.readGET(URL + _user_id + "/stickers/" + id_sticker + ".json").getJSONObject("data");
            JSONObject jObject = this.readGET(URL + _user_id + "/stickers/" + id_sticker + ".json");
			s = addSticker(jObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public void deleteSticker(final int id_sticker)
	{
		Log.d("DELETE", "stickers " + id_sticker);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				if (!sendDELETE(URL + _user_id + "/stickers/" + id_sticker))
					Log.d("DELETE", "Fail delete " + id_sticker);
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public static Sticker addSticker(JSONObject jObject) throws JSONException{
        return new Sticker(jObject.getString("code"),
                jObject.getString("created_at"),
                jObject.getInt("id"),
                jObject.getBoolean("is_active"),
                jObject.getString("name"),
                jObject.getInt("sticker_type_id"),
                jObject.getString("text"),
                jObject.getString("updated_at"),
                jObject.getInt("user_id"),
                jObject.getInt("version"));
    }

    public int get_user_id() {
        return _user_id;
    }
}
