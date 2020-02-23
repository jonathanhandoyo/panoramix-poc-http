package com.csg.panoramix.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@EnableConfigurationProperties
@SpringBootApplication
class PanoramixClientApplication(private val client: Client) {

  @EventListener(ApplicationReadyEvent::class)
  fun onApplicationReady(e: ApplicationReadyEvent) = client.register()
}

fun main(args: Array<String>) {
  runApplication<PanoramixClientApplication>(*args)
}
