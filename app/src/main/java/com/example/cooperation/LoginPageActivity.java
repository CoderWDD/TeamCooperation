package com.example.cooperation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.constant.SharedPreferencesConstant;
import com.example.cooperation.databinding.ActivityLoginPageBinding;
import com.example.cooperation.databing.PageLoginDataBinding;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.model.User;
import com.example.cooperation.model.UserLogin;
import com.example.cooperation.utils.DecodeJsonFromData;
import com.example.cooperation.utils.check.input.UserLoginCheckUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPageActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPreferences.edit();

        String username = sharedPreferences.getString(SharedPreferencesConstant.USERNAME, "");

        String password = sharedPreferences.getString(SharedPreferencesConstant.PASSWORD, "");

        String nickname = sharedPreferences.getString(SharedPreferencesConstant.NICKNAME, "");

        String firstname = sharedPreferences.getString(SharedPreferencesConstant.FIRSTNAME, "");

        String lastname = sharedPreferences.getString(SharedPreferencesConstant.LASTNAME, "");

        String phone = sharedPreferences.getString(SharedPreferencesConstant.PHONE, "");

        String department = sharedPreferences.getString(SharedPreferencesConstant.DEPARTMENT, "");

        String description = sharedPreferences.getString(SharedPreferencesConstant.DESCRIPTION, "");

        String createTime = sharedPreferences.getString(SharedPreferencesConstant.CREATE_TIME, "");

        if (UserLoginCheckUtil.check(this,username,password)){

            MyRetrofit.InitInstance();

            RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();


            User user = new User();

            user.setUserName(username);
            user.setPassword(password);
            user.setNickName(nickname);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setPhone(phone);
            user.setDepartment(department);
            user.setDescription(description);
            user.setCreateTime(createTime);

            MyRetrofit.setUser(user);

            UserLogin userLogin = new UserLogin();
            userLogin.setUsername(username);
            userLogin.setPassword(password);
            Call<ResponseBody> responseBodyCall = retrofitRequestInterface.userLogin(userLogin);

            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && HttpStatus.OK.equals(response.body().getCode())) {
                        // 如果登录成功，就将token存入全局
                        String token = DecodeJsonFromData.GetToken("{" + response.body().getData() + "}").getToken();
                        MyRetrofit.setToken(token);
                        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit1 = preferences.edit();

                        edit1.putString("token",token);

                        edit1.apply();

                        Intent intent = new Intent(context, PageHomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }else {
                        Toast.makeText(context,"Auto login failure!",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(context,"Auto login failure!",Toast.LENGTH_SHORT).show();
                }
            });

        }


        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login_page);

        // 设置dataBing
        ActivityLoginPageBinding activityLoginPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_page);
        activityLoginPageBinding.setLoginPageEventHandler(new PageLoginDataBinding(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}