import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rating extends JPanel implements Observer {
    private Model model;

    private int position;

    public Rating(Model model, int position) {
        this.model = model;
        this.position = position;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);

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

        int currating = model.getRating(position);
        if (currating == 1) {
            star1.setSelected(true);
            star2.setSelected(false);
            star3.setSelected(false);
            star4.setSelected(false);
            star5.setSelected(false);
            star1.setForeground(Color.RED);
            star2.setForeground(new Color(1f,0f,0f,.2f ));
            star3.setForeground(new Color(1f,0f,0f,.2f ));
            star4.setForeground(new Color(1f,0f,0f,.2f ));
            star5.setForeground(new Color(1f,0f,0f,.2f ));
        } else if (currating == 2) {
            star1.setSelected(true);
            star2.setSelected(true);
            star3.setSelected(false);
            star4.setSelected(false);
            star5.setSelected(false);
            star1.setForeground(Color.RED);
            star2.setForeground(Color.RED);
            star3.setForeground(new Color(1f,0f,0f,.2f ));
            star4.setForeground(new Color(1f,0f,0f,.2f ));
            star5.setForeground(new Color(1f,0f,0f,.2f ));
        } else if (currating == 3) {
            star1.setSelected(true);
            star2.setSelected(true);
            star3.setSelected(true);
            star4.setSelected(false);
            star5.setSelected(false);
            star1.setForeground(Color.RED);
            star2.setForeground(Color.RED);
            star3.setForeground(Color.RED);
            star4.setForeground(new Color(1f,0f,0f,.2f ));
            star5.setForeground(new Color(1f,0f,0f,.2f ));
        } else if (currating == 4) {
            star1.setSelected(true);
            star2.setSelected(true);
            star3.setSelected(true);
            star4.setSelected(true);
            star5.setSelected(false);
            star1.setForeground(Color.RED);
            star2.setForeground(Color.RED);
            star3.setForeground(Color.RED);
            star4.setForeground(Color.RED);
            star5.setForeground(new Color(1f,0f,0f,.2f ));
        } else if (currating == 5) {
            star1.setSelected(true);
            star2.setSelected(true);
            star3.setSelected(true);
            star4.setSelected(true);
            star5.setSelected(true);
            star1.setForeground(Color.RED);
            star2.setForeground(Color.RED);
            star3.setForeground(Color.RED);
            star4.setForeground(Color.RED);
            star5.setForeground(Color.RED);
        }

        this.add(star1);
        this.add(star2);
        this.add(star3);
        this.add(star4);
        this.add(star5);


        star1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rating = model.getRating(position);
                if (rating == -1) {
                    model.addRating(position, 1);
                } else if (rating == 1) {
                    model.deleteRating(position);
                } else {
                    model.changeRating(position, 1);
                }

                if (rating == 1) {
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
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
                int rating = model.getRating(position);
                if (rating == -1) {
                    model.addRating(position, 2);
                } else if (rating == 2) {
                    model.deleteRating(position);
                } else {
                    model.changeRating(position, 2);
                }

                if (rating == 2) {
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                    star2.setSelected(false);
                    star2.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
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
                int rating = model.getRating(position);
                if (rating == -1) {
                    model.addRating(position, 3);
                } else if (rating == 3) {
                    model.deleteRating(position);
                } else {
                    model.changeRating(position, 3);
                }

                if (rating == 3) {
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                    star2.setSelected(false);
                    star2.setForeground(new Color(1f,0f,0f,.2f ));
                    star3.setSelected(false);
                    star3.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
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
                int rating = model.getRating(position);
                if (rating == -1) {
                    model.addRating(position, 4);
                } else if (rating == 4) {
                    model.deleteRating(position);
                } else {
                    model.changeRating(position, 4);
                }

                if (rating == 4) {
                    star1.setSelected(false);
                    star1.setForeground(new Color(1f,0f,0f,.2f ));
                    star2.setSelected(false);
                    star2.setForeground(new Color(1f,0f,0f,.2f ));
                    star3.setSelected(false);
                    star3.setForeground(new Color(1f,0f,0f,.2f ));
                    star4.setSelected(false);
                    star4.setForeground(new Color(1f,0f,0f,.2f ));
                } else {
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
                int rating = model.getRating(position);
                if (rating == -1) {
                    model.addRating(position, 5);
                } else if (rating == 5) {
                    model.deleteRating(position);
                } else {
                    model.changeRating(position, 5);
                }

                if (rating == 5) {
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
    }

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed in Rating!");
    }
}
