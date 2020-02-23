package com.csg.panoramix.server

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

interface AbstractRepository<T, I> {
  fun deleteAll()
  fun exists(entity: T): Boolean
  fun findAll(): List<T>
  fun findById(id: I): T?
  fun save(entity: T): T
}

@Repository
class Processes: AbstractRepository<Process, String> {

  private val collection = ConcurrentHashMap<String, Process>()

  override fun deleteAll(): Unit = collection.clear()
  override fun exists(entity: Process): Boolean = collection.any { it.value == entity }
  override fun findAll(): List<Process> = collection.map { it.value }
  override fun findById(id: String): Process? = collection[id]
  override fun save(entity: Process): Process = entity.copy(id = entity.id ?: uuid()).let { collection[it.id!!] = it; it }
}

@Repository
class Registrations: AbstractRepository<Registration, String> {

  private val collection = ConcurrentHashMap<String, Registration>()

  override fun deleteAll() = collection.clear()
  override fun exists(entity: Registration): Boolean = collection.any { it.value == entity }
  override fun findAll(): List<Registration> = collection.map { it.value }
  override fun findById(id: String): Registration? = collection[id]
  override fun save(entity: Registration): Registration { collection[entity.name] = entity; return entity }

  fun existsByName(name: String): Boolean = collection.any { it.value.name == name }
  fun existsByUrl(url: String): Boolean = collection.any { it.value.url == url }
}
