package com.csg.panoramix.client

import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Configuration
@ConfigurationProperties("panoramix")
class ClientConfig {
  lateinit var serverUrl: String
  lateinit var clientName: String
  lateinit var clientUrl: String
}

@Service
class Client(private val config: ClientConfig) {

  private val client = OkHttpClient()

  fun register() {
    client
      .request(
        Request.Builder()
          .url("${config.serverUrl}/register")
          .post(Registration(config.clientName, config.clientUrl).toJsonBody())
          .build()
      )
      .capture<Boolean>()
  }

  fun createProcess(process: Process) {
    client
      .request(
        Request.Builder()
          .url("${config.serverUrl}/processes")
          .header("X-Registration-Name", config.clientName)
          .post(process.toJsonBody())
          .build()
      )
      .capture<Process>()
  }
}

interface Callback {
  fun onEventOne() = println("defensive implementation")
  fun onEventTwo() = println("defensive implementation")
  fun onEventThree() = println("defensive implementation")

  enum class Events { ONE, TWO, THREE }
}
