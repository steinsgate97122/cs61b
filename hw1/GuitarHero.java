import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString[] strings = new synthesizer.GuitarString[37];
        for (int i = 0; i < 37; i++) {
            double frequency = 440 * Math.pow(2.0, (i - 24.0) / 12.0);
            strings[i] = new synthesizer.GuitarString(frequency);
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) >= 0) {
                    strings[keyboard.indexOf(key)].pluck();
                } else {
                    continue;
                }
            }

        /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString string : strings) {
                sample += string.sample();
                string.tic();
            }

        /* play the sample on standard audio */
            StdAudio.play(sample);
        }
    }
}

