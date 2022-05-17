package com.example.cooperation.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cooperation.ActivityPageTaskItemDetails;
import com.example.cooperation.R;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.databinding.FragmentPageItemToDoBinding;
import com.example.cooperation.databing.adapter.RecyclerViewAdapterForTodo;
import com.example.cooperation.databing.click.TaskItemClicked;
import com.example.cooperation.model.ItemAdd;
import com.example.cooperation.model.ItemListResponseBody;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.utils.SelectListByStatusUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewTodoViewModel {
    private final List<ItemAdd> itemList;
    private final Context context;
    private final FragmentPageItemToDoBinding fragmentPageItemToDoBinding;
    private final SwipeRefreshLayout swipeRefreshLayout;

    public RecyclerViewTodoViewModel(Context context, FragmentPageItemToDoBinding fragmentPageItemToDoBinding, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.fragmentPageItemToDoBinding = fragmentPageItemToDoBinding;
        this.swipeRefreshLayout = swipeRefreshLayout;
        itemList = new LinkedList<>();
    }

    public void refreshRecyclerViewItems(){
        // 防止刷新后旧数据还在
        itemList.clear();
        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ItemListResponseBody> responseBodyCall = retrofitRequestInterface.itemGetCurrentItemList(MyRetrofit.getToken());

        responseBodyCall.enqueue(new Callback<ItemListResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call<ItemListResponseBody> call, @NonNull Response<ItemListResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
                    ItemAdd[] items = response.body().getData();
                    itemList.addAll(Arrays.asList(items));

                    // 获取要显示的是哪种状态的item，并显示
                    AppCompatCheckBox itemTodo = fragmentPageItemToDoBinding.getRoot().findViewById(R.id.item_status_todo);

                    AppCompatCheckBox itemDoing = fragmentPageItemToDoBinding.getRoot().findViewById(R.id.item_status_doing);

                    AppCompatCheckBox itemDone = fragmentPageItemToDoBinding.getRoot().findViewById(R.id.item_status_done);

                    AppCompatCheckBox showCoops = fragmentPageItemToDoBinding.getRoot().findViewById(R.id.item_show_cooperators);
                    List<ItemAdd> showList;
                    if (itemTodo == null || itemDoing == null || itemDone == null){
                        Toast.makeText(context,"Something wrong!",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        showList = SelectListByStatusUtil.selectItems(itemList, itemTodo.isChecked(), itemDoing.isChecked(), itemDone.isChecked(),showCoops.isChecked(),MyRetrofit.getUser().getUserName());
                    }

                    RecyclerViewAdapterForTodo recyclerViewAdapterForTodo = new RecyclerViewAdapterForTodo(showList, new TaskItemClicked() {
                        @Override
                        public void onClicked(View view, ItemAdd item) {
                            Intent intent = new Intent(context, ActivityPageTaskItemDetails.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("task_item",item);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }

                        @Override
                        public boolean onLongClicked(View view,final ItemAdd itemAdd) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                            dialog.setTitle(R.string.attention);
                            dialog.setMessage(R.string.delete_message);
                            dialog.setPositiveButton(R.string.delete_positive_button, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Call<ResponseBody> responseBodyCall1 = retrofitRequestInterface.itemDelete(MyRetrofit.getToken(), itemAdd.getItemId());
                                    responseBodyCall1.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            Toast.makeText(context,response.body().getData(),Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                            dialog.setNegativeButton(R.string.delete_negative_button, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            dialog.create().show();
                            return false;
                        }
                    });

                    if (swipeRefreshLayout != null) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    fragmentPageItemToDoBinding.recyclerviewTodo.setAdapter(recyclerViewAdapterForTodo);

                }
            }

            @Override
            public void onFailure(@NonNull Call<ItemListResponseBody> call, @NonNull Throwable t) {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
