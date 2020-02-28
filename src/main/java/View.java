
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class View extends JFrame implements Observer {

    private Model model;

    /**
     * Create a new View.
     */
    public View(Model model) {
        // Add views to frame
        Toolbar toolbar = new Toolbar(model);
        ImageCollectionView imageCollectionView = new ImageCollectionView(model);

        JScrollPane scrollPane = new JScrollPane(imageCollectionView);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        this.add(toolbar, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        // Add observers
        model.addObserver(toolbar);
        model.addObserver(imageCollectionView);

        // Set up the window.
        this.setTitle("Fotag");
        this.setMinimumSize(new Dimension(128, 128));
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);


        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int height = getHeight();
                int width = getWidth();

                toolbar.setPreferredSize(new Dimension(width, (int)(height * 0.1)));
                imageCollectionView.setPreferredSize(new Dimension(width, (int)(height * 0.9)));
            }
        });
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                // save images
                try {
                    ArrayList<String> names = model.getNames();
                    FileOutputStream file1 = new FileOutputStream("log/names.log");
                    ObjectOutputStream out1 = new ObjectOutputStream(file1);
                    out1.writeObject(names);
                    out1.close();
                    file1.close();

                    ArrayList<LocalDateTime> creations = model.getCreations();
                    FileOutputStream file2 = new FileOutputStream("log/creations.log");
                    ObjectOutputStream out2 = new ObjectOutputStream(file2);
                    out2.writeObject(creations);
                    out2.close();
                    file2.close();

                    ArrayList<BufferedImage> images = model.getImages();
                    int length = images.size();
                    for (int i=0; i<length; ++i) {
                        File outputfile = new File("log/"+i+".png"); // change to jpg
                        ImageIO.write(images.get(i), "png", outputfile);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                e.getWindow().dispose();
            }
        });
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed in View!");
    }
}
