package lesson5;

import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao(new Configuration().configure("hibernate.cfg.xml").buildSessionFactory());
        for (int i = 0; i < 1000; i++) {
            dao.save(new Student(null, "student_" + i, "mark_" + i));
        }

        System.out.println(dao.studentFindById((long)1));
        dao.save(new Student((long)1, "qwe", "qwe"));
        System.out.println(dao.studentFindById((long)1));

        System.out.println(dao.studentFindById((long)11));
        dao.deleteById((long)11);
        System.out.println(dao.studentFindById((long)11));
    }
}
