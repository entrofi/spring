

package net.entrofi.commons.util.model;

public enum Months {

    JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6),
    JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12);

    private int monthNum;

    Months(final int monthNum) {
        this.monthNum = monthNum;
    }

    public static String getMonthName(final int month) {
        for (Months monthName: Months.values()) {
            if (monthName.getMonthNum() == month) {
                return monthName.name();
            }
        }
        return "NaN";
    }

    public int getMonthNum() {
        return monthNum;
    }


}
