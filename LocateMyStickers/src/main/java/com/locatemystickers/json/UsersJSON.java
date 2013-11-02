package com.locatemystickers.json;

import android.util.Log;

import com.locatemystickers.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsersJSON extends ParseJSON {

    public final static String URI = "https://locatemystickers-dev.herokuapp.com/users.json";

    public UsersJSON() {
        super();
    }

    public List<User> readSearchUser(String urn)
    {
        List<User> users = new ArrayList<User>();
        try {
            JSONArray jArray = this.readGetArray(URI.concat(urn));
            for (int i=0;i<jArray.length();i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                users.add(addUser(jObject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User addUser(JSONObject jObject) throws JSONException
    {
        return (new User(jObject.getBoolean("admin"),
                jObject.getString("adress"),
                jObject.getString("city"),
                jObject.getInt("compteur"),
                jObject.getString("country"),
                jObject.getString("created_at"),
                jObject.getString("email"),
                jObject.getString("first_name"),
                jObject.getInt("id"),
                jObject.getString("last_name"),
                jObject.getString("name"),
                jObject.getString("password_digest"),
                jObject.getString("phone"),
                jObject.getString("remember_token"),
                jObject.getString("updated_at"),
                jObject.getString("zip_code")));
    }
}
