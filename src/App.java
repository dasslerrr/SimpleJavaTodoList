import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class App {
    JFrame frame;
    JPanel toDoPanel;

    public static JButton addButton;
    JButton clearButton;
    JButton saveButton;


    public void run(){


        //create Frame
        frame = new JFrame("To do List");

        //TodoPanel
        toDoPanel = new JPanel();
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

        saveButton = new JButton("Save user profile");
        saveButton.setBackground(Color.orange);
        saveButton.addActionListener(new saveAction());
        saveButton.setPreferredSize(new Dimension(200, 40));
        nav.add(saveButton);


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
        JMenuItem user = new JMenuItem("Load User");
        JMenuItem backgroundChange = new JMenuItem("Change Background");
        user.addActionListener(new LoadUserListener());
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
            task.getTextField().requestFocus();
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

    class LoadUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

    class saveAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileSave = new JFileChooser();
            fileSave.showOpenDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private void saveFile(File file){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Component item : toDoPanel.getComponents()){
                if (item instanceof Task){
                    writer.write(item.getName() + "\n");
                }
            }
            writer.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void loadFile(File file){
        ArrayList<String> tasks = new ArrayList<>();
        try{
            toDoPanel.removeAll();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null){
                Task task = new Task();
                task.setName(line);
                toDoPanel.add(task);
            }
            frame.revalidate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
