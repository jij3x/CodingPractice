public class Solution {
    double getMaxNuts(double N, double D, double C, double F) {
        if (N <= C)
            return Math.max(0, N - D * F);

        if (N % C == 0) {
            int numTrips = (int) (N / C - 1) * 2 + 1;
            double traveled = C / numTrips / F;
            if (traveled >= D)
                return N - numTrips * D * F;

            getMaxNuts(N - C, D - traveled, C, F);
        }

        int numTrips = 2 * ((int) Math.ceil(N / C) - 1) + 1;
        double traveled = C / numTrips / F;
        if (traveled >= D)
            return Math.max(N - numTrips * D * F, Math.floor(N / C) * C - (numTrips - 2) * D * F);

        double remainingNuts = N - C;
        if (N % C != 0 && N - C < Math.floor(N / C) * C - C * (numTrips - 2) / numTrips) {
            remainingNuts = Math.floor(N / C) * C - C * (numTrips - 2) / numTrips;
            numTrips -= 2;
        }

        return getMaxNuts(remainingNuts, D - traveled, C, F);
    }
}
