package com.csg.panoramix.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PanoramixServerApplication

fun main(args: Array<String>) {
  runApplication<PanoramixServerApplication>(*args)
}
