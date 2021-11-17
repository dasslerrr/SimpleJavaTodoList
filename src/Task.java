import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Task extends JPanel {
    private Color color;
    private JCheckBox checkBox;
    private JTextField name;
    private JButton addButton;
    private JButton deleteButton;
    private boolean isDone;

    public Task (){
        isDone = false;
        setPreferredSize(new Dimension(440, 40));
//        setLayout(new BorderLayout());

        name = new JTextField("");
        name.setBorder(BorderFactory.createEmptyBorder());
        name.setPreferredSize(new Dimension(300, 40));
        name.setBackground(Color.LIGHT_GRAY);
        name.setHorizontalAlignment(JLabel.LEFT);
        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    App.addButton.doClick();
                }
            }
        });
        name.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        this.add(name);

        addButton = new JButton("Done");
        addButton.setPreferredSize(new Dimension(60, 40));
        addButton.setBorder(BorderFactory.createEmptyBorder());
        addButton.setBackground(Color.cyan);
        addButton.addActionListener(new addButtonAction());
        this.add(addButton);

        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(60, 40));
        deleteButton.setBorder(BorderFactory.createEmptyBorder());
        deleteButton.setBackground(Color.cyan);
        deleteButton.addActionListener(new deleteButtonAction());
        this.add(deleteButton);
    }

    public void setName(String taskName){
        name.setText(taskName);
    }

    public String getName(){
        return name.getText();
    }

    public JTextField getTextField(){return name;}

    public boolean isDone(){
        return isDone;
    }

    public void setDone(){
        isDone = true;
    }

    public void setUndone(){
        isDone = false;
    }

    class addButtonAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (name.getBackground().equals(Color.LIGHT_GRAY)){
                name.setBackground(Color.GREEN);
                setDone();
                App.playSound("res/audio1.wav");
            }
            else{
                name.setBackground(Color.lightGray);
                setUndone();
            }
        }
    }

    class deleteButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton obj = (JButton) e.getSource();
//            System.out.println(obj.getParent().toString());
            App.toDoPanel.remove(obj.getParent());
            App.toDoPanel.repaint();
            App.toDoPanel.revalidate();
            App.playSound("res/audio3.wav");
        }
    }
}
