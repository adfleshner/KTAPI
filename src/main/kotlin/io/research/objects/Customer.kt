package io.research.objects

import com.google.gson.Gson

data class Customer(val id: Int?, val firstName: String, val lastName: String)

fun List<Customer>.toJson() : String {
    return Gson().toJson(this)
}

fun Customer.toJson(): String {
    return Gson().toJson(this)
}