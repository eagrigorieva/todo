package com.eagrigorieva.mapper;

import com.eagrigorieva.dto.IntegrationTaskDto;
import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.enumeration.TaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IntegrationTaskMapper {
    String PREFIX = "I-";

    @Mapping(target = "id", expression = "java(PREFIX + val.getId())")
    TaskDto toTaskDto(IntegrationTaskDto val);

    List<TaskDto> toTaskDto(List<IntegrationTaskDto> val);

    default TaskStatus toTaskStatus(boolean status){
        return status ? TaskStatus.COMPLETED : TaskStatus.CREATED;
    }
}
