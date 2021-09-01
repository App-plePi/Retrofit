package com.example.retrofitexam

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // 모든 post 불러오기
    @GET("posts")
    fun getPosts(): Call<List<PostData>>

    // id 에 해당하는 post만 불러오기 @Path는 동적인 Url을 가능하게 함.
    @GET("posts/{id}")
    fun getPostFilterId(@Path("id") id: String): Call<PostData>

    // 포스트 Create
    @POST("posts")
    fun setPost(@Body postData: PostData): Call<PostData>

    // POST 방식 2
/*    @FormUrlEncoded
    @POST("posts")
    fun setPost(@Field("userId") userId : Int, @Field("id") id: Int,
    @Field("title") title : String, @Field("body") body : String) : Call<PostData>*/

}