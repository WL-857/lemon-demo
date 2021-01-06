package com.nhsoft.lemon.mapstruct;

import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.dto.TeacherDTO;
import com.nhsoft.lemon.model.Student;
import com.nhsoft.lemon.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wanglei
 */
@Mapper
public interface TeacherDTOConverter {

    TeacherDTOConverter INSTANCE  =  Mappers.getMapper(TeacherDTOConverter.class);

    @Mappings({
            @Mapping(source = "teachId",target = "teachId"),
            @Mapping(source = "teachName",target = "teachName"),
            @Mapping(source = "teachNo",target = "teachNo"),
            @Mapping(source = "teachPhone",target = "teachPhone")
    })
    TeacherDTO domain2dto(Teacher teacher);

    List<TeacherDTO> domain2dto(List<Teacher> teachers);
}
