package com.csg.panoramix.client

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("callback")
class Callbacks(private val callback: Callback) {
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
class CallbackImpl: Callback {
  override fun onEventOne() = TODO("not implemented")
  override fun onEventTwo() = TODO("not implemented")
  override fun onEventThree() = TODO("not implemented")
}
