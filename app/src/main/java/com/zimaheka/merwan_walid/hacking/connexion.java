package com.zimaheka.merwan_walid.hacking;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Walid Djebbouri on 18/02/2018.
 */

public class connexion extends StringRequest {
    private  static final String url = "https://walid-djebbouri.000webhostapp.com/cnx1.php" ;
    private HashMap<String , String> parmas  ;

    public   connexion(String email , String passWord , Response.Listener<String> lisner){

        super(Method.POST, url , lisner , null);
        parmas = new HashMap<>();
        parmas.put("email" , email) ;
        parmas.put("passWord" , passWord);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parmas;
    }
}
