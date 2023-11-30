package com.example.concocte.data;

import android.content.Context;

import com.example.concocte.R;
import com.example.concocte.data.Quiz;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadJSON {

    public static JSONObject readJSONFile(Context context, int rawFile) throws IOException, JSONException{

        String jsonText = readText(context, rawFile);
        JSONObject jsonObject = null;

        jsonObject = new JSONObject(jsonText);

        return jsonObject;
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}
