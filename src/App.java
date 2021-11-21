import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class App {
    public static JFrame frame;
    public static JPanel toDoPanel;

    public static JButton addButton;
    JButton clearButton;

    List<String> quotes = new ArrayList<>();


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

        quotes = getQuotes("res/quote.txt");
        Random r = new Random();
        int i = r.nextInt(quotes.size());
        JTextField quote = new JTextField(quotes.get(i));
        quote.setBackground(Color.PINK);
        quote.setFont(new Font(Font.DIALOG, Font.ITALIC, 14));
        quote.setPreferredSize(new Dimension(450, 40));
        quote.setHorizontalAlignment(SwingConstants.CENTER);
        nav.add(quote);

        //Set up frame
        loadFile(new File("Habit.txt"));
        frame.setJMenuBar(createMenuBar());
        frame.add(BorderLayout.NORTH, new TopPanel());
        frame.add(BorderLayout.CENTER, toDoPanel);
        frame.add(BorderLayout.SOUTH, nav);
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App().run();
    }


    public static @NotNull
    List<String> getQuotes(String pathName){
        List<String> answer = new ArrayList<>();
        try{
            File file = new File(pathName);
            String line;
            line = new String(Files.readAllBytes(Paths.get(pathName)));
            int i = 0;
            line = line.replace("\n", "").replace("\r", "");


            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(line);
            while (m.find()) {
                answer.add(m.group(1));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return answer;
    }

    public JMenuBar createMenuBar(){
        JMenu menu = new JMenu("Menu");
        JMenuBar mb = new JMenuBar();
        JMenuItem loadProfile = new JMenuItem("Open Profile");
        JMenuItem saveProfile = new JMenuItem("Save Profile");
        loadProfile.addActionListener(new LoadUserListener());
        saveProfile.addActionListener(new saveAction());
        menu.add(loadProfile);
        menu.add(saveProfile);
        mb.add(menu);
        return mb;
    }

    class AddAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Task task = new Task();
            toDoPanel.add(task);
            task.getTextField().requestFocus();
            //add some sound effect
            playSound("res/audio2.wav");
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
            playSound("res/audio3.wav");
            frame.repaint();
            frame.revalidate();
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

    public static void playSound(String pathName) {
        try {

            File file = new File(pathName);
//
//            InputStream bufferedIn = new BufferedInputStream(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
