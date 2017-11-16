package exousiatech.newpracticeapp.Interfaces;

import exousiatech.newpracticeapp.Modals.HomeResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Admin on 14-11-2017.
 */

public interface WebAPI {
    @FormUrlEncoded
    @POST("ico-calendar-ongoing")
    Call<HomeResponse> onGoing(@Field("limit")int limit, @Field("offset") int offset);
}
