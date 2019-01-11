package com.develop.julio.practica2retrofit.Adaptadores

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.develop.julio.practica2retrofit.Data.Post
import com.develop.julio.practica2retrofit.R

class AdapterPost : RecyclerView.Adapter<AdapterPost.PostHolder>() {
    private lateinit var postlist : MutableList<Post>
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostHolder {
        var view = LayoutInflater.from(p0.context)
        return PostHolder(view.inflate(R.layout.row_post,p0,false))
    }

    override fun getItemCount() = postlist.size

    override fun onBindViewHolder(postHold: PostHolder, position: Int) {
        var post = postlist[position]
         postHold.bind(post)
    }
    fun setListPost(postList : MutableList<Post>){
        this.postlist.addAll(postList)
        notifyDataSetChanged()
    }

   inner class PostHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txttitle = view.findViewById<TextView>(R.id.txttitle)
        var txtbody = view.findViewById<TextView>(R.id.txtbody)
        fun bind(post: Post){
            txttitle.text = post.title
            txtbody.text = post.body
        }
    }
}