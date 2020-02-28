import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageView extends JPanel implements Observer {
    private Model model;
    private int position;

    public ImageView(Model model, int position) {
        this.model = model;
        this.position = position;

        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.LIGHT_GRAY);

        ArrayList<BufferedImage> images = model.getImages();
        BufferedImage img = images.get(position);
        JLabel imglabel = new JLabel();
        Icon image = new ImageIcon(img);
        imglabel.setIcon(image);
        imglabel.setOpaque(true);

        this.add(imglabel);
    }

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed!");
    }
}
