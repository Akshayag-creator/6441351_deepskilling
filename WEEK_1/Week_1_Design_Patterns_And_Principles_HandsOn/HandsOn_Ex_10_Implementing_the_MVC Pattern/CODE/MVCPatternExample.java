public class MVCPatternExample 
{
    static class Student 
    {
        private String id;
        private String name;
        private String grade;

        public String getId() 
        {
            return id;
        }

        public void setId(String id) 
        {
            this.id = id;
        }

        public String getName() 
        {
            return name;
        }

        public void setName(String name) 
        {
            this.name = name;
        }

        public String getGrade() 
        {
            return grade;
        }

        public void setGrade(String grade) 
        {
            this.grade = grade;
        }
    }

    static class StudentView 
    {
        public void displayStudentDetails(String id, String name, String grade) 
        {
            System.out.println("Student Details:");
            System.out.println("ID    : " + id);
            System.out.println("Name  : " + name);
            System.out.println("Grade : " + grade);
        }
    }

    static class StudentController 
    {
        private Student model;
        private StudentView view;

        public StudentController(Student model, StudentView view) 
        {
            this.model = model;
            this.view = view;
        }

        public void setStudentName(String name) 
        {
            model.setName(name);
        }

        public void setStudentId(String id) 
        {
            model.setId(id);
        }

        public void setStudentGrade(String grade) 
        {
            model.setGrade(grade);
        }

        public String getStudentName() 
        {
            return model.getName();
        }

        public String getStudentId() 
        {
            return model.getId();
        }

        public String getStudentGrade() 
        {
            return model.getGrade();
        }

        public void updateView() 
        {
            view.displayStudentDetails(model.getId(), model.getName(), model.getGrade());
        }
    }

    public static void main(String[] args) 
    {
        Student student = new Student();
        student.setId("S001");
        student.setName("Alice");
        student.setGrade("A");

        StudentView view = new StudentView();

        StudentController controller = new StudentController(student, view);

        System.out.println("== Initial Student Record ==");
        controller.updateView();

        controller.setStudentName("Bob");
        controller.setStudentGrade("A+");

        System.out.println("\n== Updated Student Record ==");
        controller.updateView();
    }
}


