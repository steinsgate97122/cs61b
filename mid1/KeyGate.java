public class KeyGate {
    public class Fingerprint {}
    public class Key {}
    public class SkeletonKey extends Key {}

    public class StandardBox { public void unlock(Key k) {} }  // UK

    public class BioBox extends StandardBox {
        public void unlock(SkeletonKey sk) {}  // USK
        public void unlock(Fingerprint f) {}  // UF
    }

//    public void doStuff(Key k, SkeletonKey sk, Fingerprint f) {
//        StandardBox sb = new StandardBox();
//        StandardBox sbbb = new BioBox();
//        BioBox bb = new BioBox();
//
//        sb.unlock(k);
//        sbbb.unlock(k);
//        bb.unlock(k);
//
//        sb.unlock(sk);
//        sbbb.unlock(sk);
//        bb.unlock(sk);
//
//        sb.unlock(f);
//        sbbb.unlock(f);
//        bb.unlock(f);
//
//        bb = (BioBox) sbbb;
//        ((StandardBox) bb).unlock(sk);
//        ((StandardBox) sbbb).unlock(sk);
//        ((BioBox) sb).unlock(sk);
//    }
}
