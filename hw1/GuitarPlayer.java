import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Plays guitar from MIDI files.
 *
 * naive的理解：先把所有事件都放进一个track内，for循环处理这个track的所有event
 * 如果这个event对应MetaMessage就调整播放速度和歌词
 * 否则这个event就是音符时间，先推动tick到当前事件，然后处理这个事件，事件可能会打开或者关闭某个vol
 * sample()函数是多根弦叠加的音频信号，那么不同的组合可能会产生相同的声音，这个在音乐合成中也是个合理的现象
 *
 * @author Eli Lipsitz
 */
public class GuitarPlayer {
    private Sequence sequence = null;
    private GuitarString[] strings;
    private double[] vol;

    public GuitarPlayer(InputStream source) {
        try {
            sequence = MidiSystem.getSequence(source);
        } catch (IOException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public GuitarPlayer(File source) {
        try {
            sequence = MidiSystem.getSequence(source);
        } catch (IOException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    /*
    创建128根弦，初始化所有弦的音量为0
    440是A4的频率，69是A4对应的MIDI编号，i-69就代表当前音符举例A4有多少个半音，分母的12代表12个半音，即一个八度，每新增12，频率翻倍
    举个例子，69对应440Hz，81对应880Hz（高八度），57代表220Hz（低八度）
     */
    private void initialize() {
        strings = new GuitarString[128];
        vol = new double[128];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 69.0) / 12.0));
            vol[i] = 0.0;
        }
    }

    private void tic() {
        for (int i = 0; i < strings.length; i++) {
            if (vol[i] > 0.0) {
                strings[i].tic();
            }
        }
    }

    private double sample() {
        double sum = 0.0f;
        for (int i = 0; i < strings.length; i++) {
            sum += vol[i] * strings[i].sample();
        }
        return sum;
    }

    /*
    sequence.getTracks()包含了MIDI文件解析后的所有音轨（Track），每个Track都代表一种乐器或者一种节奏
    代码中将这些Track合并为一个Track，event也都合并了
     */
    public void play() {
        if (sequence == null) {
            return;
        }

        System.out.println("starting performance...");
        initialize();
        double bpm = 120;  // beats per min
        double samplesPerTick = (StdAudio.SAMPLE_RATE * 60.0) / (sequence.getResolution() * bpm);

        Track[] tracks = sequence.getTracks();
        Track track = sequence.createTrack();
        int maxSize = 0;
        int lead = 0;
        for (int i = 0; i < tracks.length; i++) {
            for (int j = 0; j < tracks[i].size(); j++) {
                track.add(tracks[i].get(j));
            }
        }

        long tick = 0;
        for (int i = 0; i < track.size(); i++) {
            // 获取MIDI事件，后面将这个事件转换为MetaMessage，包含事件信息
            MidiEvent event = track.get(i);
            MidiMessage msg = event.getMessage();
            byte[] data = msg.getMessage();

            if (msg instanceof MetaMessage) {
                MetaMessage mm = (MetaMessage) msg;
                if (mm.getType() == 0x51) {
                    // set tempo  0x51在midi中用来设置播放速度
                    data = mm.getData();
                    int tempo = (data[0] & 0xff) << 16 | (data[1] & 0xff) << 8 | (data[2] & 0xff);
                    bpm = 60000000.0 / tempo;
                    samplesPerTick = (StdAudio.SAMPLE_RATE * 60.0);
                    samplesPerTick /= (sequence.getResolution() * bpm);
                } else if (mm.getType() == 0x05) {
                    // lyrics  0x05用来设置歌词
                    data = mm.getData();
                    String lyrics = new String(data);
                    lyrics = lyrics.replace("\r", "\r\n");
                    System.out.print(lyrics);
                }
                continue;
            }

            if (event.getTick() > tick) {
                int samplesToSkip = (int) ((event.getTick() - tick) * samplesPerTick);
                for (int j = 0; j < samplesToSkip; j++) {
                    tic();
                    StdAudio.play(sample());
                }
                tick = event.getTick();
            }

            int j = 0;
            while (j < data.length - 2) {
                int s = data[j++] & 0xFF;

                if (s >= 0x80 && s <= 0x8F) {
                    // note off 闭声 将对应的vol置为0
                    int note = data[j++] & 0xFF;
                    int vel = data[j++] & 0xFF;
                    vol[note] = 0.0;
                } else if (s >= 0x90 && s <= 0x9F) {
                    // note on 发声 将对应的vol打开
                    int note = data[j++] & 0xFF;
                    int vel = data[j++] & 0xFF;
                    vol[note] = vel / 127.0;
                    strings[note].pluck();
                } else {
                    // status
                    int d = data[j++] & 0xFF;
                    int d2 = data[j++] & 0xFF;
                }
            }
        }

        System.out.println("please clap");
    }
}
