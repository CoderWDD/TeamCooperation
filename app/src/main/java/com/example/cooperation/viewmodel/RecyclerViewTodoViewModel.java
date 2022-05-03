package com.example.cooperation.viewmodel;

import com.example.cooperation.model.ItemAdd;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTodoViewModel {
    public List<ItemAdd> getItems(){
        List<ItemAdd> itemList = new ArrayList<>();

        // TODO 网络请求，获取items
        ItemAdd itemAdd = new ItemAdd();
        itemAdd.setItemId(10);
        itemAdd.setItemName("item_01");
        itemAdd.setAuthor("CoderWdd");
        itemAdd.setExecutor("吴某人");
        itemAdd.setDescription("没什么");
        itemAdd.setStatus("Doing");
        itemAdd.setProjectId(22);

        itemList.add(itemAdd);

        ItemAdd itemAdd1 = new ItemAdd();
        itemAdd1.setItemId(10);
        itemAdd1.setItemName("item_01");
        itemAdd1.setAuthor("CoderWdd");
        itemAdd1.setExecutor("吴某人");
        itemAdd1.setDescription("没什么");
        itemAdd1.setStatus("Doing");
        itemAdd1.setProjectId(22);

        itemList.add(itemAdd);
        itemList.add(itemAdd);
        itemList.add(itemAdd);

        return itemList;
    }
}
