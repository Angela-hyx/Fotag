import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Info extends JPanel implements Observer {
    private Model model;

    public Info(Model model) {
        this.model = model;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        ArrayList<String> names = model.getNames();
        ArrayList<LocalDateTime> creations = model.getCreations();

        int imageCounter = model.getImageCounter();

        String name = names.get(imageCounter);
        JLabel namelabel = new JLabel(name);

        LocalDateTime creation = creations.get(imageCounter);
        String date = creation.toString();
        JLabel creationlabel = new JLabel(date);

        int position = model.getImageCounter();
        Rating ratingView = new Rating(model, position);
        model.addObserver(ratingView);

        this.add(namelabel);
        this.add(creationlabel);
        this.add(ratingView);
    }

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed in Toolbar!");
    }
}
