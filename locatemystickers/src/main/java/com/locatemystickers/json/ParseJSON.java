package com.locatemystickers.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

public class ParseJSON {
	private String _url = null;
	
	public ParseJSON() {
	}
	
	protected boolean sendDELETE(String url)	{
		HttpClient client = new DefaultHttpClient();

		try {
            HttpResponse response = client.execute(new HttpDelete(url));
            HttpConnectionParams.setSoTimeout(client.getParams(), 300);
            StatusLine statusLine = response.getStatusLine();
			int statuscode = statusLine.getStatusCode();
			if (statuscode == HttpStatus.SC_OK)
				return true;
				
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected JSONObject readGET(String url) throws JSONException	{
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		try {
            HttpResponse response = client.execute(new HttpGet(url));
            HttpConnectionParams.setSoTimeout(client.getParams(), 300);
            StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == HttpStatus.SC_OK)	{
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream content =  entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
					content.close();
				} else {
					Log.e(ParseJSON.class.toString(), "Failed to download file");
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JSONObject(builder.toString());
	}

    protected JSONArray readGetArray(String url) throws JSONException {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(new HttpGet(url));
            HttpConnectionParams.setSoTimeout(client.getParams(), 300);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity =  response.getEntity();
                if (entity != null) {
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONArray(builder.toString());
    }
}
