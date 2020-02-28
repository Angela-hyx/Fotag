import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Toolbar extends JPanel implements Observer {
    private Model model;

    public Toolbar(Model model) {
        this.model = model;

        setPreferredSize(new Dimension(800, 60));
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JToggleButton gridview = new JToggleButton();
        JToggleButton listview = new JToggleButton();
        JButton loadimages = new JButton("Load Images");
        JLabel filterby = new JLabel("            Filter by:   ");

        ImageIcon icon_grid = new ImageIcon("images/GridView.png");
        Image image_grid = icon_grid.getImage();
        Image newimg_grid = image_grid.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
        icon_grid = new ImageIcon(newimg_grid);
        gridview.setIcon(icon_grid);

        ImageIcon icon_list = new ImageIcon("images/ListView.png");
        Image image_list = icon_list.getImage();
        Image newimg_list = image_list.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
        icon_list = new ImageIcon(newimg_list);
        listview.setIcon(icon_list);

        gridview.setSelected(true);

        JToggleButton star1 = new JToggleButton("\u2605");
        JToggleButton star2 = new JToggleButton("\u2605");
        JToggleButton star3 = new JToggleButton("\u2605");
        JToggleButton star4 = new JToggleButton("\u2605");
        JToggleButton star5 = new JToggleButton("\u2605");
        star1.setForeground(new Color(1f,0f,0f,.2f ));
        star2.setForeground(new Color(1f,0f,0f,.2f ));
        star3.setForeground(new Color(1f,0f,0f,.2f ));
        star4.setForeground(new Color(1f,0f,0f,.2f ));
        star5.setForeground(new Color(1f,0f,0f,.2f ));
        star1.setSelected(false);
        star2.setSelected(false);
        star3.setSelected(false);
        star4.setSelected(false);
        star5.setSelected(false);

        this.add(gridview);
        this.add(listview);
        this.add(loadimages);
        this.add(filterby);
        this.add(star1);
        this.add(star2);
        this.add(star3);
        this.add(star4);
        this.add(star5);


        gridview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.changeLayout();
                listview.setSelected(!listview.isSelected());
            }
        });
        listview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.changeLayout();
                gridview.setSelected(!gridview.isSelected());
            }
        });
        loadimages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser upload_chooser = new JFileChooser();
                upload_chooser.setDialogTitle("Load Images");
                upload_chooser.setMultiSelectionEnabled(true);
                if (upload_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File[] files = upload_chooser.getSelectedFiles();
                    int num_files = files.length;
                    ArrayList<BufferedImage> imglist = new ArrayList<BufferedImage>();
                    ArrayList<String> namelist = new ArrayList<>();
                    ArrayList<LocalDateTime> creationlist = new ArrayList<>();
                    for (int i=0; i<num_files; ++i) {
//                        model.setNumImages();
                        try {
                            String name = files[i].getName();
                            namelist.add(name);

                            BufferedImage img = ImageIO.read(files[i]);
                            imglist.add(img);

                            BasicFileAttributes attr = Files.readAttributes(files[i].toPath(), BasicFileAttributes.class);
                            LocalDateTime creation = attr.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                            creationlist.add(creation);
                        } catch (IOException err) {
                            err.printStackTrace();
                        }
                    }
                    model.setImages(imglist, num_files);
                    model.setNames(namelist, num_files);
                    model.setCreations(creationlist, num_files);
                }
            }
        });

        star1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int star = model.getStar();
                if (star == 1) {
                    model.changeStar(0);
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
                    model.changeStar(1);
                    star1.setSelected(true);
                    star1.setForeground(Color.RED);
                }
                star2.setSelected(false);
                star2.setForeground(new Color(1f,0f,0f,.2f ));
                star3.setSelected(false);
                star3.setForeground(new Color(1f,0f,0f,.2f ));
                star4.setSelected(false);
                star4.setForeground(new Color(1f,0f,0f,.2f ));
                star5.setSelected(false);
                star5.setForeground(new Color(1f,0f,0f,.2f ));
                model.filter();
            }
        });
        star2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int star = model.getStar();
                if (star == 2) {
                    model.changeStar(0);
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                    star2.setSelected(false);
                    star2.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
                    model.changeStar(2);
                    star1.setSelected(true);
                    star1.setForeground(Color.RED);
                    star2.setSelected(true);
                    star2.setForeground(Color.RED);
                }
                star3.setSelected(false);
                star3.setForeground(new Color(1f,0f,0f,.2f ));
                star4.setSelected(false);
                star4.setForeground(new Color(1f,0f,0f,.2f ));
                star5.setSelected(false);
                star5.setForeground(new Color(1f,0f,0f,.2f ));
                model.filter();
            }
        });
        star3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int star = model.getStar();
                if (star == 3) {
                    model.changeStar(0);
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                    star2.setSelected(false);
                    star2.setForeground(new Color(1f,0f,0f,.2f ));
                    star3.setSelected(false);
                    star3.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
                    model.changeStar(3);
                    star1.setSelected(true);
                    star1.setForeground(Color.RED);
                    star2.setSelected(true);
                    star2.setForeground(Color.RED);
                    star3.setSelected(true);
                    star3.setForeground(Color.RED);
                }
                star4.setSelected(false);
                star4.setForeground(new Color(1f,0f,0f,.2f ));
                star5.setSelected(false);
                star5.setForeground(new Color(1f,0f,0f,.2f ));
                model.filter();
            }
        });
        star4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int star = model.getStar();
                if (star == 4) {
                    model.changeStar(0);
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                    star2.setSelected(false);
                    star2.setForeground(new Color(1f,0f,0f,.2f ));
                    star3.setSelected(false);
                    star3.setForeground(new Color(1f,0f,0f,.2f ));
                    star4.setSelected(false);
                    star4.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
                    model.changeStar(4);
                    star1.setSelected(true);
                    star1.setForeground(Color.RED);
                    star2.setSelected(true);
                    star2.setForeground(Color.RED);
                    star3.setSelected(true);
                    star3.setForeground(Color.RED);
                    star4.setSelected(true);
                    star4.setForeground(Color.RED);
                }
                star5.setSelected(false);
                star5.setForeground(new Color(1f,0f,0f,.2f ));
                model.filter();
            }
        });
        star5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int star = model.getStar();
                if (star == 5) {
                    model.changeStar(0);
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                    star2.setSelected(false);
                    star2.setForeground(new Color(1f,0f,0f,.2f ));
                    star3.setSelected(false);
                    star3.setForeground(new Color(1f,0f,0f,.2f ));
                    star4.setSelected(false);
                    star4.setForeground(new Color(1f,0f,0f,.2f ));
                    star5.setSelected(false);
                    star5.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
                    model.changeStar(5);
                    star1.setSelected(true);
                    star1.setForeground(Color.RED);
                    star2.setSelected(true);
                    star2.setForeground(Color.RED);
                    star3.setSelected(true);
                    star3.setForeground(Color.RED);
                    star4.setSelected(true);
                    star4.setForeground(Color.RED);
                    star5.setSelected(true);
                    star5.setForeground(Color.RED);
                }
                model.filter();
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int height = getHeight();

                gridview.setPreferredSize(new Dimension(height, height));
                listview.setPreferredSize(new Dimension(height, height));
                loadimages.setPreferredSize(new Dimension(height, height));
            }
        });
    }

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed in Toolbar!");
    }
}
