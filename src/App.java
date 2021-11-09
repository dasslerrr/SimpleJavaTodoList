import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    JFrame frame;
    JPanel toDoPanel;

    JButton addButton;
    JButton clearButton;


    public void run(){


        //create Frame
        frame = new JFrame("To do List");

        //TodoPanel
        toDoPanel = new JPanel();
        toDoPanel.setBackground(Color.pink);
        Task firstTask = new Task();
        toDoPanel.add(firstTask);

        //Navigation
        Navigation nav = new Navigation();
        addButton = new JButton("Add new Task");
        addButton.setBackground(Color.orange);
        addButton.addActionListener(new AddAction());
        addButton.setPreferredSize(new Dimension(200, 40));
        nav.add(addButton);

        clearButton = new JButton("Clear all completed Task");
        clearButton.setBackground(Color.orange);
        clearButton.addActionListener(new ClearAction());
        clearButton.setPreferredSize(new Dimension(200, 40));
        nav.add(clearButton);


        //Set up frame
        frame.setJMenuBar(createMenuBar());
        frame.add(BorderLayout.NORTH, new TopPanel());
        frame.add(BorderLayout.CENTER, toDoPanel);
        frame.add(BorderLayout.SOUTH, nav);
        frame.setSize(500, 800);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App().run();
    }

    public JMenuBar createMenuBar(){
        JMenu menu = new JMenu("Menu");
        JMenuBar mb = new JMenuBar();
        JMenuItem user = new JMenuItem("User");
        JMenuItem backgroundChange = new JMenuItem("Change Background");
        menu.add(user);
        menu.add(backgroundChange);
        mb.add(menu);
        return mb;
    }

    class AddAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Task task = new Task();
            toDoPanel.add(task);
            frame.revalidate();
        }
    }

    class ClearAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Component item : toDoPanel.getComponents()){
                if (item instanceof Task){
                    if (((Task) item).isDone()){
                        toDoPanel.remove(item);
                    }
                }
            }
            frame.repaint();
        }
    }
}
