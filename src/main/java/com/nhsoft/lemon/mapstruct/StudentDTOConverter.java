package com.nhsoft.lemon.mapstruct;

import com.nhsoft.lemon.dto.StudentDTO;
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
public interface StudentDTOConverter {

    StudentDTOConverter INSTANCE  =  Mappers.getMapper(StudentDTOConverter.class);

    @Mappings({
            @Mapping(source = "stuId",target = "stuId"),
            @Mapping(source = "stuName",target = "stuName"),
            @Mapping(source = "stuSex",target = "stuSex"),
            @Mapping(source = "stuNo",target = "stuNo"),
            @Mapping(source = "stuPhone",target = "stuPhone"),
    })
    StudentDTO domain2dto(Student student);

    List<StudentDTO> domain2dto(List<Student> students);
}
