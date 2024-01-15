package src;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.FloatControl;

class Sound extends JFrame implements ActionListener {
    JButton btn = new JButton("Play Sound");
    SoundEffect sound;
    private LinkedList<SoundEffect> sounds;
    float volume = -30, dv = 5;

    public Sound() {
        setSize(300, 100);
        setLocation(400, 300);
        JPanel jp = new JPanel();
        btn.addActionListener(this);
        jp.add(btn);
        add(jp);
        pack();
        for (int i = 0; i < 10; i++) {
            sounds.add(new SoundEffect("stereo-madness-geometry-dash"));
        }
    }

    public void actionPerformed(ActionEvent ae) {
        SoundEffect sound = sounds.get(0);
        FloatControl gainControl = (FloatControl) sound.c.getControl(FloatControl.Type.MASTER_GAIN);
        volume += dv;
        if (volume == -30 || volume == 5) {
            dv *= -1;
        }
        gainControl.setValue(volume);
        System.out.println(volume);
        sound.play();
        sound.stop();
        sounds.remove(0);
        sounds.add(sound);
    }

    public static void main(String args[]) {
        new Sound().setVisible(true);
    }
}