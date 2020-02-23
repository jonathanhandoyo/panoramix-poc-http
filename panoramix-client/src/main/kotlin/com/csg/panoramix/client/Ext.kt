package com.csg.panoramix.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.util.*

val mapper = jacksonObjectMapper()

fun uuid(): String = UUID.randomUUID().toString()

fun OkHttpClient.request(request: Request): Response = this.newCall(request).execute()

fun Any.toJsonBody(): RequestBody = mapper.writeValueAsString(this).toRequestBody("application/json".toMediaType())

inline fun <reified T> Response.capture(): T? = use { r -> r.body?.string()?.let { s -> mapper.readValue<T>(s) } }
