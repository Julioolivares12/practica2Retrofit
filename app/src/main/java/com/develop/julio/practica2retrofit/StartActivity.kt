package com.develop.julio.practica2retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.develop.julio.practica2retrofit.Adaptadores.AdapterPost
import com.develop.julio.practica2retrofit.Singleton.RetrofitSingleton
import io.reactivex.schedulers.Schedulers

class StartActivity : AppCompatActivity() {

    lateinit var recxyclerViewPost : RecyclerView
    private lateinit var adaptr : AdapterPost
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        recxyclerViewPost = findViewById(R.id.listPost)
        recxyclerViewPost.setHasFixedSize(true)
        recxyclerViewPost.addItemDecoration(DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL))
        recxyclerViewPost.layoutManager = LinearLayoutManager(applicationContext)
        adaptr = AdapterPost()
        recxyclerViewPost.adapter = adaptr
        getData()
        /**if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StartFragment.newInstance())
                .commitNow()
        }
        **/

    }

    private fun getData() {
        var retrofitSingleton = RetrofitSingleton().getApiService()
        var respuesta = retrofitSingleton.getAllPost()
        respuesta.observeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .subscribe({
                adaptr.setListPost(it)
            },{
                it.localizedMessage
            })

    }


}
