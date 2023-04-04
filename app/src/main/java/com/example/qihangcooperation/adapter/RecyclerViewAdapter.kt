package com.example.qihangcooperation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.ParameterizedType

class RecyclerViewAdapter(
    private var proxyList: MutableList<RVProxy<*, *>> = mutableListOf(),
    var dataList: MutableList<Any> = mutableListOf()
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * a way for [ViewHolder] to communicate with [RecyclerView.Adapter]
     */
    var action: ((Any?) -> Unit)? = null

    // viewType should start with 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return proxyList[0].onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (proxyList[getItemViewType(position)] as RVProxy<Any, RecyclerView.ViewHolder>).onBindViewHolder(holder, dataList[position], position, action)
    }

    // 将填充表项分发给策略（布局刷新）
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        (proxyList[getItemViewType(position)] as RVProxy<Any, RecyclerView.ViewHolder>).onBindViewHolder( holder, dataList[position], position, action, payloads )
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    // 获取策略在列表中的索引
    private fun getProxyIndex(data: Any): Int = proxyList.indexOfFirst {
        // 如果Proxy<T,VH>中的第一个类型参数T和数据的类型相同，则返回对应策略的索引
        (it.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].toString() == data.javaClass.toString()
    }


    fun <T, VH: RecyclerView.ViewHolder> addProxy(proxy: RVProxy<T, VH>){
        proxyList.add(proxy)
    }

    fun <T, VH: RecyclerView.ViewHolder> removeProxy(proxy: RVProxy<T, VH>){
        proxyList.remove(proxy)
    }
}