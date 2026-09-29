// OnlineExam.java

import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class OnlineExam extends JFrame implements ActionListener {
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int[] userAnswers;
    private JLabel questionLabel;
    private JLabel timerLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    
    private JButton previousButton, nextButton, submitButton, startButton;
    private JButton continueButton;

    private JTextField nameField;
    private JTextField rollNumberField;
    private JComboBox<String> departmentBox;
    private JComboBox<String> semesterBox;

    private String studentName;
    private String rollNumber;
    private String department;
    private String semester;

    private Timer timer;
    private int timeRemaining = 60;

    public OnlineExam() {
        questions = new ArrayList<>();
        userAnswers = new int[10]; 
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1; 
        }
        loadQuestions();
        showStartScreen();
    }

    private void loadQuestions() {
        // questions in the ArrayList
        questions.add(new Question("The Taj Mahal stands on the bank of which river?", new String[]{"Ganga", "Yamuna", "Narmada", "Godavari"}, 1));
        questions.add(new Question("A student scores 60, 70, 80 and 90 in four tests. What is the mean score?", new String[]{"75", "80", "85", "90"}, 0));
        questions.add(new Question("Which organ in the human body is primarily responsible for filtering waste products from the blood?", new String[]{"Heart", "Lungs", "Kidneys", "Stomach"}, 2));
        questions.add(new Question("If an object travels 100 metres in 20 seconds, what is its average speed?", new String[]{"5 m/s", "10 m/s", "15 m/s", "20 m/s"}, 0));
        questions.add(new Question("Who wrote the Harry Potter Series?", new String[]{"Charles Dickens", "J.K. Rowling", "Mark Twain", "Jane Austen"}, 1));
        questions.add(new Question("Which physical quantity is measured in Hertz (Hz)?", new String[]{"Frequency", "Voltage", "Current", "Power"}, 0));
        questions.add(new Question("What is the chemical symbol for gold?", new String[]{"Ag", "Au", "Gd", "Go"}, 1));
        questions.add(new Question("Which gas do plants absorb from the atmosphere?", new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"}, 1));
        questions.add(new Question("If the probability of an event occurring is 0.7, what is the probability of it not occurring?", new String[]{"1.3", "0.7", "0.3", "1.7"}, 2));
        questions.add(new Question("What is the smallest planet in our solar system?", new String[]{"Mercury", "Venus", "Earth", "Mars"}, 0));
    }

    private void showStartScreen() {

    setTitle("QuizSphere");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel startPanel = new JPanel();
    startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));

    JLabel titleLabel = new JLabel("QUIZSPHERE");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel subtitleLabel =
            new JLabel("Challenge Your Knowledge");
    subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel infoLabel =
            new JLabel("10 Questions  |  Time Limit: 60 Seconds");
    infoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    startButton = new JButton("START EXAM");
    startButton.setFont(new Font("Arial", Font.BOLD, 16));
    startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    startButton.addActionListener(e -> startExam());

    startPanel.add(Box.createVerticalGlue());
    startPanel.add(titleLabel);
    startPanel.add(Box.createVerticalStrut(15));
    startPanel.add(subtitleLabel);
    startPanel.add(Box.createVerticalStrut(20));
    startPanel.add(infoLabel);
    startPanel.add(Box.createVerticalStrut(30));
    startPanel.add(startButton);
    startPanel.add(Box.createVerticalGlue());

    add(startPanel, BorderLayout.CENTER);

    setLocationRelativeTo(null);
    setVisible(true);
}

    //startexam()
    private void startExam() {

    getContentPane().removeAll();

    showStudentDetails();

    revalidate();
    repaint();
}


    private void showStudentDetails() {

    setTitle("Student Details");
    setSize(600, 400);
    setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JLabel titleLabel = new JLabel("STUDENT DETAILS");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));

    JLabel nameLabel = new JLabel("Student Name:");
    nameField = new JTextField();

    JLabel rollLabel = new JLabel("Roll Number:");
    rollNumberField = new JTextField();

