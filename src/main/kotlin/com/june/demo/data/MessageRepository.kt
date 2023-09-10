package com.june.demo.data

import com.june.demo.data.entities.MessageEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: CrudRepository<MessageEntity, Long>
