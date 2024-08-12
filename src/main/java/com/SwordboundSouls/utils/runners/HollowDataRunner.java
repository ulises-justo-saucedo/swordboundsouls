package com.SwordboundSouls.utils.runners;

import com.SwordboundSouls.entity.Hollow;
import com.SwordboundSouls.repository.IHollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class HollowDataRunner implements CommandLineRunner {

    @Autowired
    private final IHollowRepository IHollowRepository;

    @Override
    public void run(String... args) throws Exception {
        if(IHollowRepository.count() == 0){
            IHollowRepository.saveAll(
                    List.of(
                            new Hollow("https://i.imgur.com/7fOisXy.jpg", 150, 5, 5, 10, 1, 20, "Fishbone D", "Fishbone D is a large humanoid Hollow with a fish-like mask and a dorsal fin on his back. His shoulders and arms are plated with what appears to be part of his mask, and his body is covered with dark gray stripes, resembling the scales of a fish."),
                            new Hollow("https://i.imgur.com/R5i08jm.jpg", 200, 35, 5, 25, 3, 50, "Hexapodus", "Hexapodus resembles a large six-legged insect. He has gray green skin with orange joints, as well as orange spots on his back. His mask is plain white with a humanoid face."),
                            new Hollow("https://i.imgur.com/7sNvLne.jpg", 100, 15, 50, 30, 5, 100, "Acidwire", "Acidwire is a large nāga-like Hollow with shoulder-length black hair and a skull-like mask with narrow yellow eyes; beneath this lies his original Human face. The majority of his muscular body is dark brown in color."),
                            new Hollow("https://i.imgur.com/z1Due6d.jpg", 200, 35, 40, 25, 4, 30, "Shrieker", "Shrieker stands at around two to three times the height of most Humans, with an ape-like body that leads him to stand on all fours during most occasions. He has shaggy black fur around his neck and shoulders, as well as in two lines."),
                            new Hollow("https://i.imgur.com/Flzy2gV.png", 50, 5, 25, 5, 1, 5, "Grand Fish", "Grand Fisher resembles a giant hamster with red bird-like hands and feet and a red tentacle-like limb that hangs from his forehead, similar to the lure of an anglerfish. The lure is vaguely Human-sized and can be transformed."),
                            new Hollow("https://i.imgur.com/zX3dij2.png", 500, 50, 25, 25, 10, 250, "The Demi-Hollow", "The Demi-Hollow is medium-sized, bipedal green Hollow with a mask resembling the face of a frog that has two long extensions coming out of the sides, resembling pigtails, and yellow eyes."),
                            new Hollow("https://i.imgur.com/N92oQ7A.png", 500, 50, 25, 35, 15, 1000, "Bulbous G", "Bulbous G is a large bipedal Hollow with an extremely muscular body, easily dwarfing the 197cm tall Yasutora Sado. His arms noticeably widen as they progress from his shoulders, culminating in almost cube-shaped fists as big as his mask."),
                            new Hollow("https://i.imgur.com/PFJHW28.png", 750, 100, 5, 150, 15, 1500, "Numb Chandelier", "Numb Chandelier is a large Hollow whose body is composed of twelve large, lavender tentacles of sufficient length for her to prop herself up between buildings. Her mask resembles a jellyfish, with six appendages at the bottom."),
                            new Hollow("https://i.imgur.com/ZsCPXuc.png", 500, 50, 150, 200, 20, 2500, "Metastacia", "In his natural state, he exists as a six-limbed creature with a large, flame-patterned mask and a flock of tentacles on his back. When fused with a body, the body takes on characteristics of his appearance."),
                            new Hollow("https://i.imgur.com/Q6rgQpb.png", 300, 350, 100, 250, 25, 3500, "Bawabawa", "Bawabawa is a giant, snake-like Hollow whose mask consists of a simple plate on his head with a pair of horns. He has large pink lips and equally large teeth."),
                            new Hollow("https://i.imgur.com/LoZGAfr.png", 500, 400, 500, 150, 35, 5000,"Runuganga", "Runuganga has a large, grey body and is made from sand. His arms are long, with the forearms being much thicker than his upper arms. Runuganga's head resembles a sand castle.")
                    )
            );
        }
    }
}
