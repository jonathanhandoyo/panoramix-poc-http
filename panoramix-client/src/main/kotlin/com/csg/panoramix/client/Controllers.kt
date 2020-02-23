package com.csg.panoramix.client

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("callback")
class CallbackRestController(private val callback: Callback) {
  @PostMapping
  fun onEvent(@RequestHeader("X-Event") event: Callback.Events, @RequestBody payload: Any) {
    when(event) {
      Callback.Events.ONE -> callback.onEventOne()
      Callback.Events.TWO -> callback.onEventTwo()
      Callback.Events.THREE -> callback.onEventThree()
    }
  }
}

@Service
class CustomCallbackImpl: Callback {
  override fun onEventOne() = println("overridden implementation")
}
