/**
 * given a huge file of 4.3 billion sequential 32 bit integer, there is at least one duplicate number. return a duplicate number.
 * <p>
 * Follow-up: what if only a 10 MB memory is allowed?
 */
public class BillionIntegerDuplicate {

    public int getDuplicate(IntegerFileReader input) {
        int radix = 8;
        byte[] count = new byte[0xFFFFFFFF / radix]; // 512M

        while (input.hasNext()) {
            int num = input.nextInt();
            int bucket = num / radix;
            int slot = num % radix;

            if ((count[bucket] & (1 << slot)) == 1)
                return num;
            count[bucket] ^= 1 << slot;
        }

        return -1;
    }

    public int getDuplicate10M(IntegerFileReader input) {
        char[] count = new char[1 << 20]; // 16b * 2^ 20 = 16M bit = 2MB, char is unsigned, short is signed.
        int magic = 0, mask = 0x000FFFFF, radix = 1 << 12;
        // scan which bucket has duplicate:
        OUTER:
        while (magic <= radix) {
            //make a scan:
            while (input.hasNext()) {
                if (++count[input.nextInt() & mask] > radix)
                    break OUTER;
            }
            magic++;
            input.reset();
            resetArray(count);
        }
        resetArray(count);

        // bucket No. magic has duplicate, scan again to get duplicate in the bucket.
        input.reset();
        while (input.hasNext()) {
            int num = input.nextInt();
            if ((num & (magic << 20)) != 1) continue;
            if (count[num & mask]++ > 0) return num;
        }

        return -1;
    }

    private void resetArray(char[] input) {
        for (int i = 0; i < input.length; i++)
            input[i] = 0;
    }


    public interface IntegerFileReader {
        // get next integer in the file
        int nextInt();

        // if it reaches the end of the file
        boolean hasNext();

        // reset the offset to the head of the file
        void reset();
    }
}


