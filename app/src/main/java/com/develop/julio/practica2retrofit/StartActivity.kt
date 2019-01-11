package com.develop.julio.practica2retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.develop.julio.practica2retrofit.Adaptadores.AdapterPost
import com.develop.julio.practica2retrofit.Data.Post
import com.develop.julio.practica2retrofit.Singleton.RetrofitSingleton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StartActivity : AppCompatActivity() {

    lateinit var recxyclerViewPost : RecyclerView
    private lateinit var adaptr : AdapterPost
    private var compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        recxyclerViewPost = findViewById(R.id.listPost)
        recxyclerViewPost.setHasFixedSize(true)
        recxyclerViewPost.addItemDecoration(DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL))
        recxyclerViewPost.layoutManager = LinearLayoutManager(applicationContext)

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
        compositeDisposable.add(retrofitSingleton.getAllPost()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({posts -> mostrarDatos(posts)
            Log.i("datos",posts.toString())
        },{
                r -> r.cause
            //Toast.makeText(this,"No puedo establecer connexion",Toast.LENGTH_SHORT)
        }))
    }
    private fun mostrarDatos(listPost:MutableList<Post>){
        adaptr = AdapterPost()
        adaptr.setListPost(listPost)
        recxyclerViewPost.adapter = adaptr
    }
}

/*
* var respuesta = retrofitSingleton.getAllPost()

        respuesta.observeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adaptr.setListPost(it)
            },{
                it.localizedMessage
                it.cause
            })
* */
