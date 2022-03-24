package morticia.mancia.Alignment;

public class AlignmentUtil {
    public static ManciaAlignment convert(int id) {
        if (id == 1) {
            return ManciaAlignment.NECRO;
        } else if (id == 2) {
            return ManciaAlignment.VITO;
        } else {
            return ManciaAlignment.NEUTRAL;
        }
    }

    public static int convert(ManciaAlignment id) {
        if (id == ManciaAlignment.NECRO) {
            return 1;
        } else if (id == ManciaAlignment.VITO) {
            return 2;
        } else {
            return 3;
        }
    }
}
