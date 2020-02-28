import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ListImageView extends JPanel implements Observer {
    private Model model;

    public ListImageView(Model model) {
        this.model = model;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);

        ArrayList<BufferedImage> images = model.getImages();
        int imageCounter = model.getImageCounter();
        BufferedImage img = images.get(imageCounter);
        JLabel imglabel = new JLabel();
        Icon image = new ImageIcon(img);
        imglabel.setIcon(image);
        imglabel.setOpaque(true);

        Info infoView = new Info(model);
        model.addObserver(infoView);

        this.add(imglabel);
        this.add(infoView);


        imglabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                JFrame ImageFrame = new JFrame("View Image");
                ImageFrame.setMaximumSize(new Dimension(720, 540));
                ImageFrame.setSize(640, 480);

                ImageView imageView = new ImageView(model, imageCounter);
                ImageFrame.add(imageView);

                ImageFrame.setVisible(true);
            }
        });

    }

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed in ListImaeView!");
    }
}
