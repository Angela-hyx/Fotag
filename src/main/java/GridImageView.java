import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GridImageView  extends JPanel implements Observer {
    private Model model;

    public GridImageView(Model model) {
        this.model = model;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        ArrayList<BufferedImage> images = model.getImages();
        ArrayList<String> names = model.getNames();
        ArrayList<LocalDateTime> creations = model.getCreations();

        int imageCounter = model.getImageCounter();
        BufferedImage img = images.get(imageCounter);

//        int nW = img.getWidth();
//        int nH = img.getHeight();
//        BufferedImage nImg = new BufferedImage(nW, nH, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = nImg.createGraphics();
//        g2d.drawImage(img, 0, 0, null);
//        g2d.dispose();

        JLabel imglabel = new JLabel();
        Icon image = new ImageIcon(img);
        imglabel.setIcon(image);
        imglabel.setOpaque(true);

        String name = names.get(imageCounter);
        JLabel namelabel = new JLabel(name);

        LocalDateTime creation = creations.get(imageCounter);
        String date = creation.toString();
        JLabel creationlabel = new JLabel(date);

        Rating ratingView = new Rating(model, imageCounter);
        model.addObserver(ratingView);


        this.add(imglabel);
        this.add(namelabel);
        this.add(creationlabel);
        this.add(ratingView);


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
        System.out.println("Model changed in GridImageView!");
    }
}
