package com.csg.panoramix.server

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("processes")
class ProcessController(private val processes: Processes, private val registrations: Registrations) {

  @GetMapping
  fun get(): List<Process> = processes.findAll()

  @GetMapping("{p}")
  fun get(@PathVariable p: String): Process? = processes.findById(p)

  @PostMapping
  fun post(@RequestHeader("X-Registration-Name") name: String, @RequestBody body: Process): Process {
    return if (registrations.existsByName(name)) processes.save(body)
    else throw ResponseStatusException(HttpStatus.FORBIDDEN, "Unregistered")
  }
}

@RestController
@RequestMapping("register")
class RegistrationController(private val registrations: Registrations) {

  @GetMapping
  fun get(): List<Registration> = registrations.findAll()

  @PostMapping
  fun post(@RequestBody body: Registration): Boolean {
    if (!registrations.existsByName(body.name)) {
      registrations.save(body)
    }
    return true
  }
}
