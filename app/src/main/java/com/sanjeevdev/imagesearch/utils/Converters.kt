package com.sanjeevdev.imagesearch.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sanjeevdev.imagesearch.models.Links
import com.sanjeevdev.imagesearch.models.Sponsorship
import com.sanjeevdev.imagesearch.models.Urls
import com.sanjeevdev.imagesearch.models.User

class Converters {

    @TypeConverter
    fun fromGroupTaskMemberList(value: List<Any>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Any>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromGroupTaskMemberList(value: Links): String {
        val gson = Gson()
        val type = object : TypeToken<Links>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromGroupTaskMemberList(value: Urls): String {
        val gson = Gson()
        val type = object : TypeToken<Urls>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromGroupTaskMemberList(value: Sponsorship): String {
        val gson = Gson()
        val type = object : TypeToken<Sponsorship>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromGroupTaskMemberList(value: User): String {
        val gson = Gson()
        val type = object : TypeToken<User>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskAnyList(value: String): List<Any> {
        val gson = Gson()
        val type = object : TypeToken<List<Any>>() {}.type
        return gson.fromJson(value, type)
    }
    @TypeConverter
    fun toGroupTaskLinksList(value: String): Links {
        val gson = Gson()
        val type = object : TypeToken<Links>() {}.type
        return gson.fromJson(value, type)
    }
    @TypeConverter
    fun toGroupTaskUserList(value: String): User {
        val gson = Gson()
        val type = object : TypeToken<User>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskUrlsList(value: String): Urls {
        val gson = Gson()
        val type = object : TypeToken<Urls>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): Sponsorship {
        val gson = Gson()
        val type = object : TypeToken<Sponsorship>() {}.type
        return gson.fromJson(value, type)
    }
}