JLabel departmentLabel = new JLabel("Department:");

    departmentBox = new JComboBox<>(
            new String[]{
                    "Computer Science",
                    "Data Science",
                    "Other"
            }
    );

    JLabel semesterLabel = new JLabel("Semester:");

    semesterBox = new JComboBox<>(
            new String[]{
                    "Semester I",
                    "Semester II",
                    "Semester III",
                    "Semester IV",
                    "Semester V",
                    "Semester VI",
                    "Semester VII",
                    "Semester VIII"
            }
    );

    formPanel.add(nameLabel);
    formPanel.add(nameField);

    formPanel.add(rollLabel);
    formPanel.add(rollNumberField);

    formPanel.add(departmentLabel);
    formPanel.add(departmentBox);

    formPanel.add(semesterLabel);
    formPanel.add(semesterBox);

    continueButton = new JButton("CONTINUE");
    continueButton.setFont(new Font("Arial", Font.BOLD, 16));
    continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    continueButton.addActionListener(e -> continueToExam());

    mainPanel.add(Box.createVerticalStrut(30));
    mainPanel.add(titleLabel);
    mainPanel.add(Box.createVerticalStrut(30));
    mainPanel.add(formPanel);
    mainPanel.add(Box.createVerticalStrut(30));
    mainPanel.add(continueButton);

    add(mainPanel, BorderLayout.CENTER);

    setLocationRelativeTo(null);
    setVisible(true);
}

    private void continueToExam() {
        studentName = nameField.getText().trim();
        rollNumber = rollNumberField.getText().trim();
        department = (String) departmentBox.getSelectedItem();
        semester = (String) semesterBox.getSelectedItem();

        if (studentName.isEmpty() || rollNumber.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all the details.",
                    "Incomplete Details",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        getContentPane().removeAll();
        setupUI();
        startTimer();

        revalidate();
        repaint();
    }

    private void setupUI() {
        setTitle("QuizSphere");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());


        JLabel titleLabel = new JLabel("QuizSphere");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
         
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        //add(questionLabel, BorderLayout.CENTER);

        timerLabel = new JLabel("Time Left: 01:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(timerLabel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(questionLabel, BorderLayout.NORTH);
        centerPanel.add(optionsPanel, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit");
    
        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        submitButton.addActionListener(this);

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);


        displayQuestion(currentQuestionIndex);

        setLocationRelativeTo(null); //open in center of the screen
        setVisible(true);
    }

    private void displayQuestion(int index) {
        Question question = questions.get(index);
        questionLabel.setText("Q" + (index + 1) + ": " + question.getQuestionText());
        String[] options = question.getOptions();
        optionsGroup.clearSelection();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setSelected(false);
        }
        //to retstore previously selecte answer when user navigates back to the question
        if (userAnswers[index] != -1) {
            optionButtons[userAnswers[index]].setSelected(true);
        }

        previousButton.setEnabled(index > 0);
        nextButton.setEnabled(index < questions.size() - 1);
    }

    private void saveanswer() {
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                userAnswers[currentQuestionIndex] = i;
                return;
            }
        }
        userAnswers[currentQuestionIndex] = -1; // No answer selected
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previousButton) {
    saveanswer();

    if (currentQuestionIndex > 0) {
        currentQuestionIndex--;
        displayQuestion(currentQuestionIndex);
    }
}
else if (e.getSource() == nextButton) {
    saveanswer();

    if (currentQuestionIndex < questions.size() - 1) {
        currentQuestionIndex++;
        displayQuestion(currentQuestionIndex);
    }
}
 else if (e.getSource() == submitButton) {
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to submit the exam?", "Confirm Submission", JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.YES_OPTION) {
            saveanswer();
            submitExam();
        }
    }

}
    private void startTimer() {
        timer = new Timer(1000, e -> {
            timeRemaining--;
             int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;

        timerLabel.setText(
                String.format("Time Left: %02d:%02d", minutes, seconds)
        );

        if (timeRemaining <= 0) {

            timer.stop();

            JOptionPane.showMessageDialog(this, "Time is over! Your exam will be submitted automatically.", "Time Up", JOptionPane.WARNING_MESSAGE);
                submitExam();
            }
        });
        timer.start();
    }

   private void submitExam() {

    if (timer != null) {
        timer.stop();
    }

    saveanswer();

    int correctCount = 0;
    int unansweredCount = 0;

    for (int i = 0; i < questions.size(); i++) {

        if (userAnswers[i] == -1) {
            unansweredCount++;
        }
        else if (userAnswers[i] ==
                questions.get(i).getCorrectAnswerIndex()) {

            correctCount++;
        }
    }

    int totalQuestions = questions.size();

    int wrongAnswers =
            totalQuestions - correctCount - unansweredCount;

    double percentage =
            (correctCount / (double) totalQuestions) * 100;

    String result = percentage >= 40 ? "PASS" : "FAIL";

    String message =
            "        EXAM COMPLETED        "
            + "\n\n"
            + "Student Name : " + studentName
            + "\n"
            + "Roll Number  : " + rollNumber
            + "\n"
            + "Department   : " + department
            + "\n"
            + "Semester     : " + semester
            + "\n\n"
            + "Total Questions : " + totalQuestions
            + "\n"
            + "Correct Answers : " + correctCount
            + "\n"
            + "Wrong Answers   : " + wrongAnswers
            + "\n"
            + "Unanswered      : " + unansweredCount
            + "\n"
            + "Percentage      : "
            + String.format("%.2f", percentage) + "%"
            + "\n\n"
            + "Result: " + result;

    JOptionPane.showMessageDialog(
            this,
            message,
            "Exam Result",
            JOptionPane.INFORMATION_MESSAGE
    );

    System.exit(0);
}
    public static void main(String[] args) {
        new OnlineExam();
    }

}