import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TopPanel extends JPanel {
    public TopPanel(){
        this.setPreferredSize(new Dimension(400, 70));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleText = new JLabel("TODO LIST");
        titleText.setPreferredSize(new Dimension(200, 40));
        titleText.setAlignmentX(Component.CENTER_ALIGNMENT);

        LocalDateTime time = LocalDateTime.now();
        JLabel dayLabel = new JLabel(time.format(DateTimeFormatter.ofPattern("EEEE, d/M/y")));
        dayLabel.setPreferredSize(new Dimension(200, 40));
        dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(titleText);
        this.add(dayLabel);
    }
}
