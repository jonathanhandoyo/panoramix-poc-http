package com.csg.panoramix.server

data class Process(
  val id: String? = null,
  val code: String,
  val name: String
)

data class Registration(
  val name: String,
  val url: String
)
