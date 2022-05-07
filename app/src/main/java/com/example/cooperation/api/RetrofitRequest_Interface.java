package com.example.cooperation.api;

import com.example.cooperation.model.ItemAdd;
import com.example.cooperation.model.ItemListResponseBody;
import com.example.cooperation.model.ProjectCreate;
import com.example.cooperation.model.ProjectListResponseBody;
import com.example.cooperation.model.ProjectModifyInfo;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.model.User;
import com.example.cooperation.model.UserInfoResponseBody;
import com.example.cooperation.model.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitRequest_Interface {
    @POST("user/login")
    Call<ResponseBody> userLogin(@Body UserLogin userLogin);

    @POST("user/register")
    Call<ResponseBody> userRegister(@Body User userEntity);


    @POST("user/delete")
    Call<ResponseBody> userDelete(@Header("token") String token,@Body String userName);

    @POST("user/modify")
    Call<ResponseBody> userModify(@Header("token") String token,@Body User user);

    @POST("user/password/{password}")
    Call<ResponseBody> userModifyPassword(@Header("token") String token,@Path("password") String password);

    @GET("user/get/{username}")
    Call<UserInfoResponseBody> userGetByUsername(@Header("token") String token, @Path("username") String username);

    @GET("user/get/current")
    Call<UserInfoResponseBody> userGetCurrent(@Header("token") String token);

    @POST("project/create")
    Call<ResponseBody> projectCreate(@Header("token") String token, @Body ProjectCreate projectCreate);

    @POST("project/delete/{projectId}")
    Call<ResponseBody> projectDelete(@Header("token") String token, @Path("projectId") int projectId);

    @POST("project/modify/{projectId}")
    Call<ResponseBody> projectModify(@Header("token") String token, @Body ProjectModifyInfo projectModify,@Path("projectId") int projectId);

    @GET("project/get/{projectId}")
    Call<ResponseBody> projectGetOne(@Header("token") String token, @Path("projectId") int projectId);

    @GET("project/getList")
    Call<ProjectListResponseBody> projectGetList(@Header("token") String token);

    @GET("project/getCooperator/{projectId}")
    Call<ResponseBody> projectGetCooperator(@Header("token") String token,@Path("projectId") int projectId);

    @POST("project/joinProject/{invitationCode}")
    Call<ResponseBody> projectJoinProject(@Header("token") String token,@Path("invitationCode") String invitationCode);

    @POST("item/add")
    Call<ResponseBody> itemAdd(@Header("token") String token, @Body ItemAdd itemAdd);

    @POST("item/delete/{itemId}")
    Call<ResponseBody> itemDelete(@Header("token") String token,@Path("itemId") int itemId);

    @POST("item/modify/{itemId}")
    Call<ResponseBody> itemModifyInfo(@Header("token") String token,@Path("itemId") int itemId,@Body ItemAdd itemAdd);

    @POST("item/setStatus/{itemId}")
    Call<ResponseBody> itemModifyStatus(@Header("token") String token, @Path("itemId") int itemId, @Query("itemStatus") String itemStatus);

    @GET("item/getListByProjectId/{projectId}")
    Call<ResponseBody> itemGetListByProjectId(@Header("token") String token,@Path("projectId") int projectId);

    @GET("item/get/{itemId}")
    Call<ResponseBody> itemGetItemInfo(@Header("token") String token,@Path("itemId") int itemId);

    @GET("item/getCurrentItemList")
    Call<ItemListResponseBody> itemGetCurrentItemList(@Header("token") String token);
}
