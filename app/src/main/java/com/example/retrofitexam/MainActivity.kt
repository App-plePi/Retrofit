package com.example.retrofitexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getPosts(): Unit {

        val call: Call<List<PostData>> = JsonPlaceHolderClient.getApiService().getPosts()
        call.enqueue(object : Callback<List<PostData>> {

            override fun onResponse(
                call: Call<List<PostData>>,
                response: Response<List<PostData>>
            ) {
                findViewById<TextView>(R.id.codeTv).text = response.code().toString()
                if (response.isSuccessful) {
                    val postList: List<PostData> = response.body()!!
                    Log.e("getPostList", postList.toString())
                }
            }

            override fun onFailure(call: Call<List<PostData>>, t: Throwable) {
                Log.e("error", "error caused!")
            }

        })
    }

    fun getPostsFilterId(): Unit {
        val call: Call<PostData> = JsonPlaceHolderClient.getApiService().getPostFilterId("1")
        call.enqueue(object : Callback<PostData> {

            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                findViewById<TextView>(R.id.codeTv).text = response.code().toString()
                if (response.isSuccessful) {
                    val postData: PostData = response.body()!!
                    findViewById<TextView>(R.id.userIdTv).text = postData.userId.toString()
                    findViewById<TextView>(R.id.idTv).text = postData.id.toString()
                    findViewById<TextView>(R.id.titleTv).text = postData.title
                    findViewById<TextView>(R.id.bodyTv).text = postData.body

                }
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun setPost(): Unit {
        val postData: PostData = PostData(120, 115, "hello", "hello world!")
        val call: Call<PostData> = JsonPlaceHolderClient.getApiService().setPost(postData)

        call.enqueue(object : Callback<PostData> {

            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                findViewById<TextView>(R.id.codeTv).text = response.code().toString()
                if (response.isSuccessful) {
                    findViewById<TextView>(R.id.userIdTv).text = postData.userId.toString()
                    findViewById<TextView>(R.id.idTv).text = postData.id.toString()
                    findViewById<TextView>(R.id.titleTv).text = postData.title
                    findViewById<TextView>(R.id.bodyTv).text = postData.body
                }
            }


            override fun onFailure(call: Call<PostData>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}