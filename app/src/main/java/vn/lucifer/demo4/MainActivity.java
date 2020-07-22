package vn.lucifer.demo4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String getUrl = "https://jsonplaceholder.typicode.com/posts";
    String posstUrl = "https://jsonplaceholder.typicode.com/posts";

    Button btnGetRetrofit, btnGetVolley, btnGetAndroid, btnPostRetrofit, btnPostVolley, btnPostAndroid;
    TextView tvKq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetRetrofit = findViewById(R.id.btnGetRetrofit);
        btnGetVolley = findViewById(R.id.btnGetValue);
        btnGetAndroid = findViewById(R.id.btnGetAndroid);
        btnPostRetrofit = findViewById(R.id.btnPostRetrofit);
        btnPostVolley = findViewById(R.id.btnPostVolley);
        btnPostAndroid = findViewById(R.id.btnPostAndroid);
        tvKq = findViewById(R.id.tvKQ);


        btnGetVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btnGetAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts/1").build().getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        MyGet myGet=new Gson().fromJson(response.toString(),MyGet.class);

                        Toast.makeText(MainActivity.this,myGet.userId,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
            }
        });


        btnPostRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                retrofitService.getPostData("AA","1","DD").enqueue(new Callback<MyGet>() {
                    @Override
                    public void onResponse(Call<MyGet> call, Response<MyGet> response) {
                        String name = response.body().userId;
                        String des = response.body().body;
                        String title=response.body().title;

                        Log.e("AbC", name + des);

                        Toast.makeText(MainActivity.this, name + " : " + des +" : "+title, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<MyGet> call, Throwable t) {
                        // trả về lỗi
                        Log.e("A", t.getMessage());
                    }
                });

            }
        });


        btnPostVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btnPostAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
AndroidNetworking.post(posstUrl).addBodyParameter("title","dadfasdf").addBodyParameter("body","123").
        addBodyParameter("userId","hasdihf").build().getAsJSONObject(new JSONObjectRequestListener() {
    @Override
    public void onResponse(JSONObject response) {
        MyGet myGet=new Gson().fromJson(response.toString(),MyGet.class);
        Toast.makeText(MainActivity.this,myGet.body,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(ANError anError) {

    }
});

            }
        });
    }

    public void RetrofitGet(View view) {
        Log.e("ABC", "ABBB");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.getData().enqueue(new Callback<MyGet>() {
            @Override
            public void onResponse(Call<MyGet> call, Response<MyGet> response) {
                String name = response.body().id;
                String des = response.body().body;

                Log.e("AbC", name + des);

                Toast.makeText(MainActivity.this, name + " : " + des, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MyGet> call, Throwable t) {
                // trả về lỗi
                Log.e("A", t.getMessage());
            }
        });
    }
}