package com.tioeun.a20191120_02_userlistpractice.datas

import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class User : Serializable{

    var loginId = ""
    var name = ""
    var createdAt = Calendar.getInstance()

    var category:Category? = null

    val printTimeFormat = SimpleDateFormat("yy년 M월 d일")

    fun getFormattedCreatedAt() : String {
        return printTimeFormat.format(this.createdAt.time)
    }

    companion object {

        val serverTimeParseFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")


        fun getUserFromJson(userJson: JSONObject) : User {
            var userObject = User()
//            왼쪽 : 우리가 만든 클래스 변수
//            오른쪽 : 서버에서 내려주는 JSON 활용
            userObject.loginId = userJson.getString("login_id")
            userObject.name = userJson.getString("name")

            val testCreatedAt = userJson.getString("created_at")
//            String으로 따낸 변수를 => 어떻게 Calendar?


            userObject.createdAt.time = serverTimeParseFormat.parse(testCreatedAt)  //Date 타입으로 변환.그 결과를 createdAt에 세팅

            userObject.category = Category.getCategoryFromJson(userJson.getJSONObject("category"))
            return userObject
        }
    }

}