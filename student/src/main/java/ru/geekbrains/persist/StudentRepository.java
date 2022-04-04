package ru.geekbrains.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {
    private final Map<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.save(new Student(1, "student 1", 21));
        this.save(new Student(2, "student 2", 22));
        this.save(new Student(3, "student 3", 23));
        id.set(3);
    }

    public void save(Student student) {
        if (student.getId() == 0) {
            student.setId(this.id.incrementAndGet());
        }
        students.put(student.getId(), student);
    }

    public void remove(long id) {
        students.remove(id);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public Optional<Student> getStudentById(long id) {
        return Optional.ofNullable(students.get(id));
    }
}
