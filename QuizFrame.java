import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizFrame extends JFrame implements ActionListener {
    Question[] questions;
    int current = 0, score = 0;
    JLabel questionLabel;
    JRadioButton[] options;
    ButtonGroup bg;
    JButton nextBtn;

    public QuizFrame() {
        questions = new Question[] {
            new Question("What is the capital of France?", new String[] {"Berlin", "London", "Paris", "Madrid"}, 2),
            new Question("2 + 2 = ?", new String[] {"3", "4", "5", "6"}, 1),
            new Question("Java is a ___?", new String[] {"Language", "Fruit", "Tool", "Browser"}, 0)
        };

        setTitle("Online Quiz");
        setLayout(new GridLayout(6, 1));
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        add(questionLabel);

        options = new JRadioButton[4];
        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            bg.add(options[i]);
            add(options[i]);
        }

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(this);
        add(nextBtn);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        bg.clearSelection();
        questionLabel.setText("Q" + (current + 1) + ": " + questions[current].question);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[current].options[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) selected = i;
        }

        if (selected == questions[current].correctAnswerIndex) score++;

        current++;
        if (current < questions.length) {
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over! Your Score: " + score + "/" + questions.length);
            System.exit(0);
        }
    }
}
