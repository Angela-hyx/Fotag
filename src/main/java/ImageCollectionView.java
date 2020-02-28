import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ImageCollectionView extends JPanel implements Observer {
    private Model model;

    public ImageCollectionView(Model model) {
        this.model = model;

        setPreferredSize(new Dimension(800, 540));
        setBackground(Color.WHITE);

        int num_images = 0;



        try {
            FileInputStream file1 = new FileInputStream("log/names.log");
            ObjectInputStream in1 = new ObjectInputStream(file1);
            ArrayList<String> names = (ArrayList) in1.readObject();
            int length = names.size();
            num_images = length;
            model.setNames(names, length);
            ArrayList<String> a = model.getNames();
            in1.close();
            file1.close();

            FileInputStream file2 = new FileInputStream("log/creations.log");
            ObjectInputStream in2 = new ObjectInputStream(file2);
            ArrayList<LocalDateTime> creations = (ArrayList) in2.readObject();
            model.setCreations(creations, length);
            in2.close();
            file2.close();

            for (int i=0; i<length; ++i) {
                BufferedImage img = ImageIO.read(new File("log/"+i+".png"));
                model.addImage(img);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        int Layout = model.getLayout();
        if (Layout == 0) {
            // grid layout
            setLayout(new GridLayout(2,3));
            this.removeAll();
            for (int i=0; i<num_images; ++i) {
                int star = model.getStar();
                int rating = model.getRating(i);
                if (rating >= star || star == 0) {
                    model.changeImageCounter(i);
                    addGridImageView();
                }
            }
        } else {
            // list layout
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.removeAll();
            for (int i=0; i<num_images; ++i) {
                int star = model.getStar();
                int rating = model.getRating(i);
                if (rating >= star || star == 0) {
                    model.changeImageCounter(i);
                    addListImageView();
                }
            }
        }
    }

    public void addGridImageView() {
        GridImageView gridImageView = new GridImageView(model);
        model.addObserver(gridImageView);
        this.add(gridImageView);
    }

    public void addListImageView() {
        ListImageView listImageView = new ListImageView(model);
        model.addObserver(listImageView);
        this.add(listImageView);
    }

    public void setupGridLayout(int length) {
        setLayout(new GridLayout(2,3));
        this.removeAll();
        for (int i=0; i<length; ++i) {
            int star = model.getStar();
            int rating = model.getRating(i);
            if (rating >= star || star == 0) {
                model.changeImageCounter(i);
                addGridImageView();
            }
        }
    }
    public void setupListLayout(int length) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.removeAll();
        for (int i=0; i<length; ++i) {
            int star = model.getStar();
            int rating = model.getRating(i);
            if (rating >= star || star == 0) {
                model.changeImageCounter(i);
                addListImageView();
            }
        }
    }

    public void update(Object observable) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<BufferedImage> images = model.getImages();
            int length = images.size();
            System.out.println(length);

            int Layout = model.getLayout();
            if (Layout == 0) {
                // grid layout
                setupGridLayout(length);
            } else {
                // list layout
                setupListLayout(length);
            }
            repaint();
            revalidate();
        });
    }
}
