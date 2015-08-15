package JavaSwing;

/**
 * Created by Thanakorn on 8/15/15.
 */

import javax.swing.*;
import java.awt.*;

public class Minion extends JFrame {

    public Minion() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 1120);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
//*****bg
        g.setColor(new Color(246, 221, 79));

        g.fillRect(0, 0, 800, 1120);

//*****glasses
        g.setColor(new Color(37, 17, 19));
        g.fillRect(180, 342, 469, 54); // glasses 1

        g.setColor(new Color(37, 17, 19));
        g.fillRect(200, 334, 430, 68); // glasses 2

        g.setColor(new Color(37, 17, 19));
        g.fillOval(210, 254, 215, 215); // glasses 3_1

        g.setColor(new Color(37, 17, 19));
        g.fillOval(407, 254, 215, 215); // glasses 3_2

        g.setColor(new Color(255, 235, 76));
        g.fillOval(240, 284, 160, 160); // glasses 4_1

        g.setColor(new Color(255, 235, 76));
        g.fillOval(437, 284, 160, 160); // glasses 4_1

        g.setColor(new Color(255, 255, 255));
        g.fillOval(442, 290, 146, 146); // glasses _1

        g.setColor(new Color(255, 255, 255));
        g.fillOval(248, 290, 146, 146); // glasses 5_1

        g.setColor(new Color(37, 17, 19));
        g.fillOval(465, 333, 60, 60); // glasses 6_1

        g.setColor(new Color(37, 17, 19));
        g.fillOval(308, 333, 60, 60); // glasses 6_2

//*****shirt

        g.setColor(new Color(11, 58, 74)); //blue
        g.fillRoundRect(175, 503, 478, 402, 400, 300); // shirt1

        g.setColor(new Color(246, 221, 79)); //yellow
        g.fillRoundRect(175, 483, 478, 300, 250, 100); // shirt2

        g.setColor(new Color(11, 58, 74)); //blue
        g.fillRoundRect(175, 483, 478, 177, 250, 100); // shirt3

        g.setColor(new Color(246, 221, 79)); //yellow
        g.fillRoundRect(175, 482, 478, 134, 250, 100); // shirt4

        g.setColor(new Color(11, 58, 74)); //blue
        g.fillRect(271, 626, 290, 160); // shirt5
//g.fillOval(315, 470, 478, 402); // shirt1

//*****button
        g.setColor(new Color(37, 17, 19)); //brown
        g.fillOval(286, 638, 25, 25); //

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillOval(520, 638, 25, 25); //

//*****mouse

        g.setColor(new Color(255, 255, 255));
        g.fillOval(315, 470, 200, 116); // mouse1

        g.setColor(new Color(246, 221, 79));
        g.fillOval(283, 465, 266, 69); // mouse2

        g.setColor(new Color(0, 0, 0));
        g.fillOval(340, 542, 150, 42); // mouse4

        g.setColor(new Color(244, 156, 155));
        g.fillOval(352, 548, 126, 39); // mouse3

//*****legs left

        g.setColor(new Color(11, 58, 74)); //blue
        g.fillRect(303, 880, 89, 52);

        g.setColor(new Color(246, 221, 79)); //yellow
        g.fillRoundRect(268, 895, 70, 70, 65, 80);

//*****legs right

        g.setColor(new Color(11, 58, 74)); //blue
        g.fillRect(445, 880, 89, 52);

        g.setColor(new Color(246, 221, 79)); //yellow
        g.fillRoundRect(497, 894, 70, 70, 65, 80);

//*****shoes1 left

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillRoundRect(318, 930, 90, 56, 40, 40);

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillOval(312, 955, 100, 35);

//*****shoes2 right

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillRoundRect(428, 930, 90, 56, 40, 40);

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillOval(423, 955, 100, 35);

//*****bag
        {
            g.setColor(new Color(190, 204, 207)); //yellow
            int[] xPoints[] = {{350, 350, 415, 480, 480}};

            int[] yPoints[] = {{696, 786, 800, 786, 696}};
            int nPoints[] = {5};
            g.fillPolygon(xPoints[0], yPoints[0], nPoints[0]);
        }

        {
            g.setColor(new Color(11, 58, 74)); //blue
            int[] xPoints[] = {{352, 352, 415, 478, 478}};

            int[] yPoints[] = {{698, 784, 796, 784, 698}};
            int nPoints[] = {5};
            g.fillPolygon(xPoints[0], yPoints[0], nPoints[0]);
        }

