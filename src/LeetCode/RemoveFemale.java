package LeetCode;

/**
 * Created by Thanakorn on 6/4/15.
 */
public class RemoveFemale {


    public enum GENDER {MALE, FEMALE};

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            GENDER[] genders = generateRandom(10,4);
            //printGenders(genders);
            removeFemaleKeepOrder(genders);
            printGenders(genders);
        }

    }

    public static void removeFemaleKeepOrder(GENDER[] genders) {
        if (genders == null) {
            return;
        }

        if (genders.length == 1) {
            if (genders[0] == GENDER.FEMALE) {
                genders[0] = null;
            }
            return;
        }

        int left = genders.length;

        for (int i = 0; i < genders.length; i++) {
            if (genders[i] == GENDER.FEMALE) {
                left = i;
                break;
            }
        }

        int right = genders.length;

        for (int i = left + 1; i < genders.length; i++) {
            if (genders[i] == GENDER.MALE) {
                right = i;
                break;
            }
        }

        while (right < genders.length && left < genders.length) {
            genders[left] = GENDER.MALE;
            genders[right] = null;
            left++;
            right++;

            while (left < genders.length) {
                if (genders[left] == GENDER.FEMALE) {
                    break;
                }
                left++;
            }

            while(right < genders.length) {
                if (genders[right] == GENDER.MALE) {
                    break;
                }
                right++;
            }
        }

        for (int i = left; i < genders.length; i++) {
            genders[i] = null;
        }
    }

    public static void removeFemale(GENDER[] genders) {
        if (genders == null) {
            return;
        }

        int left = genders.length, right = -1;

        for (int i = 0; i < genders.length; i++) {
            if (genders[i] == GENDER.FEMALE) {
                left = i;
                break;
            }
        }

        for (int i = genders.length - 1; i >= 0; i--) {
            if (genders[i] == GENDER.MALE) {
                right = i;
                break;
            } else {
                genders[i] = null;
            }
        }

        while (left < right) {
            genders[left] = GENDER.MALE;
            genders[right] = null;
            left++;
            right--;

            while (left < genders.length) {
                if (genders[left] == GENDER.FEMALE) {
                    break;
                }
                left++;
            }

            while (right >= 0) {
                if (genders[right] == GENDER.MALE) {
                    break;
                } else {
                    genders[right] = null;
                }
                right--;
            }
        }
    }

    public static GENDER[] generateRandom(int length, int numFemales) {
        if (length <= 0 || numFemales > length || numFemales < 0) {
            return null;
        }

        GENDER[] genders = new GENDER[length];

        for (int i = 0; i < length; i++) {
            genders[i] = GENDER.MALE;
        }

        int femaleCount = 0;

        while (femaleCount != numFemales) {
            for (int i = 0; i < length && femaleCount != numFemales; i++) {
                if (genders[i] == GENDER.MALE) {
                    if (Math.random() > 0.5) {
                        genders[i] = GENDER.FEMALE;
                        femaleCount++;
                    }
                }
            }
        }

        return genders;
    }

    public static void printGenders(GENDER[] genders) {
        for (GENDER g : genders) {
            if (g != null) {
                System.out.print(g.toString() + " ");
            }
        }
        System.out.println();
    }

}
