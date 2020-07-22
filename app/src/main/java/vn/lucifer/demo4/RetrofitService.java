package vn.lucifer.demo4;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
String a= "3";
    @GET("/posts/"+a)
    Call<MyGet> getData();

    @POST("/posts")
    @FormUrlEncoded
    Call<MyGet> getPostData(@Field("title") String title,
                            @Field("userId") String userId,
                            @Field("body") String body);

}
