package com.linkcm.demo.repository.school;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.demo.entity.Student;

public interface StudentRepository extends BaseRepository<Student, Long>, StudentRepositoryCustom {

}
