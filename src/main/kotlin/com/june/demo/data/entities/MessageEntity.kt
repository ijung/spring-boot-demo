package com.june.demo.data.entities

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener::class)
data class MessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:JsonProperty
    var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    var userId: Long,

    @Column(name = "message", nullable = false)
    var message: String,

    @CreatedDate
    @Column(name = "created_date", nullable = true, updatable = false)
    @get:JsonProperty
    var createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = true)
    @get:JsonProperty
    var lastModifiedDate: LocalDateTime? = null
)