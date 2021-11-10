import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task extends JPanel {
    private Color color;
    private JCheckBox checkBox;
    private JTextField name;
    private JButton button;
    private boolean isDone;

    public Task (){
        isDone = false;
        setPreferredSize(new Dimension(400, 40));
        setLayout(new BorderLayout());

        name = new JTextField("");
        name.setBorder(BorderFactory.createEmptyBorder());
        name.setPreferredSize(new Dimension(300, 40));
        name.setBackground(Color.LIGHT_GRAY);
        name.setHorizontalAlignment(JLabel.LEFT);
        this.add(name, BorderLayout.CENTER);

        button = new JButton("Complete");
        button.setPreferredSize(new Dimension(60, 40));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(Color.cyan);
        button.addActionListener(new buttonAction());
        this.add(button, BorderLayout.EAST);
    }

    public void setName(String taskName){
        name.setText(taskName);
    }

    public String getName(){
        return name.getText();
    }

    public boolean isDone(){
        return isDone;
    }

    public void setDone(){
        isDone = true;
    }

    public void setUndone(){
        isDone = false;
    }

    class buttonAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (name.getBackground().equals(Color.LIGHT_GRAY)){
                name.setBackground(Color.GREEN);
                setDone();
            }
            else{
                name.setBackground(Color.lightGray);
            }
        }
    }
}
