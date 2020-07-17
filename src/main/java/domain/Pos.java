package domain;

public enum Pos {
    ORDER(1), CALCULATION(2), EXIT(3);

    private final int selectNumber;

    Pos(final int selectNumber) {
        this.selectNumber = selectNumber;
    }

    public static boolean isExit(final int selectNumber) {
        return EXIT.selectNumber == selectNumber;
    }

    public static boolean isOrder(final int selectNumber) {
        return CALCULATION.selectNumber == selectNumber;
    }

    public static boolean isCalculation(final int selectNumber) {
        return ORDER.selectNumber == selectNumber;
    }
}
