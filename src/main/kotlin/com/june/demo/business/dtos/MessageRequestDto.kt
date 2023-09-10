package com.june.demo.business.dtos

import com.june.demo.data.entities.MessageEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

data class MessageRequestDto(
    val userId: Long,
    val message: String,
) {
    @Mapper
    abstract class DtoMapper {
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdDate", ignore = true)
        @Mapping(target = "lastModifiedDate", ignore = true)
        abstract fun toMessageEntity(dto: MessageRequestDto): MessageEntity

        abstract fun updateMessageEntity(dto: MessageRequestDto, @MappingTarget entity: MessageEntity)
    }
    companion object {
        val Mapper: DtoMapper = Mappers.getMapper(DtoMapper::class.java)
    }
}