import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class Task extends JPanel {
    private Color color;
    private JCheckBox checkBox;
    private JTextField name;
    private JButton button;
    private boolean isDone;

    public Task (){
        isDone = false;
//        setPreferredSize(new Dimension(400, 40));
        setLayout(new BorderLayout());

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

    class EnterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            KeyAdapter adapter = new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        button.doClick();
                    }
                }
            };
        }
    }

    class buttonAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (name.getBackground().equals(Color.LIGHT_GRAY)){
                name.setBackground(Color.GREEN);
                setDone();
                App.playSound("src/Resources/audio1.wav");
            }
            else{
                name.setBackground(Color.lightGray);
                setUndone();
            }
        }
    }
}
