package com.nhsoft.lemon.mapstruct;

import com.nhsoft.lemon.dto.CourseDTO;
import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.model.Course;
import com.nhsoft.lemon.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wanglei
 */
@Mapper
public interface CourseDTOConverter {

    CourseDTOConverter INSTANCE  =  Mappers.getMapper(CourseDTOConverter.class);

    @Mappings({
            @Mapping(source = "couId",target = "couId"),
            @Mapping(source = "couName",target = "couName"),
            @Mapping(source = "couNo",target = "couNo"),
    })
    CourseDTO domain2dto(Course course);

    List<CourseDTO> domain2dto(List<Course> courses);
}