//******hand
        g.setColor(new Color(37, 17, 19)); //brown
        int[] lHand_x[] = {{120, 120, 128, 129, 147, 177, 200, 207, 173, 175, 184, 184}};
        int[] lHand_y[] = {{823, 837, 837, 877, 918, 938, 942, 886, 853, 837, 837, 823}};
        int hand_Points[] = {12};
        g.fillPolygon(lHand_x[0], lHand_y[0], hand_Points[0]);

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillOval(170, 888, 70, 53);

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillOval(148, 855, 59, 70);

        g.setColor(new Color(37, 17, 19)); //brown
        int[] rHand_x[] = {{712, 712, 703, 702, 685, 652, 636, 626, 660, 657, 649, 649}};
        int[] rHand_y[] = {{823, 837, 837, 877, 918, 938, 942, 886, 853, 837, 837, 823}};

        g.fillPolygon(rHand_x[0], rHand_y[0], hand_Points[0]);

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillOval(592, 888, 70, 55);

        g.setColor(new Color(37, 17, 19)); //brown
        g.fillOval(628, 855, 59, 70);

//*****hair
        g.setColor(new Color(37, 17, 19)); //brown
        int[] hair_x[] = {{218, 244, 245, 218},
                {239, 275, 278, 239},
                {214, 274, 275, 214},
                {240, 280, 282, 240},
                {259, 312, 313, 260},
                {298, 325, 329, 298},
                {326, 344, 347, 326},
                {319, 355, 359, 319},
                {348, 378, 381, 349},
                {394, 403, 407, 394},
                {446, 429, 431, 446},//
                {460, 442, 447, 460},
                {497, 478, 479, 497},
                {508, 481, 484, 508},
                {526, 502, 508, 526},
                {558, 516, 516, 558},
                {596, 549, 551, 596},
                {612, 553, 555, 612},
                {598, 551, 554, 598},
                {610, 585, 587, 610}};

        int[] hair_y[] = {{212, 214, 212, 212},
                {166, 193, 192, 166},
                {159, 182, 180, 159},
                {157, 177, 175, 157},
                {129, 160, 158, 129},
                {129, 161, 159, 129},
                {118, 144, 145, 118},
                {89, 142, 142, 89},
                {101, 140, 140, 101},
                {117, 149, 149, 117},
                {80, 136, 138, 80},//
                {95, 137, 137, 95},
                {115, 143, 145, 115},
                {94, 145, 145, 94},
                {130, 160, 162, 130},
                {127, 158, 159, 127},
                {157, 175, 178, 157},
                {160, 180, 182, 160},
                {166, 186, 189, 166},
                {209, 211, 213, 209}};
        int hair_Points[] = {4};
        g.fillPolygon(hair_x[0], hair_y[0], hair_Points[0]);
        g.fillPolygon(hair_x[1], hair_y[1], hair_Points[0]);
        g.fillPolygon(hair_x[2], hair_y[2], hair_Points[0]);
        g.fillPolygon(hair_x[3], hair_y[3], hair_Points[0]);
        g.fillPolygon(hair_x[4], hair_y[4], hair_Points[0]);
        g.fillPolygon(hair_x[5], hair_y[5], hair_Points[0]);
        g.fillPolygon(hair_x[6], hair_y[6], hair_Points[0]);
        g.fillPolygon(hair_x[7], hair_y[7], hair_Points[0]);
        g.fillPolygon(hair_x[8], hair_y[8], hair_Points[0]);
        g.fillPolygon(hair_x[9], hair_y[9], hair_Points[0]);
        g.fillPolygon(hair_x[10], hair_y[10], hair_Points[0]);
        g.fillPolygon(hair_x[11], hair_y[11], hair_Points[0]);
        g.fillPolygon(hair_x[12], hair_y[12], hair_Points[0]);
        g.fillPolygon(hair_x[13], hair_y[13], hair_Points[0]);
        g.fillPolygon(hair_x[14], hair_y[14], hair_Points[0]);
        g.fillPolygon(hair_x[15], hair_y[15], hair_Points[0]);
        g.fillPolygon(hair_x[16], hair_y[16], hair_Points[0]);
        g.fillPolygon(hair_x[17], hair_y[17], hair_Points[0]);
        g.fillPolygon(hair_x[18], hair_y[18], hair_Points[0]);
        g.fillPolygon(hair_x[19], hair_y[19], hair_Points[0]);

    }

    public static void main(String[] args) {
        new Minion();

    }

}
