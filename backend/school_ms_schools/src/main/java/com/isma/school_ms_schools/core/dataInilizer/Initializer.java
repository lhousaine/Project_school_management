package com.isma.school_ms_schools.core.dataInilizer;

import com.isma.school_ms_schools.core.helpers.*;
import com.isma.school_ms_schools.data.Entities.*;
import com.isma.school_ms_schools.data.Repositories.*;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class Initializer {
    private static BranchRepository branchRepository;
    private static ClassroomRepository classroomRepository;
    private static SessionRepository sessionRepository;
    private static EducationLevelRepository educationLevelRepository;
    private static GroupRepository groupRepository;
    private static SchoolRepository schoolRepository;
    private static StudentCardRepository studentCardRepository;
    private static SubjectRepository subjectRepository;
    private static TimeTableRepository timeTableRepository;
    private static TrainingRepository trainingRepository;
    private static TeacherRepository teacherRepository;
    private static StudentRepository studentRepository;
    private static TeacherCardRepository teacherCardRepository;


    public Initializer(BranchRepository branchRepository, ClassroomRepository classroomRepository, SessionRepository sessionRepository,
                       EducationLevelRepository educationLevelRepository, GroupRepository groupRepository, SchoolRepository schoolRepository,
                       StudentCardRepository studentCardRepository, SubjectRepository subjectRepository, TimeTableRepository timeTableRepository,
                       TrainingRepository trainingRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, TeacherCardRepository teacherCardRepository) {
        Initializer.branchRepository = branchRepository;
        Initializer.classroomRepository = classroomRepository;
        Initializer.sessionRepository = sessionRepository;
        Initializer.educationLevelRepository = educationLevelRepository;
        Initializer.groupRepository = groupRepository;
        Initializer.schoolRepository = schoolRepository;
        Initializer.studentCardRepository = studentCardRepository;
        Initializer.subjectRepository = subjectRepository;
        Initializer.timeTableRepository = timeTableRepository;
        Initializer.trainingRepository = trainingRepository;
        Initializer.studentRepository = studentRepository;
        Initializer.teacherRepository = teacherRepository;
        Initializer.teacherCardRepository = teacherCardRepository;
    }

    public static void initData() {
        Address address1 = new Address("123 D", "rue 12", "Zagora", "Maroc");
        Address address2 = new Address("1125l", "rue 136", "Marrakech", "Maroc");
        ContactSchool contact1 = new ContactSchool("+212603708829", "lhou@gmail.com");
        ContactSchool contact2 = new ContactSchool("+212632568947", "settani@gmail.com");

        contact2.setFix("+212524235689");
        contact2.setFacebook("settani97");

        Branch branch1 = new Branch("Branch Zagora", "Branch Zagora Branch Zagora", contact1, address1);
        Branch branch2 = new Branch("Branch marrakech", "Branch marrakech Branch marrakech", contact2, address2);

        School school = new School();
        school.setName("Ecole Lhoussaine");
        school.setDescription("Ecole Lhoussaine Ecole Lhoussaine Ecole Lhoussaine");

        Classroom classroom1 = new Classroom("E12", 4.5, 6.5, 35);
        Classroom classroom2 = new Classroom("E13", 5, 8, 50);
        classroom1.setStatus(ClassroomStatus.EMPTY);
        classroomRepository.save(classroom1);
        classroom1.setStatus(ClassroomStatus.EMPTY);
        classroomRepository.save(classroom2);

        Subject subject1 = new Subject("Infomatique", "Infomatique Infomatique Infomatique ");
        Subject subject2 = new Subject("Math", "Math Math Math Math Math");
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        EducationLevel educationLevel1 = new EducationLevel("First Bac");
        educationLevel1.setDescription("Plusieurs variations de Lorem Ipsum peuvent être trouvées ici ou là, mais la majeure partie d'entre elles a été altérée par l'addition d'humour ou de mots aléatoires qui ne ressemblent pas une seconde à du texte standard.");
        EducationLevel educationLevel2 = new EducationLevel("Second Bac");
        educationLevel2.setDescription("Plusieurs variations de Lorem Ipsum peuvent être trouvées ici ou là, mais la majeure partie d'entre elles a été altérée par l'addition d'humour ou de mots aléatoires qui ne ressemblent pas une seconde à du texte standard.");

        Training training1 = new Training("PHYSIQUE", 1200.0);
        training1.setDescription("PHYSIQUE PHYSIQUE PHYSIQUE");
        Training training2 = new Training("S EXP", 1000.0);
        training2.setDescription("S EXP S EXP S EXP S EXP");

        TimeTable timeTable1 = new TimeTable("TTSB12");
        TimeTable timeTable2 = new TimeTable("TTSVT13");
        TimeTable timeTable3 = new TimeTable("TTSBPC1");
        TimeTable timeTable4 = new TimeTable("TTSBSVT");

        timeTableRepository.save(timeTable1);
        timeTableRepository.save(timeTable2);
        timeTableRepository.save(timeTable3);
        timeTableRepository.save(timeTable4);

        Group group1 = new Group("FBS1");
        group1.setTimeTable(timeTable1);
        Group group2 = new Group("FBS2");
        group2.setTimeTable(timeTable2);
        Group group3 = new Group("SBSPC1");
        group3.setTimeTable(timeTable3);
        Group group4 = new Group("SBSVT");
        group4.setTimeTable(timeTable4);


        training1.addSubject(subject1);
        training1.addSubject(subject2);
        trainingRepository.save(training1);
        trainingRepository.save(training2);

        group1.setTraining(training1);
        group2.setTraining(training1);
        group3.setTraining(training2);
        group4.setTraining(training2);

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);

        TeacherCard teacherCard1 = new TeacherCard();
        teacherCard1.setLevelName(educationLevel1.getName());
        teacherCard1.setTrainingName(training1.getName());

        TeacherCard teacherCard2 = new TeacherCard();
        teacherCard2.setLevelName(educationLevel2.getName());
        teacherCard2.setTrainingName(training2.getName());

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Lhoussaine");
        teacher1.setLastName("ouarhou");
        teacher1.setContact(contact1);
        teacher1.setAddress(address1);
        teacher1.addTeacherCard(teacherCard1);
        teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Abdrrahman");
        teacher2.setLastName("Settani");
        teacher2.setContact(contact2);
        teacher2.setAddress(address2);
        teacher2.addTeacherCard(teacherCard2);
        teacherRepository.save(teacher2);

        StudentCard studentCard1 = new StudentCard("SB", "SPC", "FBS1");
        StudentCard studentCard2 = new StudentCard("SB", "SVT", "FBS2");
        // studentCardRepository.save(studentCard1);
        // studentCardRepository.save(studentCard2);

        Contact con1 = new Contact("+212615895623", "lhoussaine@gmail.com");
        Contact con2 = new Contact("+21212458230", "settani@gmail.com");
        Contact con3 = new Contact("+21261589158", "youssef@gmail.com");
        Contact con4 = new Contact("+21212412568", "abdo@gmail.com");
        Person parent1 = new Person("lhou", "ouarhou", GenderEnum.MALE, "01-01-2012", con1, address2);
        Student student1 = new Student("lhou", "ouarhou", GenderEnum.MALE, "01-01-2012", con2,
                address2, parent1);
        Person parent2 = new Person("abdo", "settani", GenderEnum.MALE, "01-01-2012", con3, address2);
        Student student2 = new Student("abdo", "settani", GenderEnum.MALE, "01-01-2012", con4,
                address2, parent2);

        student1.setStudentCard(studentCard1);
        student2.setStudentCard(studentCard2);

        studentRepository.save(student1);
        studentRepository.save(student2);

        group1.addStudent(student1);
        group2.addStudent(student2);
        groupRepository.save(group1);
        groupRepository.save(group2);

        Session session1 = new Session(1.45, new Date(), subject1, classroom1, teacher1);
        Session session2 = new Session(1.45, new Date(), subject2, classroom2, teacher2);
        timeTable1.addSession(session1);
        timeTableRepository.save(timeTable1);
        timeTable2.addSession(session2);
        timeTableRepository.save(timeTable2);

        teacherCard1.setTeacher(teacher1);
        teacherCard1.addSession(session1);
        teacherCardRepository.save(teacherCard1);

        teacherCard2.setTeacher(teacher2);
        teacherCard2.addSession(session2);
        teacherCardRepository.save(teacherCard2);

        schoolRepository.save(school);

        branch1.setSchool(school);
        branch2.setSchool(school);

        branchRepository.save(branch1);
        branchRepository.save(branch2);

        educationLevel1.addTrainig(training1);
        educationLevel1.addTrainig(training2);
        educationLevel2.addTrainig(training1);
        educationLevelRepository.save(educationLevel1);
        training1.setEducationLevel(educationLevel1);
        training2.setEducationLevel(educationLevel1);
        trainingRepository.save(training1);
        trainingRepository.save(training2);
        educationLevelRepository.save(educationLevel2);
    }
}
